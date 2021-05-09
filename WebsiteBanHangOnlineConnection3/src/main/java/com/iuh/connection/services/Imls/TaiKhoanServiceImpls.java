package com.iuh.connection.services.Imls;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.iuh.connection.model.LoaiTaiKhoan;
import com.iuh.connection.model.TaiKhoan;
import com.iuh.connection.repository.TaiKhoanRepository;
import com.iuh.connection.services.ITaiKhoanService;



@Service
@Transactional
public class TaiKhoanServiceImpls implements ITaiKhoanService{
	@Autowired
	private TaiKhoanRepository taiKhoanRepository;
	
	
	@Override
	public boolean save(TaiKhoan taikhoan) {
		taiKhoanRepository.save(taikhoan);
		return true;
	}


	@Override
	public TaiKhoan findByTenTaiKhoanEmail(String tenTaiKhoanEmail) {
		return taiKhoanRepository.findByTenTaiKhoanEmail(tenTaiKhoanEmail);
	}


	@Override
	public TaiKhoan findByXacNhanEmail(String confirmationToken) {
		return taiKhoanRepository.findByXacNhanEmail(confirmationToken);
	}
	
	@Override 
	public Page<TaiKhoan> findAllTaiKhoansByloaiTaiKhoanAndTrangThai(Pageable pageable,LoaiTaiKhoan loaiTaiKhoan,boolean trangThai){
		return taiKhoanRepository.findAllTaiKhoansByloaiTaiKhoanAndTrangThai(pageable, loaiTaiKhoan, trangThai);
	}
	
	@Override
	public TaiKhoan findTaiKhoanByID(String id) {
		return taiKhoanRepository.findById(id).get();
	}


	@Override
	public Boolean delete(TaiKhoan taiKhoan) {
		taiKhoanRepository.delete(taiKhoan);
		return true;
	}


	@Override
	public Page<TaiKhoan> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return taiKhoanRepository.findAll(pageable);
	}


	@Override
	public Page<TaiKhoan> timKiem(Pageable pageable, String keyword) {
		// TODO Auto-generated method stub
		return taiKhoanRepository.timKiem(pageable, keyword);
	}
	
	
}
