package com.iuh.connection.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.iuh.connection.model.ChiTietHoaDon;
import com.iuh.connection.model.HoaDon;
import com.iuh.connection.model.SanPham;
@Service
@Transactional
public interface IChiTietHoaDonService {
	
	boolean save(ChiTietHoaDon ct);

	List<ChiTietHoaDon> findAllByHoaDon(HoaDon hoadon);
	
	void delete(ChiTietHoaDon cthd);
	
	ChiTietHoaDon findByHoaDonAndSanPham(HoaDon hd, SanPham sp);
}
