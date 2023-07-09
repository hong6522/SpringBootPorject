package fc.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import fc.db.MemberDTO;
import fc.db.ProductDTO;
import fc.db.ProductMapper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/ad_page/product/")
public class ProductController {
	
	@Resource
	ProductMapper pm;
	
	@RequestMapping("list")
	String list(Model mm,ProductDTO dto) {
		System.out.println("------------"+dto);
				
		List<ProductDTO> mainData = pm.pro(dto);
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초 ");
		for(ProductDTO dd : mainData) {
			dd.setReg_dateStr(sdf.format(dd.getReg_date()));
		}
		mm.addAttribute("mainData",mainData);
		
		//System.out.println(mainData.get(0));
		return "ad_page/product/list";
	}
	
	
	@GetMapping("add")
	String addForm(ProductDTO dto) {
		return "ad_page/product/add";
	}
	
	@PostMapping("add")
	String addComplete(Model mm,ProductDTO dto,HttpServletRequest request,MultipartFile pf1Str,MultipartFile pf2Str,MultipartFile pf3Str) {
		System.out.println(dto.getColor());
		
		dto.setPf1(dto.getPf1Str().getOriginalFilename());
		dto.setPf2(dto.getPf2Str().getOriginalFilename());
		dto.setPf3(dto.getPf3Str().getOriginalFilename());
		
		int res = pm.insert(dto);
		
		if(res ==1) {
			mm.addAttribute("msg","상품이 추가되었습니다");
			mm.addAttribute("goUrl","list");
			fileSave(pf1Str,request);
			fileSave(pf2Str,request);
			fileSave(pf3Str,request);
		}else {
			mm.addAttribute("msg","추가에 실패하였습니다");
			mm.addAttribute("goUrl","ad_page/product/add");
		}
		
		return "ad_page/product/alert";
	}
	
	void fileSave(MultipartFile ff,	HttpServletRequest request) {
		String path = request.getServletContext().getRealPath("productImg");
		//System.out.println(path);
		try {
			FileOutputStream fos = new FileOutputStream(path+"/"+ff.getOriginalFilename());
			fos.write(ff.getBytes());
			fos.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	
	@RequestMapping("inventory")
	String inventory() {
		return "ad_page/product/inventory";
	}
}