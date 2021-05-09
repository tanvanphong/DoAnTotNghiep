package com.iuh.connection.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.iuh.connection.model.DanhMuc;
import com.iuh.connection.model.NguoiDung;
import com.iuh.connection.model.SanPham;



@Service
@Transactional
public interface ISanPhamService {

	Boolean saveSanPham(SanPham sanPham);
	
	List<SanPham> saveALLSanPham(List<SanPham> dssanPham);

	Boolean deleteSanPham(SanPham sanPham);

	Optional<SanPham> findSanPhamById(String sanPhamId);

	List<SanPham> findAllSanPham();

	Page<SanPham> timKiem(Pageable pageable,String keyword);
	
	SanPham findbyId(String id);

	Page<SanPham> findAllSanPhamsByTrangThai(Pageable pageable, String trangThai);

	Page<SanPham> findAllSanPhamsByNguoiDung(Pageable pageable, NguoiDung nguoiDung, String trangThai);
	
	Page<SanPham> findAll (Pageable pageable);

	Page<SanPham> timKIemMoi(Pageable pageable, String keyword);
	
	
}
