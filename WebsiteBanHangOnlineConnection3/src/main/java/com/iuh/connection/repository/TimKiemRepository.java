package com.iuh.connection.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.iuh.connection.model.NguoiDung;
import com.iuh.connection.model.TimKiem;

public interface TimKiemRepository extends JpaRepository<TimKiem,String>{
	
	TimKiem findTimKiemByKeyWord(String keyword);
	
	
	
	
	
	

}
