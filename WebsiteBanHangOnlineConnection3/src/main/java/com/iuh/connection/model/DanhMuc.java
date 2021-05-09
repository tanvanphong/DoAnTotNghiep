package com.iuh.connection.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@EqualsAndHashCode
public class DanhMuc implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name ="id")
	private String id;
	
	@Column(name = "ten_danh_muc",columnDefinition = "NVARCHAR(255)")
	private String tenDanhMuc;
	
	
	@OneToMany(mappedBy = "danhMuc")
	private List<SanPham> sanPhams;
	

	public DanhMuc() {
		super();
	}

	@Override
	public String toString() {
		return "DanhMuc [id=" + id + ", tenDanhMuc=" + tenDanhMuc + "]";
	}


	
	

	

	

}
