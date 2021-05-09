package com.iuh.HP_toystore.services;

import java.util.HashMap;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.iuh.HP_toystore.model.GioHang;
import com.iuh.HP_toystore.model.NguoiDung;


@Service
@Transactional
public interface IGioHangService {
	
    boolean thanhToanPayPal(HashMap<String, GioHang> gioHang,NguoiDung nguoiDung,String idLienHe);

    boolean thanhToanGiaohang(HashMap<String, GioHang> gioHang,NguoiDung nguoiDung,String idLienHe);
}
