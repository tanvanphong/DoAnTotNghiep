package com.iuh.connection.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iuh.connection.model.ChiTietTimKiem;
import com.iuh.connection.model.NguoiDung;
import com.iuh.connection.model.TimKiem;

public interface ChiTietTimKiemRepository extends JpaRepository<ChiTietTimKiem, String> {

	List<ChiTietTimKiem> findAllChiTietTimKiemsByNguoiDung(NguoiDung nguoiDung);

	ChiTietTimKiem findByTimKiemAndNguoiDung(TimKiem timKiem, NguoiDung nguoiDung);

	ChiTietTimKiem findTopByNguoiDungOrderByTimKiemDesc(NguoiDung nguoiDung);
	
	
	List<ChiTietTimKiem> findAllChiTietTimKiemsByNguoiDungAndThoiGianTimKiemBetween(NguoiDung nguoiDung,LocalDateTime start, LocalDateTime end);

}
