package com.iuh.connection.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@EqualsAndHashCode
public class TaiKhoan implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "id")
	private String id;
	
	private String tenTaiKhoanEmail;
	
	private String matKhau;
	
	
	@OneToOne
	@MapsId
	@JoinColumn(name = "nguoi_dung_id", referencedColumnName = "id")
	private NguoiDung nguoiDung;

	@ManyToMany
	private Set<LoaiTaiKhoan> loaiTaiKhoan;

	private String xacNhanEmail;

	@Temporal(TemporalType.DATE)
	private Date ngayDangKy;

	private boolean trangThai;

	public TaiKhoan() {
		super();
	}

	public TaiKhoan(String tenTaiKhoanEmail, String matKhau) {
		super();
		this.tenTaiKhoanEmail = tenTaiKhoanEmail;
		this.matKhau = matKhau;
	}

	@Override
	public String toString() {
		return "TaiKhoan [id=" + id + ", tenTaiKhoanEmail=" + tenTaiKhoanEmail + ", matKhau=" + matKhau
				+ ", xacNhanEmail=" + xacNhanEmail + ", ngayDangKy=" + ngayDangKy + ", trangThai=" + trangThai + "]";
	}


	
	

}
