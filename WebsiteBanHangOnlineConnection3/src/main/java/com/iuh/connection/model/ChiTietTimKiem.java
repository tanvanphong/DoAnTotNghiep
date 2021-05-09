package com.iuh.connection.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@IdClass(ChiTietTimKiemPK.class)
@Setter
@Getter
@EqualsAndHashCode
public class ChiTietTimKiem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonIgnore
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	private TimKiem timKiem;
	
	
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "nguoi_dung_id", referencedColumnName = "id")
	private NguoiDung nguoiDung;
	
	
	private String trangThai;
	private int soLuong;
	
	private LocalDateTime thoiGianTimKiem;
	
	public ChiTietTimKiem() {
		super();
	}

	@Override
	public String toString() {
		return "ChiTietTimKiem [timkiem=" + timKiem.getId() + ", nguoiDung=" + nguoiDung.getId() + "]";
	}
	
	
	
	
	

	
}
