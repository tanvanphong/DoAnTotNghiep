package com.iuh.HP_toystore.services;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.iuh.HP_toystore.model.DanhGia;


@Service
@Transactional
public interface IDanhGiaService {

	void save (DanhGia danhgia);
}
