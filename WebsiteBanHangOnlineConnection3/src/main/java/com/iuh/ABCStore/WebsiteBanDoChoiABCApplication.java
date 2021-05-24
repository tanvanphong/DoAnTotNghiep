package com.iuh.ABCStore;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.iuh.ABCStore.model.ChiTietHoaDon;
import com.iuh.ABCStore.model.HoaDon;
import com.iuh.ABCStore.services.IChiTietHoaDonService;
import com.iuh.ABCStore.services.IHoaDonService;

@SpringBootApplication
public class WebsiteBanDoChoiABCApplication {

//	@Autowired
//	private IHoaDonService ihoadon;
//
//	@Autowired
//	private IChiTietHoaDonService ichitiet;

	public static void main(String[] args) {
		SpringApplication.run(WebsiteBanDoChoiABCApplication.class, args);
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
