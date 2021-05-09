package com.iuh.HP_toystore.services.Imls;

import java.util.ArrayList;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.iuh.HP_toystore.model.LoaiTaiKhoan;
import com.iuh.HP_toystore.model.TaiKhoan;
import com.iuh.HP_toystore.repository.TaiKhoanRepository;



public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private TaiKhoanRepository taiKhoanRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		TaiKhoan taiKhoan = taiKhoanRepository.findByTenTaiKhoanEmail(username);
		System.err.println(taiKhoan);
		if (taiKhoan != null && taiKhoan.isTrangThai() == true) {
			return toUserDetails(taiKhoan);
			
		} else {
			throw new UsernameNotFoundException("Tai Khoan Khong Ton Tai");
		}

	}

	private UserDetails toUserDetails(TaiKhoan taiKhoan) {
		Set<LoaiTaiKhoan> rolelist = taiKhoan.getLoaiTaiKhoan();
		ArrayList<String> strs = new ArrayList<String>();
		for (LoaiTaiKhoan loaiTaiKhoan : rolelist) {
			strs.add(loaiTaiKhoan.getTenLoaiTaiKhoan());
		}
		String[] roles = strs.toArray(new String[0]);
		return User.withUsername(taiKhoan.getTenTaiKhoanEmail()).password(taiKhoan.getMatKhau()).roles(roles).build();

	}

}
