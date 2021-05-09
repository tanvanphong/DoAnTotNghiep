package com.iuh.connection.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iuh.connection.model.DanhGia;


@Repository
public interface DanhGiaRepository extends JpaRepository<DanhGia, String> {
	
}
