package com.iuh.HP_toystore.services.Imls;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iuh.HP_toystore.model.ChiTietHoaDon;
import com.iuh.HP_toystore.model.HoaDon;
import com.iuh.HP_toystore.repository.ChiTietHoaDonRepository;
import com.iuh.HP_toystore.services.IChiTietHoaDonService;

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


	
}
