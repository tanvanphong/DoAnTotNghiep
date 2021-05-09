package com.iuh.connection.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.iuh.connection.model.NguoiDung;


@Service
@Transactional
public interface INguoiDungService {
	
	Optional<NguoiDung> findNguoiDungById(String nguoiDungid);
	
	NguoiDung saveNguoiDung(NguoiDung nguoiDung);

	NguoiDung updateNguoiDung(NguoiDung nguoiDung);

	boolean delete(NguoiDung nguoiDung);


}
