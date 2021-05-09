package com.iuh.HP_toystore.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.iuh.HP_toystore.model.LoaiTaiKhoan;
import com.iuh.HP_toystore.model.TaiKhoan;


@Service
@Transactional
public interface ITaiKhoanService {

	boolean save(TaiKhoan taikhoan);

	TaiKhoan findByTenTaiKhoanEmail(String tenTaiKhoanEmail);

	TaiKhoan findByXacNhanEmail(String confirmationToken);

	Page<TaiKhoan> findAllTaiKhoansByloaiTaiKhoanAndTrangThai(Pageable pageable, LoaiTaiKhoan loaiTaiKhoan,
			boolean trangThai);

	TaiKhoan findTaiKhoanByID(String id);

	Boolean delete(TaiKhoan taiKhoan);

	Page<TaiKhoan> findAll(Pageable pageable);
	
	Page<TaiKhoan> timKiem(Pageable pageable,String keyword);
}
