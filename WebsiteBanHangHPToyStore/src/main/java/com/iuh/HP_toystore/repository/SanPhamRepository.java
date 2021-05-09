package com.iuh.HP_toystore.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.iuh.HP_toystore.model.DanhMuc;
import com.iuh.HP_toystore.model.NguoiDung;
import com.iuh.HP_toystore.model.SanPham;


@Repository
public interface SanPhamRepository extends JpaRepository<SanPham, String> {
	
	List<SanPham> findTop10ByNguoiDung(NguoiDung nguoiDung);
	
	Page<SanPham> findAllSanPhamsByNguoiDungAndTrangThai(Pageable pageable,NguoiDung nguoiDung,String trangThai);
	
	Page<SanPham> findAllSanPhamsByTrangThai(Pageable pageable,String trangThai);
	Page<SanPham> findAllSanPhamsByDanhMuc(Pageable pageable,DanhMuc danhMuc);
	
	@Query("SELECT sp FROM SanPham sp WHERE CONCAT(sp.tenSanPham, ' ', sp.donGia, ' ', sp.namSanXuat, ' ', sp.thuongHieu) LIKE %?1%")
	Page<SanPham> timKiem(Pageable pageable,String keyword);
	
	@Query("SELECT sp FROM SanPham sp WHERE CONCAT(sp.tenSanPham, ' ', sp.donGia, ' ', sp.namSanXuat, ' ', sp.thuongHieu,' ', sp.danhMuc.tenDanhMuc ) LIKE %?1% and sp.trangThai='Đã Xác Nhận' ")
	Page<SanPham> timKiemMoi(Pageable pageable,String keyword);
}
