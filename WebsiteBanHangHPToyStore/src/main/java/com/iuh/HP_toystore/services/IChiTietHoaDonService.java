package com.iuh.HP_toystore.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.iuh.HP_toystore.model.ChiTietHoaDon;
import com.iuh.HP_toystore.model.HoaDon;
@Service
@Transactional
public interface IChiTietHoaDonService {
	
	boolean save(ChiTietHoaDon ct);

	List<ChiTietHoaDon> findAllByHoaDon(HoaDon hoadon);
}
