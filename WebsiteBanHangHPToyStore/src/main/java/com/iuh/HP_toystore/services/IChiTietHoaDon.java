package com.iuh.HP_toystore.services;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.iuh.HP_toystore.model.ChiTietHoaDon;
@Service
@Transactional
public interface IChiTietHoaDon {
	boolean save(ChiTietHoaDon ct);

}
