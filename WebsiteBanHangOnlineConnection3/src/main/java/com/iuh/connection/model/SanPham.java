package com.iuh.connection.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class SanPham implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;

	private BigDecimal donGia;

	@Column(name = "ten_san_pham", columnDefinition = "NVARCHAR(255)")
	private String tenSanPham;
	@Column(name = "mo_ta", columnDefinition = "NVARCHAR(255)")
	private String moTa;
	@Column(name = "mo_ta_chi_tiet", columnDefinition = "TEXT")
	private String moTaChiTiet;

	private int namSanXuat;
	private long soLuongBan;
	private long soLuongKho;
	private LocalDate ngayTao;
	private String trangThai;


	private String hinhAnh;
	private String hinhAnh1;
	private String hinhAnh2;
	private String hinhAnh3;
	
	@OneToMany(mappedBy = "sanPham")
	private List<DanhGia> danhGias;

	@JsonIgnore
	@OneToMany(mappedBy = "sanPham",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<ChiTietHoaDon> chiTietHoaDons;

	private String thuongHieu;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "danh_muc_id", referencedColumnName = "id")
	private DanhMuc danhMuc;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "nguoi_dung_id", referencedColumnName = "id")
	private NguoiDung nguoiDung;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "khuyen_mai_id", referencedColumnName = "id")
	private KhuyenMai khuyenMai;
	

	public SanPham() {
		super();
	}

	@Override
	public String toString() {
		return "SanPham [tenSanPham=" + tenSanPham + ", hinhAnh=" + hinhAnh + " _ " + hinhAnh1 + " _ " + hinhAnh2
				+ " _ " + hinhAnh3 	+ ", danhMuc=" + danhMuc.getId() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SanPham other = (SanPham) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
