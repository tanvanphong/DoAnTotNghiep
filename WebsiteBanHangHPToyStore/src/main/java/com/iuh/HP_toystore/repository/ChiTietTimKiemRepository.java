package com.iuh.HP_toystore.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iuh.HP_toystore.model.ChiTietTimKiem;
import com.iuh.HP_toystore.model.NguoiDung;
import com.iuh.HP_toystore.model.TimKiem;

public interface ChiTietTimKiemRepository extends JpaRepository<ChiTietTimKiem, String> {

	List<ChiTietTimKiem> findAllChiTietTimKiemsByNguoiDung(NguoiDung nguoiDung);

	ChiTietTimKiem findByTimKiemAndNguoiDung(TimKiem timKiem, NguoiDung nguoiDung);

	ChiTietTimKiem findTopByNguoiDungOrderByTimKiemDesc(NguoiDung nguoiDung);
	
	
	List<ChiTietTimKiem> findAllChiTietTimKiemsByNguoiDungAndThoiGianTimKiemBetweenAndTrangThai(NguoiDung nguoiDung,LocalDateTime start, LocalDateTime end,String trangThai);

}
