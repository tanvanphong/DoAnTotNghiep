package com.iuh.connection.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.iuh.connection.model.DanhMuc;



@Service
@Transactional
public interface IDanhMucService {

	List<DanhMuc> findAllLoaiSanPham();
	
	List<DanhMuc> findAllNguoiDung(String id);

}
