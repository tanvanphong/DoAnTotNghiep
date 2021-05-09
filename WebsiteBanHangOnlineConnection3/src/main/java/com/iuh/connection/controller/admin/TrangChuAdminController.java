package com.iuh.connection.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iuh.connection.services.IHoaDonService;

@Controller
public class TrangChuAdminController {

	
	

	@RequestMapping("/quan-ly")
	public String trangChuAdmin() {
		
		return "/admin/trang-chu";
	}

	@RequestMapping("/index")
	public String index() {
		
		return "/admin/index";
	}
}
