package com.iuh.HP_toystore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iuh.HP_toystore.model.LoaiTaiKhoan;


@Repository
public interface LoaiTaiKhoanRepository extends JpaRepository<LoaiTaiKhoan, Long> {
	LoaiTaiKhoan findByTenLoaiTaiKhoan(String tenloaiTaiKhoan);
}
