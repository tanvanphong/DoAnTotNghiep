package com.iuh.HP_toystore.services;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.iuh.HP_toystore.model.HoaDon;
import com.iuh.HP_toystore.model.NguoiDung;

@Service
@Transactional
public interface IHoaDonService {
	List<HoaDon> findAllByTrangThai(String trangThai);
	Page<HoaDon> findAllHoaDonsByTrangThai(Pageable pageable,String trangThai);
	Optional<HoaDon> findByHoaDonId(String idHoaDon);
	
	
	HoaDon saveHoaDon(HoaDon hd);
	Page<HoaDon> findAll(Pageable pageable);
	void deleteHoaDonById (String id);
	Page<HoaDon> timKiem(Pageable pageable,String keyword);
	Page<HoaDon> findAllHoaDonsByNguoiDungAndTrangThai(Pageable pageable, NguoiDung nguoiDung, String trangThai);
}
