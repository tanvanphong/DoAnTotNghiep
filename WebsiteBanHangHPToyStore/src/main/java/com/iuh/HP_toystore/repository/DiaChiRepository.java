package com.iuh.HP_toystore.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iuh.HP_toystore.model.DiaChi;
import com.iuh.HP_toystore.model.NguoiDung;


@Repository
public interface DiaChiRepository extends JpaRepository<DiaChi, String> {
	Page<DiaChi> findAllLienHesByNguoiDung(Pageable pageable, NguoiDung nguoiDung);
	DiaChi findByNguoiDungAndTrangThai(NguoiDung nguoiDung, boolean trangthai);
	List<DiaChi> findByNguoiDung(NguoiDung nguoiDung);
	
	
}
