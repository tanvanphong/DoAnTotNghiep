package com.iuh.connection.services.Imls;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iuh.connection.model.DanhGia;
import com.iuh.connection.repository.DanhGiaRepository;
import com.iuh.connection.services.IDanhGiaService;


@Service
@Transactional
public class DanhGiaImpl implements IDanhGiaService{
	
	
	
	@Autowired
	DanhGiaRepository danhGiaRepository;

	@Override
	public void save(DanhGia danhgia) {
		danhGiaRepository.save(danhgia);
	}

}
