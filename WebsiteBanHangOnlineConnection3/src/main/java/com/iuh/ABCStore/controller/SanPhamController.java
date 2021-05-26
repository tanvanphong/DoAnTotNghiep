package com.iuh.ABCStore.controller;

import java.io.File;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import com.iuh.ABCStore.model.DanhGia;
import com.iuh.ABCStore.model.DanhMuc;
import com.iuh.ABCStore.model.NguoiDung;
import com.iuh.ABCStore.model.SanPham;
import com.iuh.ABCStore.model.TaiKhoan;
import com.iuh.ABCStore.repository.TaiKhoanRepository;
import com.iuh.ABCStore.services.IDanhGiaService;
import com.iuh.ABCStore.services.IDanhMucService;
import com.iuh.ABCStore.services.ISanPhamService;
import com.iuh.ABCStore.utils.AmazonClient;

@Controller
@ComponentScan("com.iuh.BanHangOnline.services")
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SanPhamController {
	

	@Autowired
	private TaiKhoanRepository taiKhoanRepository;

	@Autowired
	private ISanPhamService sanPhamRepository;

	@Autowired
	private IDanhMucService danhMucRepository;

	@Autowired
	private AmazonClient amazonClient;

	@Autowired
	private IDanhGiaService danhGiaService;

	@Value("${s3.bucketName}")
	private String bucketName;

	private static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir"));

//	@RequestMapping("/quan-ly/san-pham/them-san-pham-moi")
//	public String saveSanPham(Model model, @ModelAttribute("danhMuc") DanhMuc danhMuc,
//			@ModelAttribute("sanPham") SanPham sanPham) {
//
//		List<DanhMuc> danhMucs = danhMucRepository.findAllLoaiSanPham();
//		model.addAttribute("listDanhMuc", danhMucs);
//
//		sanPham.setDanhMuc(danhMuc);
//		return "/quan-ly/san-pham/them-san-pham-moi";
//	}
//
//	@PostMapping(value = "/quan-ly/san-pham/them-san-pham-moi")
//	public String xuLyThemSanPham(Model model, @ModelAttribute("sanPham") SanPham sanPham, HttpServletRequest request,
//			@RequestPart(value = "file") MultipartFile file, @RequestPart(value = "file1") MultipartFile file1,
//			@RequestPart(value = "file2") MultipartFile file2, @RequestPart(value = "file3") MultipartFile file3)
//			throws Exception {
//
//		TaiKhoan taiKhoan = taiKhoanRepository
//				.findByTenTaiKhoanEmail("admin@gmail.com");
//		NguoiDung khachHang = taiKhoan.getNguoiDung();
//		sanPham.setNguoiDung(khachHang);
//		sanPham.setTrangThai("Đã Xác Nhận");
//		sanPham.setNgayTao(LocalDate.now());
//
//		
////		File filecv = this.amazonClient.convertMultiPartToFile(file);
////		String fileName = this.amazonClient.generateFileName(file);
////		String fileUrl = "http://" + bucketName + ".s3.us-east-2.amazonaws.com/" + fileName;
////		this.amazonClient.uploadFileTos3bucket(fileName, filecv);
////		filecv.delete();
////
////		File filecv1 = this.amazonClient.convertMultiPartToFile(file1);
////		String fileName1 = this.amazonClient.generateFileName(file1);
////		String fileUrl1 = "http://" + bucketName + ".s3.us-east-2.amazonaws.com/" + fileName1;
////		this.amazonClient.uploadFileTos3bucket(fileName1, filecv1);
////		filecv1.delete();
////
////		File filecv2 = this.amazonClient.convertMultiPartToFile(file2);
////		String fileName2 = this.amazonClient.generateFileName(file2);
////		String fileUrl2 = "http://" + bucketName + ".s3.us-east-2.amazonaws.com/" + fileName2;
////		this.amazonClient.uploadFileTos3bucket(fileName2, filecv2);
////		filecv2.delete();
////
////		File filecv3 = this.amazonClient.convertMultiPartToFile(file3);
////		String fileName3 = this.amazonClient.generateFileName(file3);
////		String fileUrl3 = "http://" + bucketName + ".s3.us-east-2.amazonaws.com/" + fileName3;
////		this.amazonClient.uploadFileTos3bucket(fileName3, filecv3);
////		filecv3.delete();
////
////		sanPham.setHinhAnh(fileUrl);
////		sanPham.setHinhAnh1(fileUrl1);
////		sanPham.setHinhAnh2(fileUrl2);
////		sanPham.setHinhAnh3(fileUrl3);
//
////		System.err.println(sanPham);
//
//		sanPhamRepository.saveSanPham(sanPham);
//		model.addAttribute("tx", "Thêm sản phẩm mới thành công, " + "Tiếp tục thêm sản phẩm mới");
//
//		return "redirect:/quan-ly/san-pham/them-san-pham-moi";
//
//	}
//
//	@RequestMapping(value = "/ban-hang/sua-san-pham/{id}")
//	public String updateSanPham(Model model, @ModelAttribute("sanPham") SanPham sanPham,
//			@ModelAttribute("danhMuc") DanhMuc danhMuc, @PathVariable(name = "id") String id) {
//
//		sanPham = sanPhamRepository.findSanPhamById(id).get();
//
//		List<DanhMuc> danhMucs = danhMucRepository.findAllLoaiSanPham();
//		System.out.println();
//		model.addAttribute("listDanhMuc", danhMucs);
//
//		model.addAttribute("sanPham", sanPham);
//
//		return "sua-san-pham";
//	}
	@RequestMapping("/quan-ly/san-pham/them-san-pham-moi")
	public String saveSanPham(Model model, @ModelAttribute("danhMuc") DanhMuc danhMuc,
			@ModelAttribute("sanPham") SanPham sanPham) {

		List<DanhMuc> danhMucs = danhMucRepository.findAllLoaiSanPham();
		model.addAttribute("listDanhMuc", danhMucs);

		sanPham.setDanhMuc(danhMuc);
		return "them-san-pham-moi-2";
	}

	@PostMapping(value = "/quan-ly/san-pham/them-san-pham-moi")
	public String xuLyThemSanPham(Model model, @ModelAttribute("sanPham") SanPham sanPham, HttpServletRequest request,
			@RequestPart(value = "file") MultipartFile file, @RequestPart(value = "file1") MultipartFile file1,
			@RequestPart(value = "file2") MultipartFile file2, @RequestPart(value = "file3") MultipartFile file3)
			throws Exception {

		TaiKhoan taiKhoan = taiKhoanRepository
				.findByTenTaiKhoanEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		NguoiDung khachHang = taiKhoan.getNguoiDung();
		sanPham.setNguoiDung(khachHang);
		sanPham.setTrangThai("Đã Xác Nhận");
		sanPham.setNgayTao(LocalDate.now());
		
		Path staticPath=Paths.get("static");
		Path imagePath=Paths.get("images");
		if(!Files.exists(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath))) {
            Files.createDirectories(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath));
        }
		 Path anhdaidien = CURRENT_FOLDER.resolve(staticPath)
	                .resolve(imagePath).resolve(file.getOriginalFilename());
	        try (OutputStream os = Files.newOutputStream(anhdaidien)) {
	            os.write(file.getBytes());
	        }
	        Path anhPhu1 = CURRENT_FOLDER.resolve(staticPath)
	                .resolve(imagePath).resolve(file1.getOriginalFilename());
	        try (OutputStream os = Files.newOutputStream(anhPhu1)) {
	            os.write(file.getBytes());
	        }
	        Path anhPhu2 = CURRENT_FOLDER.resolve(staticPath)
	                .resolve(imagePath).resolve(file2.getOriginalFilename());
	        try (OutputStream os = Files.newOutputStream(anhPhu2)) {
	            os.write(file.getBytes());
	        }
	        Path anhPhu3 = CURRENT_FOLDER.resolve(staticPath)
	                .resolve(imagePath).resolve(file3.getOriginalFilename());
	        try (OutputStream os = Files.newOutputStream(anhPhu3)) {
	            os.write(file.getBytes());
	        }
//		File filecv = this.amazonClient.convertMultiPartToFile(file);
//		String fileName = this.amazonClient.generateFileName(file);
//		String fileUrl = "http://" + bucketName + ".s3.us-east-2.amazonaws.com/" + fileName;
//		this.amazonClient.uploadFileTos3bucket(fileName, filecv);
//		filecv.delete();
	     String url1="http://192.168.1.3:8090/";
		sanPham.setHinhAnh(url1+imagePath.resolve(( file).getOriginalFilename()).toString());
		sanPham.setHinhAnh1(url1+imagePath.resolve(( file1).getOriginalFilename()).toString());
		sanPham.setHinhAnh2(url1+imagePath.resolve(( file2).getOriginalFilename()).toString());
		sanPham.setHinhAnh3(url1+imagePath.resolve(( file3).getOriginalFilename()).toString());
//		sanPham.setHinhAnh1(fileUrl1);
//		sanPham.setHinhAnh2(fileUrl2);
//		sanPham.setHinhAnh3(fileUrl3);

//		System.err.println(sanP ham);

		sanPhamRepository.saveSanPham(sanPham);
		model.addAttribute("tx", "Thêm sản phẩm mới thành công, " + "Tiếp tục thêm sản phẩm mới");

		return "redirect:/quan-ly/san-pham/them-san-pham-moi";

	}

	@RequestMapping(value = "/ban-hang/sua-san-pham/{id}")
	public String updateSanPham(Model model, @ModelAttribute("sanPham") SanPham sanPham,
			@ModelAttribute("danhMuc") DanhMuc danhMuc, @PathVariable(name = "id") String id) {

		sanPham = sanPhamRepository.findSanPhamById(id).get();

		List<DanhMuc> danhMucs = danhMucRepository.findAllLoaiSanPham();
		System.out.println();
		model.addAttribute("listDanhMuc", danhMucs);

		model.addAttribute("sanPham", sanPham);

		return "sua-san-pham";
	}

	@RequestMapping(value = "/ban-hang/sua-san-pham/{id}", method = RequestMethod.POST)
	public String xuLyUpdateSanPham(Model model, @ModelAttribute("sanPham") SanPham sanPham,
			@PathVariable(name = "id") String id, @RequestPart(value = "file") MultipartFile file,
			@RequestPart(value = "file1") MultipartFile file1, @RequestPart(value = "file2") MultipartFile file2,
			@RequestPart(value = "file3") MultipartFile file3) throws Exception {

		TaiKhoan taiKhoan = taiKhoanRepository
				.findByTenTaiKhoanEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		NguoiDung khachHang = new NguoiDung("4028818d79541fa60179542a1dbe0000");

		sanPham.setId(id);
	
		sanPham.setNguoiDung(khachHang);
		
		sanPham = sanPhamRepository.findbyId(id);
				
		Path staticPath=Paths.get("static");
		Path imagePath=Paths.get("images");
		if(!Files.exists(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath))) {
            Files.createDirectories(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath));
        }
		 Path anhdaidien = CURRENT_FOLDER.resolve(staticPath)
	                .resolve(imagePath).resolve(file.getOriginalFilename());
	        try (OutputStream os = Files.newOutputStream(anhdaidien)) {
	            os.write(file.getBytes());
	        }
	        Path anhPhu1 = CURRENT_FOLDER.resolve(staticPath)
	                .resolve(imagePath).resolve(file1.getOriginalFilename());
	        try (OutputStream os = Files.newOutputStream(anhPhu1)) {
	            os.write(file.getBytes());
	        }
	        Path anhPhu2 = CURRENT_FOLDER.resolve(staticPath)
	                .resolve(imagePath).resolve(file2.getOriginalFilename());
	        try (OutputStream os = Files.newOutputStream(anhPhu2)) {
	            os.write(file.getBytes());
	        }
	        Path anhPhu3 = CURRENT_FOLDER.resolve(staticPath)
	                .resolve(imagePath).resolve(file3.getOriginalFilename());
	        try (OutputStream os = Files.newOutputStream(anhPhu3)) {
	            os.write(file.getBytes());
	        }
//		File filecv = this.amazonClient.convertMultiPartToFile(file);
//		String fileName = this.amazonClient.generateFileName(file);
//		String fileUrl = "http://" + bucketName + ".s3.us-east-2.amazonaws.com/" + fileName;
//		this.amazonClient.uploadFileTos3bucket(fileName, filecv);
//		filecv.delete();

		sanPham.setHinhAnh(imagePath.resolve(( file).getOriginalFilename()).toString());
		sanPham.setHinhAnh1(imagePath.resolve(( file1).getOriginalFilename()).toString());
		sanPham.setHinhAnh2(imagePath.resolve(( file2).getOriginalFilename()).toString());
		sanPham.setHinhAnh3(imagePath.resolve(( file3).getOriginalFilename()).toString());
		

		sanPhamRepository.saveSanPham(sanPham);
		System.out.println(sanPham);
		model.addAttribute("tx", "Thêm sản phẩm mới thành công, " + "Tiếp tục thêm sản phẩm mới");
		return "redirect:/ban-hang/san-pham-cua-ban";
	}
	
	@GetMapping("/ban-hang/xoa-san-pham/{id}")
	public String xoaSanPham(@PathVariable(name = "id") String id) {

		SanPham sp = sanPhamRepository.findSanPhamById(id).get();
//		if (sanPhamRepository.deleteSanPham(sp) == true) {
//
//			this.amazonClient.deleteFileFromS3Bucket(sp.getHinhAnh());
//			this.amazonClient.deleteFileFromS3Bucket(sp.getHinhAnh1());
//			this.amazonClient.deleteFileFromS3Bucket(sp.getHinhAnh2());
//			this.amazonClient.deleteFileFromS3Bucket(sp.getHinhAnh3());
//
//		}

		return "redirect:/ban-hang/san-pham-cua-ban";
	}

	@GetMapping("/ban-hang/san-pham-cho-kiem-duyet")
	public String listSanPhamChoKiemDuyet(Model model,
			@RequestParam(name = "page", required = false, defaultValue = "1") Optional<Integer> page,
			@RequestParam(name = "size", required = true, defaultValue = "12") Integer size,
			@RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort) {
		Sort sortable = null;
		if (sort.equals("ASC")) {
			sortable = Sort.by("id").ascending();
		}
		if (sort.equals("DESC")) {
			sortable = Sort.by("id").descending();
		}
		int currentPage = page.orElse(1);
		Pageable pageable = PageRequest.of(currentPage - 1, size, sortable);

		TaiKhoan taiKhoan = taiKhoanRepository
				.findByTenTaiKhoanEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		NguoiDung khachHang = taiKhoan.getNguoiDung();

		Page<SanPham> pageSanPham = sanPhamRepository.findAllSanPhamsByNguoiDung(pageable, khachHang, "Chưa Xác Nhận");
		int totalPage = pageSanPham.getTotalPages();
		if (totalPage > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}

		model.addAttribute("listSanPham", sanPhamRepository.findAllSanPhamsByNguoiDung(pageable, khachHang, "Chưa Xác Nhận"));
		// so luong mua
		return "danh-sach-san-pham-cho-kiem-duyet";
	}
	
	@GetMapping("/ban-hang/san-pham-cua-ban")
	public String listSanPham(Model model,
			@RequestParam(name = "page", required = false, defaultValue = "1") Optional<Integer> page,
			@RequestParam(name = "size", required = true, defaultValue = "12") Integer size,
			@RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort) {
		Sort sortable = null;
		if (sort.equals("ASC")) {
			sortable = Sort.by("id").ascending();
		}
		if (sort.equals("DESC")) {
			sortable = Sort.by("id").descending();
		}
		int currentPage = page.orElse(1);
		Pageable pageable = PageRequest.of(currentPage - 1, size, sortable);

		TaiKhoan taiKhoan = taiKhoanRepository
				.findByTenTaiKhoanEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		NguoiDung khachHang = taiKhoan.getNguoiDung();

		Page<SanPham> pageSanPham = sanPhamRepository.findAllSanPhamsByNguoiDung(pageable, khachHang, "Đã Xác Nhận");
		int totalPage = pageSanPham.getTotalPages();
		if (totalPage > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}

		model.addAttribute("listSanPham", sanPhamRepository.findAllSanPhamsByNguoiDung(pageable, khachHang, "Đã Xác Nhận"));
		// so luong mua
		return "danh-sach-san-pham-cua-ban";
	}


	@RequestMapping(value = "/{id}")
	public String getChitietSanPham(Model model, @PathVariable(name = "id") String id) {
		Optional<SanPham> sp = sanPhamRepository.findSanPhamById(id);
		sp.isPresent();
		DanhGia danhgia = new DanhGia();
		danhgia.setSanPham(sp.get());

		if (sp.isPresent()) {
			model.addAttribute("sanpham", sp.get());
			model.addAttribute("danhgianew", danhgia);
			return "chi-tiet-san-pham";
		}
		return "trang-chu";
	}


	@RequestMapping(value = "/san-pham-danh-gia/{id}", method = RequestMethod.POST)
	public String danhGiaSP(@ModelAttribute("danhgianew") DanhGia danhGia) {
		Optional<TaiKhoan> tk = Optional.ofNullable(taiKhoanRepository
				.findByTenTaiKhoanEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
		if (tk.isPresent()) {
			DanhGia danhgia1 = new DanhGia();
			danhgia1.setNoiDung(danhGia.getNoiDung());
			danhgia1.setNguoiDung(tk.get().getNguoiDung());
			danhgia1.setNgayDanhGia(LocalDateTime.now());
			danhgia1.setSanPham(danhGia.getSanPham());
			danhGiaService.save(danhgia1);
		}
		return "redirect:/san-pham/{id}";
	}
	
	@GetMapping("/chi-tiet-san-pham-ban-hang/{id}")
	public ResponseEntity<Object> chiTietSanPhamModal(Model model,@PathVariable("id") String id){
		try {
			SanPham sanPham = sanPhamRepository.findbyId(id);
			return new ResponseEntity<Object>(sanPham,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Null",HttpStatus.BAD_REQUEST);
		}
	} 


}
