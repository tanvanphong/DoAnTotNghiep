package com.iuh.HP_toystore.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.iuh.HP_toystore.model.NguoiDung;


@Service
@Transactional
public interface INguoiDungService {
	
	Optional<NguoiDung> findNguoiDungById(String nguoiDungid);
	
	NguoiDung saveNguoiDung(NguoiDung nguoiDung);

	NguoiDung updateNguoiDung(NguoiDung nguoiDung);

	boolean delete(NguoiDung nguoiDung);


}
