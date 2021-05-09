
package com.iuh.connection.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@EqualsAndHashCode
public class DanhGia implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name ="id")
	private String id;

	@Column(name = "noi_dung", columnDefinition = "NVARCHAR(255)")
	private String noiDung;
	
	private LocalDateTime ngayDanhGia;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "nguoi_dung_id", referencedColumnName = "id")
	private NguoiDung nguoiDung;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "san_pham_id", referencedColumnName = "id")
	private SanPham sanPham;

	public DanhGia() {
		super();
	}

	@Override
	public String toString() {
		return "DanhGia [id=" + id + ", noiDung=" + noiDung + ", nguoiDung=" + nguoiDung.getHoTen() + ", sanPham=" + sanPham.getId() + "]";
	}
	
	

	
	

	

}
