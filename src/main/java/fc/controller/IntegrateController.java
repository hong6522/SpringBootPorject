package fc.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import fc.db.IntegrateMapper;
import fc.db.MemberDTO;
import fc.db.NoticeDTO;
import fc.db.NoticeMapper;
import fc.db.ProductDTO;
import jakarta.annotation.Resource;

@Controller
@RequestMapping("/ad_page/integrate/")
public class IntegrateController {
	
	IntegrateMapper im;
	
	@Resource
	NoticeMapper nm;
	
	@RequestMapping("")
	String list(Model mm,NoticeDTO dto) {
		System.out.println("integrate------------");
		
		List<NoticeDTO> mainData =  nm.adNoticeList(dto);
		
		System.out.println(mainData);
		
		mm.addAttribute("mainData", mainData);
		
		
		return "ad_page/board/list";
	}
	
	@RequestMapping(value = {"/detail/{no}"})
	String detail(Model mm, NoticeDTO dto) {
		
		
		mm.addAttribute("mainData", nm.adNoticeDetail(dto));

		
		return "ad_page/board/detail";
	}
	
	@GetMapping("/insert")
	String insert(NoticeDTO dto) {
		System.out.println("insert");
		return "ad_page/board/insertForm";
	}
	
	@PostMapping("/insert")
	String  insertRes(Model mm, NoticeDTO dto) {
		System.out.println("insertRes");
		nm.adNoticeInsert(dto);
		mm.addAttribute("msg", "입력되었습니다.");
		mm.addAttribute("goUrl","/ad_page/integrate/detail/"+dto.getNo());
		System.out.println("InsertComplete:"+dto);
		return "ad_page/product/alert";
	}
	
	@GetMapping("/modify/{no}")
	String modify(Model mm, NoticeDTO dto) {
		System.out.println("수정폼");
		
		System.out.println(dto);
		mm.addAttribute("mainData", nm.adNoticeDetail(dto));
		return "ad_page/board/modifyForm";
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
