package com.iuh.connection.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
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
	
	
}
