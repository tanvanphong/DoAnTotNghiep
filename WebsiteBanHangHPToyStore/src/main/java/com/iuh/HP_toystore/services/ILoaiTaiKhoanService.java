package com.iuh.HP_toystore.services;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.iuh.HP_toystore.model.LoaiTaiKhoan;
import com.iuh.HP_toystore.model.TaiKhoan;


@Service
@Transactional
public interface ILoaiTaiKhoanService {

	List<LoaiTaiKhoan> findAll ();
	
	LoaiTaiKhoan findLoaiTKbyID(long id);
	
	LoaiTaiKhoan findByTenLoaiTaiKhoan(String tenloaiTaiKhoan);
}
