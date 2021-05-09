package com.iuh.HP_toystore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.iuh.HP_toystore.model.NguoiDung;
import com.iuh.HP_toystore.model.TimKiem;

public interface TimKiemRepository extends JpaRepository<TimKiem,String>{
	
	TimKiem findTimKiemByKeyWord(String keyword);
	
	
	
	
	
	

}
