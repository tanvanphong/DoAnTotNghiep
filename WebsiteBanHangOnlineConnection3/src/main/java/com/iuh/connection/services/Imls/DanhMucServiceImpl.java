package com.iuh.connection.services.Imls;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iuh.connection.model.DanhMuc;
import com.iuh.connection.repository.DanhMucRepository;
import com.iuh.connection.services.IDanhMucService;


@Service
@Transactional
public class DanhMucServiceImpl implements IDanhMucService {

	@Autowired
	private DanhMucRepository loaisanPhamRepository;

	@Override
	public List<DanhMuc> findAllLoaiSanPham() {
		return loaisanPhamRepository.findAll();
	}

	@Override
	public List<DanhMuc> findAllNguoiDung(String id) {
		// TODO Auto-generated method stub
		return loaisanPhamRepository.findAllNguoiDung(id);
	}

}
