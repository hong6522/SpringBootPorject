package fc.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fc.db.AdQnaPData;
import fc.db.CenterPData;
import fc.db.IntegrateMapper;
import fc.db.MemberDTO;
import fc.db.NoticeDTO;
import fc.db.NoticeMapper;
import fc.db.ProductDTO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/ad_page/integrate/")
public class IntegrateController {
	
	IntegrateMapper im;
	
	@Resource
	NoticeMapper nm;
	
	@RequestMapping("")
	String list(Model mm,NoticeDTO dto, HttpServletRequest request) {
		System.out.println("integrate텥텥-----------");
		String msg="";

		new CenterPData(request);
		CenterPData pd = (CenterPData)request.getAttribute("pd");
		int qtotal = nm.ntotalcount();
		System.out.println(qtotal);
		pd.setTotal(qtotal);

		
		List<NoticeDTO> mainData =  nm.nlist(pd);
		
		System.out.println(mainData);
		
		mm.addAttribute("mainData", mainData);
		mm.addAttribute("pdata", pd);

		
		return "ad_page/integrate/list";
	}
	
	@RequestMapping(value = {"/detail/{no}"})
	String detail(Model mm, NoticeDTO dto) {
		
		
		mm.addAttribute("mainData", nm.adNoticeDetail(dto));

		
		return "ad_page/integrate/detail";
	}
	
	@GetMapping("/insert")
	String insert(NoticeDTO dto) {
		System.out.println("insert");
		return "ad_page/integrate/insertForm";
	}
	
	@PostMapping("/insert")
	String  insertRes(Model mm, NoticeDTO dto) {
		System.out.println("insertRes");
		nm.adNoticeInsert(dto);
		int maxNo = nm.maxNo();
		System.out.println(maxNo);
		mm.addAttribute("msg", "입력되었습니다.");
		mm.addAttribute("goUrl","/ad_page/integrate/detail/"+maxNo);
		System.out.println("InsertComplete:"+dto);
		return "ad_page/product/alert";
	}
	
	@GetMapping("/modify/{no}")
	String modify(Model mm, NoticeDTO dto) {
		System.out.println("수정폼");
		
		System.out.println(dto);
		mm.addAttribute("mainData", nm.adNoticeDetail(dto));
		return "ad_page/integrate/modifyForm";
	}
	
	@PostMapping("/modify/{no}")
	String modifyRes(Model mm,NoticeDTO dto) {
		System.out.println("수정dto"+dto);
		int cnt = nm.adNoticeModify(dto);
		
		String goUrl = "/modify/"+dto.getNo();
		String msg = "";
		
		System.out.println(cnt);
		
		if(cnt>0) {
			msg = "수정되었습니다.";
			goUrl = "/ad_page/integrate/detail/"+dto.getNo();
		}
		
		mm.addAttribute("msg", msg);
		mm.addAttribute("goUrl", goUrl);
		return "ad_page/product/alert";
	}
	
	@GetMapping("/delete/{no}")
	String delete(Model mm, NoticeDTO dto) {
		
		int cnt = nm.adNoticeDelete(dto);
		String msg = "";
		String goUrl = "/delete/"+dto.getNo();
		if(cnt>0) {
			msg = "삭제되었습니다.";
			goUrl = "/ad_page/integrate/";
		}
		System.out.println("삭제갯수:"+cnt);
		mm.addAttribute("msg", msg);
		mm.addAttribute("goUrl", goUrl);
		return "ad_page/product/alert";
		
	}
	
	
	
}