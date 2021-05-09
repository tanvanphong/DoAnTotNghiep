package com.iuh.HP_toystore.services.Imls;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iuh.HP_toystore.model.DanhGia;
import com.iuh.HP_toystore.repository.DanhGiaRepository;
import com.iuh.HP_toystore.services.IDanhGiaService;


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
