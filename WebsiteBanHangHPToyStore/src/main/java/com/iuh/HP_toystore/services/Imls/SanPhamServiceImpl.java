package com.iuh.HP_toystore.services.Imls;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.iuh.HP_toystore.model.DanhMuc;
import com.iuh.HP_toystore.model.NguoiDung;
import com.iuh.HP_toystore.model.SanPham;
import com.iuh.HP_toystore.repository.SanPhamRepository;
import com.iuh.HP_toystore.services.ISanPhamService;

@Service
@Transactional
public class SanPhamServiceImpl implements ISanPhamService {

	@Autowired
	private SanPhamRepository sanPhamRepository;

	@Override
	public Boolean saveSanPham(SanPham sanPham) {
		sanPhamRepository.save(sanPham);
		return true;
	}

	@Override
	public Boolean deleteSanPham(SanPham sanPham) {
		sanPhamRepository.delete(sanPham);
		return true;
	}

	@Override
	public Optional<SanPham> findSanPhamById(String sanPhamId) {
		return sanPhamRepository.findById(sanPhamId);
	}

	@Override
	public List<SanPham> findAllSanPham() {
		return sanPhamRepository.findAll();
	}

	@Override
	public Page<SanPham> findAllSanPhamsByNguoiDung(Pageable pageable, NguoiDung nguoiDung,String trangThai) {
		return sanPhamRepository.findAllSanPhamsByNguoiDungAndTrangThai(pageable, nguoiDung,trangThai);
	}


	@Override
	public SanPham findbyId(String id) {
		return sanPhamRepository.findById(id).get();
	}
	
	@Override
	public Page<SanPham> findAllSanPhamsByTrangThai(Pageable pageable,String trangThai){
		return sanPhamRepository.findAllSanPhamsByTrangThai(pageable, trangThai);
	}


	@Override
	public Page<SanPham> findAll(Pageable pageable) {
		return sanPhamRepository.findAll(pageable);
	}

	@Override
	public Page<SanPham> timKiem(Pageable pageable,String keyword) {
		return sanPhamRepository.timKiem(pageable,keyword);
	}
	
	@Override
	public Page<SanPham> timKIemMoi(Pageable pageable,String keyword){
		return sanPhamRepository.timKiemMoi(pageable,keyword);
	}
	
	@Override
	public Page<SanPham> findAllSanPhamsByDanhMuc(Pageable pageable,DanhMuc danhMuc){
		return sanPhamRepository.findAllSanPhamsByDanhMuc(pageable, danhMuc);
	}




	
}
