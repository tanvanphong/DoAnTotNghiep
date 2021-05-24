package com.iuh.ABCStore.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;



@Entity

public class KhuyenMai implements Serializable {
	private static final long serialVersionUID = 536044485786468627L;

	@Id
	private String id;
	
	private String tenKhuyenMai;
	
	private double phanTramKhuyenMai;
	
	private BigDecimal giaKhuyenmai;
	
	@OneToMany(mappedBy = "khuyenMai")
	private List<SanPham> sanPhams;

	public KhuyenMai() {
		super();
	}

	@Override
	public String toString() {
		return "KhuyenMai [id=" + id + ", tenKhuyenMai=" + tenKhuyenMai + ", phanTramKhuyenMai=" + phanTramKhuyenMai
				+ ", sanPhams=" + sanPhams + "]";
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
		KhuyenMai other = (KhuyenMai) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public KhuyenMai(String id, String tenKhuyenMai, double phanTramKhuyenMai, BigDecimal giaKhuyenmai,
			List<SanPham> sanPhams) {
		super();
		this.id = id;
		this.tenKhuyenMai = tenKhuyenMai;
		this.phanTramKhuyenMai = phanTramKhuyenMai;
		this.giaKhuyenmai = giaKhuyenmai;
		this.sanPhams = sanPhams;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTenKhuyenMai() {
		return tenKhuyenMai;
	}

	public void setTenKhuyenMai(String tenKhuyenMai) {
		this.tenKhuyenMai = tenKhuyenMai;
	}

	public double getPhanTramKhuyenMai() {
		return phanTramKhuyenMai;
	}

	public void setPhanTramKhuyenMai(double phanTramKhuyenMai) {
		this.phanTramKhuyenMai = phanTramKhuyenMai;
	}

	public BigDecimal getGiaKhuyenmai() {
		return giaKhuyenmai;
	}

	public void setGiaKhuyenmai(BigDecimal giaKhuyenmai) {
		this.giaKhuyenmai = giaKhuyenmai;
	}

	public List<SanPham> getSanPhams() {
		return sanPhams;
	}

	public void setSanPhams(List<SanPham> sanPhams) {
		this.sanPhams = sanPhams;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
