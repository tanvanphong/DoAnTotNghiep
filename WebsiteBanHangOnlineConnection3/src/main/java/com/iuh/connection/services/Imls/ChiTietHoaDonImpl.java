package com.iuh.connection.services.Imls;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iuh.connection.model.ChiTietHoaDon;
import com.iuh.connection.model.HoaDon;
import com.iuh.connection.model.SanPham;
import com.iuh.connection.repository.ChiTietHoaDonRepository;
import com.iuh.connection.services.IChiTietHoaDonService;

@Service
@Transactional
public class ChiTietHoaDonImpl implements IChiTietHoaDonService{

	@Autowired
	private ChiTietHoaDonRepository chiTietHoaDonRepository;
	
	@Override
	public boolean save(ChiTietHoaDon ct) {
		
		chiTietHoaDonRepository.save(ct);
		return true;
	}

	@Override
	public List<ChiTietHoaDon> findAllByHoaDon(HoaDon hoadon) {
		// TODO Auto-generated method stub
		return chiTietHoaDonRepository.findAllByHoaDon(hoadon);
	}

	@Override
	public void delete(ChiTietHoaDon cthd) {
		// TODO Auto-generated method stub
		chiTietHoaDonRepository.delete(cthd);
	}

	@Override
	public ChiTietHoaDon findByHoaDonAndSanPham(HoaDon hd, SanPham sp) {
		// TODO Auto-generated method stub
		return chiTietHoaDonRepository.findByHoaDonAndSanPham(hd, sp);
	}


	
}
