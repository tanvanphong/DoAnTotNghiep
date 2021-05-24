package com.iuh.ABCStore.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.amazonaws.services.simpleworkflow.flow.core.TryCatch;
import com.iuh.ABCStore.config.PaypalPaymentIntent;
import com.iuh.ABCStore.config.PaypalPaymentMethod;
import com.iuh.ABCStore.model.ChiTietHoaDon;
import com.iuh.ABCStore.model.DiaChi;
import com.iuh.ABCStore.model.DiaChiGiaoHangHoaDon;
import com.iuh.ABCStore.model.GioHang;
import com.iuh.ABCStore.model.HoaDon;
import com.iuh.ABCStore.model.NguoiDung;
import com.iuh.ABCStore.model.SanPham;
import com.iuh.ABCStore.model.TaiKhoan;
import com.iuh.ABCStore.services.IDiaChiService;
import com.iuh.ABCStore.services.IGioHangService;
import com.iuh.ABCStore.services.IHoaDonService;
import com.iuh.ABCStore.services.ISanPhamService;
import com.iuh.ABCStore.services.ITaiKhoanService;
import com.iuh.ABCStore.services.PaypalService;
import com.iuh.ABCStore.utils.Utils;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

@Controller
public class PaymentController {

	@Autowired
	private ITaiKhoanService iTaiKhoanService;

	@Autowired
	private IHoaDonService iHoaDonService;
	
	@Autowired
	private IDiaChiService lienHeService;
	
	@Autowired
	private ISanPhamService sanPhamService;
	
	@Autowired
	private IHoaDonService hoaDonService;
	
	@Autowired
	private ITaiKhoanService taiKhoanService;

	public static final String URL_PAYPAL_SUCCESS = "pay-pal/hoan-tat";
	public static final String URL_PAYPAL_CANCEL = "pay-pal/giao-dich-that-bai";

	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private PaypalService paypalService;

	@RequestMapping(value = "/pay-pal", method = RequestMethod.GET)
	public String pay(HttpServletRequest request, Authentication authentication) {
		TaiKhoan taiKhoan = taiKhoanService
				.findByTenTaiKhoanEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		HoaDon hd = hoaDonService.findHoaDonsByNguoiDungAndTrangThai(taiKhoan.getNguoiDung(), "Giỏ Hàng");
		double price = hd.getTongTien().doubleValue() / 23000;
		String cancelUrl = Utils.getBaseURL(request) + "/" + URL_PAYPAL_CANCEL;
		String successUrl = Utils.getBaseURL(request) + "/" + URL_PAYPAL_SUCCESS;
		try {
			Payment payment = paypalService.createPayment(price, "USD", PaypalPaymentMethod.paypal,
					PaypalPaymentIntent.sale, "payment description", cancelUrl, successUrl);
			for (Links links : payment.getLinks()) {
				if (links.getRel().equals("approval_url")) {
					return "redirect:" + links.getHref();
				}
			}
		} catch (PayPalRESTException e) {
			log.error(e.getMessage());
		}
		return "redirect:/";
	}

	@GetMapping(URL_PAYPAL_CANCEL)
	public String cancelPay() {
		return "giao-dich-that-bai";
	}

	@GetMapping(URL_PAYPAL_SUCCESS)
	public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId,
			Authentication authentication, HttpSession session) {

		try {
			Payment payment = paypalService.executePayment(paymentId, payerId);
			if (payment.getState().equals("approved")) {
				@SuppressWarnings("unchecked")
				TaiKhoan taiKhoan = iTaiKhoanService.findByTenTaiKhoanEmail(authentication.getName());
				DiaChi dc = lienHeService.findById((String)session.getAttribute("idLH"));
				HoaDon hd = iHoaDonService.findHoaDonsByNguoiDungAndTrangThai(taiKhoan.getNguoiDung(), "Giỏ Hàng");
				DiaChiGiaoHangHoaDon chiGiaoHangHoaDon = new DiaChiGiaoHangHoaDon();
				chiGiaoHangHoaDon.setDiaChi(dc.getDiaChi());
				chiGiaoHangHoaDon.setHoTen(dc.getHoTen());
				chiGiaoHangHoaDon.setSoDienThoai(dc.getSoDienThoai());
				chiGiaoHangHoaDon.setHoaDon(hd);
				hd.setDiaChiGiaoHang(chiGiaoHangHoaDon);
				hd.setNgayLap(LocalDateTime.now().withNano(0));
				hd.setTienShip(0.0);
				hd.setTrangThai("Đã Thanh Toán");
				hd.setHinhThucThanhToan("Thanh toán Pay-Pal");
				List<ChiTietHoaDon> dscthd = hd.getDssp();
				List<SanPham> dssp = new ArrayList<SanPham>();
				for(ChiTietHoaDon cthd :dscthd) {
					SanPham sp = cthd.getSanPham();
					sp.setSoLuongBan(sp.getSoLuongBan()+cthd.getSoLuong());
					dssp.add(sp);
				}
				sanPhamService.saveALLSanPham(dssp);
				hoaDonService.saveHoaDon(hd);
				return "hoan-tat";
			}
		} catch (PayPalRESTException e) {
			log.error(e.getMessage());
		}
		return "redirect:/";
	}
}
