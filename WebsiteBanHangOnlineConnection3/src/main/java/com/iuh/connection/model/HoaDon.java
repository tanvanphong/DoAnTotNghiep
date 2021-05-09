package com.iuh.connection.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class HoaDon implements Serializable {
	

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")  
	private String id;
	
	private LocalDateTime ngayLap;
	
	@ManyToOne
	@JoinColumn(name = "nguoi_dung_id", referencedColumnName = "id")
	private NguoiDung nguoiDung;
	
	@OneToMany(mappedBy = "hoaDon",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<ChiTietHoaDon> dssp;

	@OneToOne(mappedBy = "hoaDon", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private DiaChiGiaoHangHoaDon diaChiGiaoHang;

	private String hinhThucThanhToan;
	private BigDecimal tongTien;
	private double tienShip;
	private String trangThai;

	public HoaDon() {
		super();
	}

	public void themSanPham(SanPham sp, int soluong) {
		if (this.dssp == null)
			this.dssp = new ArrayList<ChiTietHoaDon>();
		ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon(this, sp, sp.getDonGia(), soluong);
		dssp.add(chiTietHoaDon);
	}

	public BigDecimal tinhTongTien() {
		double sum = 0;
		for (int i = 0; i < dssp.size(); i++)
			sum += dssp.get(i).getSanPham().getDonGia().doubleValue() * dssp.get(i).getSoLuong();
		return BigDecimal.valueOf(sum);
	}

	public HoaDon(String id, LocalDateTime ngayLap, BigDecimal tongTien, String trangThai) {
		super();
		this.id = id;
		this.ngayLap = ngayLap;
		this.tongTien = tongTien;
		this.trangThai = trangThai;
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
		HoaDon other = (HoaDon) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "HoaDon [id=" + id + ", ngayLap=" + ngayLap + ", hinhThucThanhToan=" + hinhThucThanhToan + ", tongTien="
				+ tongTien + ", tienShip=" + tienShip + ", trangThai=" + trangThai + "]";
	}


	


}
