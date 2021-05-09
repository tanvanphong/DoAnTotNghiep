package com.iuh.connection.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.iuh.connection.model.NguoiDung;
import com.iuh.connection.model.DiaChi;

@Service
@Transactional
public interface IDiaChiService {

	Optional<DiaChi> findLienHeById(String lienHeid);

	Page<DiaChi> findAllLienHesByNguoiDung(Pageable pageable, NguoiDung nguoiDung);

	DiaChi findByIdAndTrangThai(NguoiDung nguoiDung, boolean trangthai);

	DiaChi saveLienHe(DiaChi lienhe);

	List<DiaChi> findAllLienHe();

	List<DiaChi> findByNguoiDung(NguoiDung nguoiDung);

	
	DiaChi findById(String id);
	
	boolean delete(DiaChi lh);

	DiaChi getMot(String id);

}
