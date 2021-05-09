package com.iuh.connection.services;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.iuh.connection.model.DanhGia;


@Service
@Transactional
public interface IDanhGiaService {

	void save (DanhGia danhgia);
}
