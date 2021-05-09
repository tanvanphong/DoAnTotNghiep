package com.iuh.connection.services;

import java.util.HashMap;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.iuh.connection.model.GioHang;
import com.iuh.connection.model.NguoiDung;


@Service
@Transactional
public interface IGioHangService {
	
    boolean thanhToanPayPal(HashMap<String, GioHang> gioHang,NguoiDung nguoiDung,String idLienHe);

    boolean thanhToanGiaohang(HashMap<String, GioHang> gioHang,NguoiDung nguoiDung,String idLienHe);
}
