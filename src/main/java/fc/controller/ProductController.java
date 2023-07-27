package fc.controller;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import fc.db.AdQnaPData;
import fc.db.ListPData;
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
    String list(Model mm,ProductDTO dto, HttpServletRequest request) {
//        System.out.println("------------"+dto);
        System.out.println("prolist");


        new AdQnaPData(request); // request객체에 "pd" 어트리뷰트 세팅(nowPage가 있을경우와 없을경우 확인을 위해, 처음 진입시 nowPage없음)
        AdQnaPData pd = (AdQnaPData)request.getAttribute("pd"); // 리퀘스트로 받은 pd객체 대입
        int qtotal = pm.protalcount(); // protalcount()메서드에서 테이블에서 총 로우 갯수 확인
        System.out.println("tt1");

        pd.setTotal(qtotal); // 페이지 데이터에 총 로우 갯수 세팅

        System.out.println("tt2");
        System.out.println(dto);

        List<ProductDTO> mainData = pm.propagelist(pd, dto); // 페이지데이터와 dto객체로 시작페이지 끝페이지 확인
//        List<ProductDTO> mainData = pm.pro(dto);

        SimpleDateFormat sdf = new SimpleDateFormat("yy/ MM/ dd");
//        DecimalFormat df = new DecimalFormat("#.###"); 

        for(ProductDTO dd : mainData) {
            dd.setReg_dateStr(sdf.format(dd.getReg_date()));
//            String price = df.format(dto.getSellPrice());
//            System.out.println("-------"+dd);
        }

        mm.addAttribute("pdata", pd);
        mm.addAttribute("mainData",mainData);

//        System.out.println("$#%^&(&^%$#@$%^-------------------------"+mainData);
        return "ad_page/product/list";
    }
	
	@GetMapping("/modify/{proName}")
    String detail(Model mm, ProductDTO dto) {
        ProductDTO pd = pm.detail(dto);
        String[] colorArr = pd.getColor().split(",");
        String [] colorName = pd.getColorName().split(",");

        System.out.println(Arrays.toString(colorArr));
        System.out.println(Arrays.toString(colorName));


        Map<String,String> colors = new LinkedHashMap<>();

        for(int i = 0; i < colorArr.length; i++ ) {
            colors.put(colorArr[i], colorName[i]);
        }


        System.out.println(colors);



        mm.addAttribute("colors",colors);
        mm.addAttribute("productDTO",pd);
//        System.out.println(pm.detail(dto)+"-----------------"+dto);

        return "ad_page/product/modify";
    }

	
	@PostMapping("/modify/{proName}")
	String modify(Model mm,ProductDTO dto,HttpServletRequest request,MultipartFile pf1Str,MultipartFile pf2Str,MultipartFile pf3Str) {
		
		String goUrl = "/modify/"+dto.getProName();
		String msg = "";
		
		System.out.println("수정dto:"+dto+",cnt");

		if(dto.getPf1Str()!=null) {
		dto.setPf1(dto.getPf1Str().getOriginalFilename());
		}
		if(dto.getPf2Str()!=null) {
		dto.setPf2(dto.getPf2Str().getOriginalFilename());
		}
		if(dto.getPf3Str()!=null) {
		dto.setPf3(dto.getPf3Str().getOriginalFilename());
		}
		int cnt = pm.modify(dto);

		
		if(cnt>0) {
			if(dto.getPf1Str()!=null) {
			fileSave(pf1Str,request);
			}
			if(dto.getPf2Str()!=null) {
			fileSave(pf2Str,request);
			}
			if(dto.getPf3Str()!=null) {
			fileSave(pf3Str,request);
			}
			msg = "수정되었습니다.";	
			goUrl = "/ad_page/product/list";
			
		}
		
		mm.addAttribute("msg", msg);
		mm.addAttribute("goUrl", goUrl);
		
		return "ad_page/product/alert";
	}
	
	
	
	@GetMapping("add")
	String addForm(ProductDTO dto) {
		return "ad_page/product/add";
	}
	
	@PostMapping("add")
	String addComplete(Model mm,ProductDTO dto,HttpServletRequest request,MultipartFile pf1Str,MultipartFile pf2Str,MultipartFile pf3Str) {
		System.out.println(dto);
//	
		dto.setPf1(dto.getPf1Str().getOriginalFilename());
//		dto.setPf2(dto.getPf2Str().getOriginalFilename());
//		dto.setPf3(dto.getPf3Str().getOriginalFilename());
////		
		int res = pm.insert(dto);
		
		if(res ==1) {
			mm.addAttribute("msg","상품이 추가되었습니다");
			mm.addAttribute("goUrl","list");
			fileSave(pf1Str,request);
//			fileSave(pf2Str,request);
//			fileSave(pf3Str,request);
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
	@PostMapping("FileDelete/{fileName}")
	String FileDelete(Model mm,ProductDTO dto,@PathVariable String fileName) {
//		System.out.println("---************---"+fileName+"-----######------"+dto);
		
		if(fileName.equals("pf1")) {
			dto.setPf1(null);
			dto.setPf1Str(null);
		}
		if(fileName.equals("pf2")) {
			dto.setPf2(null);
			dto.setPf2Str(null);
		}
		if(fileName.equals("pf3")) {
			dto.setPf3(null);
			dto.setPf3Str(null);
		}
//		System.out.println(dto);
		int cnt = pm.modify(dto);
		mm.addAttribute("msg", "삭제완료");
		mm.addAttribute("goUrl", "../modify/"+dto.getProName());
		
		return "ad_page/product/alert";		
	}
	
	@RequestMapping("inventory")
	String inventory() {
		return "ad_page/product/inventory";
	}
	@PostMapping("PrdDelete/{prdName}")
    String PrdDelete(Model mm,ProductDTO dto,@PathVariable String prdName) {
//        System.out.println("---**---"+fileName+"-----######------"+dto);


        System.out.println(dto);
        int cnt = pm.prdDelete(dto);



        mm.addAttribute("msg", "삭제완료");
        mm.addAttribute("goUrl", "/ad_page/product/list");

        return "ad_page/product/alert";
    }
}