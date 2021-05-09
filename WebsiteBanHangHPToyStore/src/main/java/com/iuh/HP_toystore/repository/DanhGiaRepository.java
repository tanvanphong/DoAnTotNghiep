package com.iuh.HP_toystore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iuh.HP_toystore.model.DanhGia;


@Repository
public interface DanhGiaRepository extends JpaRepository<DanhGia, String> {
	
}
