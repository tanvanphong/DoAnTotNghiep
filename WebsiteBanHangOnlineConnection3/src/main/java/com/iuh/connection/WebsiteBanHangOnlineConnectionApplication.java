package com.iuh.connection;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.iuh.connection.model.ChiTietHoaDon;
import com.iuh.connection.model.HoaDon;
import com.iuh.connection.services.IChiTietHoaDonService;
import com.iuh.connection.services.IHoaDonService;

@SpringBootApplication
public class WebsiteBanHangOnlineConnectionApplication {

//	@Autowired
//	private IHoaDonService ihoadon;
//
//	@Autowired
//	private IChiTietHoaDonService ichitiet;

	public static void main(String[] args) {
		SpringApplication.run(WebsiteBanHangOnlineConnectionApplication.class, args);
	}

//	@Bean
//	InitializingBean sendDatabase() {
//		return () -> {
//			HoaDon hd = ihoadon.findByHoaDonId("402881b0733777fc0173378409da0001").orElse(null);
//
//			// List<ChiTietHoaDon> dssp = ichitiet.findAllByHoaDon(hd);
//			System.err.println(hd.getDssp());
//			hd.getDssp().remove(0);
////
////			
////
//			System.err.println(hd.getDssp());
//			hd = ihoadon.saveHoaDon(hd);
//			System.err.println(hd.getDssp());
//		};
//	}

}
