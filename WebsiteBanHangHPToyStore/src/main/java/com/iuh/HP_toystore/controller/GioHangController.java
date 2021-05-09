package com.iuh.HP_toystore.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.iuh.HP_toystore.model.DiaChi;
import com.iuh.HP_toystore.model.GioHang;
import com.iuh.HP_toystore.model.SanPham;
import com.iuh.HP_toystore.model.TaiKhoan;
import com.iuh.HP_toystore.services.IDiaChiService;
import com.iuh.HP_toystore.services.IGioHangService;
import com.iuh.HP_toystore.services.ISanPhamService;
import com.iuh.HP_toystore.services.ITaiKhoanService;
import com.iuh.HP_toystore.utils.TinhTien;

@Controller
public class GioHangController {

	@Autowired
	private ISanPhamService iSanPhamService;

	@Autowired
	private ITaiKhoanService taiKhoanService;

	@Autowired
	private IDiaChiService lienHeService;

	@Autowired
	private TinhTien tinhTien;

	@Autowired
	private IGioHangService iGioHangService;

	private HashMap<String, GioHang> gioHang;

	@RequestMapping("/hoan-tat")
	public String hoanTatDatHang() {
		return "hoan-tat";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("/gio-hang")
	public String xemGioHang(Model model, HttpSession session) {
		gioHang = (HashMap<String, GioHang>) session.getAttribute("giohang");
		if (gioHang == null || gioHang.isEmpty()) {
			gioHang = new HashMap<String, GioHang>();
			return "redirect:/gio-hang-rong";
		} else
			return "gio-hang";

	}

	@SuppressWarnings("unchecked")
	@GetMapping("/gio-hang/them-san-pham")
	public String themSanPhamNhieu(ModelMap mm, HttpSession session, @RequestParam("id") String id,
			@RequestParam("sl") int sl) {
		gioHang = (HashMap<String, GioHang>) session.getAttribute("giohang");
		if (gioHang == null) {
			gioHang = new HashMap<String, GioHang>();
		}
		SanPham sp = iSanPhamService.findSanPhamById(String.valueOf(id)).get();
		long kt = sp.getSoLuongKho() - sp.getSoLuongBan();
		
		if(kt<sl) {
			mm.addAttribute("error","Số lượng sản phẩm trong kho không đủ");
			return "redirect:/san-pham/"+id;
		}
		
		if (sp != null) {
			if (gioHang.containsKey(id)) {
				GioHang giohang = gioHang.get(id);
				giohang.setSanPham(sp);
				giohang.setSoLuong(giohang.getSoLuong() + sl);
			} else {
				GioHang giohang = new GioHang();
				giohang.setSanPham(sp);
				giohang.setSoLuong(sl);
				gioHang.put(id, giohang);
			}
		}
		session.setAttribute("giohang", gioHang);
		session.setAttribute("tongtien", tinhTien.tongTien(gioHang));
		session.setAttribute("sl", gioHang.size());
		return "redirect:/san-pham/"+id;
	}

	@GetMapping("/gio-hang/xoa-san-pham/{id}")
	public String xoaSanPhamGH(@PathVariable("id") String id, HttpSession session) {
		gioHang = (HashMap<String, GioHang>) session.getAttribute("giohang");
		gioHang.remove(id);
		session.setAttribute("tongtien", tinhTien.tongTien(gioHang));
		session.setAttribute("sl", gioHang.size());
		return "redirect:/gio-hang";
	}

	@GetMapping("/gio-hang/xoa-san-pham-nav/{id}")
	public String xoaSanPhamNav(@PathVariable("id") String id, HttpSession session) {
		gioHang = (HashMap<String, GioHang>) session.getAttribute("giohang");
		gioHang.remove(id);
		session.setAttribute("tongtien", tinhTien.tongTien(gioHang));
		session.setAttribute("sl", gioHang.size());
		return "redirect:/";
	}

	@RequestMapping(value = "/gio-hang/cap-nhat-gio-hang", method = RequestMethod.GET)
	public String capNhatGioHang(HttpSession session, @RequestParam("sl") int soluong,
			@RequestParam("key") String key) {
		gioHang = (HashMap<String, GioHang>) session.getAttribute("giohang");
		if (soluong == 0) {
			gioHang.remove(key);
		} else {
			for (GioHang cart : gioHang.values()) {
				if (cart.getSanPham().getId().equals(key)) {
					cart.setSoLuong(soluong);
				}
			}
		}
		session.setAttribute("giohang", gioHang);
		session.setAttribute("tongtien", tinhTien.tongTien(gioHang));

		session.setAttribute("sl", gioHang.size());
		return "redirect:/gio-hang";
	}

	@RequestMapping("/dat-hang")
	public String datHang(Model model, HttpSession session) {

		gioHang = (HashMap<String, GioHang>) session.getAttribute("giohang");
		if (gioHang == null || gioHang.isEmpty()) {
			gioHang = new HashMap<>();
			return "redirect:/gio-hang-rong";
		} else {

			TaiKhoan taiKhoan = taiKhoanService
					.findByTenTaiKhoanEmail(SecurityContextHolder.getContext().getAuthentication().getName());
			if (taiKhoan.getNguoiDung().getDanhSachLienHe().isEmpty()
					|| taiKhoan.getNguoiDung().getDanhSachLienHe() == null) {
				return "redirect:/tai-khoan/dia-chi";
			} else {
				DiaChi lienHeNew = new DiaChi();
				model.addAttribute("dslienhe", lienHeService.findByNguoiDung(taiKhoan.getNguoiDung()));
				model.addAttribute("lienHeNew", lienHeNew);
			}
		}
		return "thanh-toan-dia-chi";

	}

	@GetMapping("/dat-hang/chi-tiet")
	public String datHangChiTiet(Model model, HttpSession session, @RequestParam("idLienhe") String idLienhe) {

		gioHang = (HashMap<String, GioHang>) session.getAttribute("giohang");

		if (gioHang == null || gioHang.isEmpty()) {
			gioHang = new HashMap<>();
			return "redirect:/gio-hang-rong";
		} else {
			TaiKhoan taiKhoan = taiKhoanService
					.findByTenTaiKhoanEmail(SecurityContextHolder.getContext().getAuthentication().getName());
			model.addAttribute("khachhang", taiKhoan.getNguoiDung());
			session.setAttribute("lienhe", lienHeService.findByLienHeID(idLienhe).get());
			return "thanh-toan-chi-tiet";
		}
	}

	@GetMapping("/dat-hang/thanh-toan-giao-hang")
	public String thanhToanGiaoHang(Model model, HttpSession session) {
		gioHang = (HashMap<String, GioHang>) session.getAttribute("giohang");
		DiaChi LienHe = (DiaChi) session.getAttribute("lienhe");
		TaiKhoan taiKhoan = taiKhoanService
				.findByTenTaiKhoanEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		if (gioHang == null || gioHang.isEmpty()) {
			gioHang = new HashMap<>();
			return "redirect:/gio-hang-rong";
		}
		if (iGioHangService.thanhToanGiaohang(gioHang, taiKhoan.getNguoiDung(), LienHe.getId())) {
			session.removeAttribute("giohang");
			session.removeAttribute("sl");
			session.removeAttribute("tongtien");
			session.removeAttribute("lienhe");
			return "hoan-tat";
		} else {
			return "giao-dich-that-bai";
		}

	}
}
