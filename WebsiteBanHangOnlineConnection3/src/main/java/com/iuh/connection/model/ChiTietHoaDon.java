package com.iuh.connection.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@IdClass(ChiTietHoaDonPK.class)
@Setter
@Getter
@EqualsAndHashCode
public class ChiTietHoaDon implements Serializable {

	private static final long serialVersionUID = 1L;
	@JsonIgnore
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "hoa_don_id", referencedColumnName = "id")
	private HoaDon hoaDon;
	
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "san_pham_id", referencedColumnName = "id")
	private SanPham sanPham;

	private BigDecimal tongTien;
	private int soLuong;

	public ChiTietHoaDon() {
		super();
	}

	public ChiTietHoaDon(HoaDon hoaDon,int soLuong, SanPham sanPham) {
		super();
		this.tongTien = sanPham.getDonGia().multiply(BigDecimal.valueOf(soLuong));
		this.soLuong = soLuong;
		this.sanPham = sanPham;
		this.hoaDon = hoaDon;
	}

	public ChiTietHoaDon(HoaDon hoaDon, SanPham sanPham, BigDecimal tongTien, int soLuong) {
		super();
		this.hoaDon = hoaDon;
		this.sanPham = sanPham;
		this.tongTien = tongTien;
		this.soLuong = soLuong;
	}

	public BigDecimal getTongTienChiTietHoaDon() {
		return BigDecimal.valueOf(soLuong).multiply(tongTien);
	}
	
	@Override
	public String toString() {
		return "ChiTietHoaDon [hoaDon=" + hoaDon.getId() + ", sanPham=" + sanPham.getTenSanPham() + ", donGia=" + tongTien + ", soLuong="
				+ soLuong + "]";
	}

	/**
	 * @param sanPham
	 * @param tongTien
	 * @param soLuong
	 */
	public ChiTietHoaDon(SanPham sanPham, BigDecimal tongTien, int soLuong) {
		super();
		this.sanPham = sanPham;
		this.tongTien = tongTien;
		this.soLuong = soLuong;
	}

	
}
