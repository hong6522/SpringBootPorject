package fc.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fc.db.BoardDTO;
import fc.db.BoardMapper;
import fc.db.IntegrateMapper;
import fc.db.MemberDTO;
import fc.db.ProductDTO;
import jakarta.annotation.Resource;

@Controller
@RequestMapping("/ad_page/integrate/")
public class IntegrateController {
	
	IntegrateMapper im;
	
	@Resource
	BoardMapper bm;
	
	@RequestMapping("")
	String list(Model mm,BoardDTO dto) {
		System.out.println("integrate------------");
		
		List<BoardDTO> mainData =  bm.boardlist(dto);
		
		System.out.println(mainData);
		
		mm.addAttribute("mainData", mainData);
		
		
		return "ad_page/board/list";
	}
	
	@RequestMapping(value = {"/detail/{no}"})
	String detail(Model mm, BoardDTO dto) {
		
		
		mm.addAttribute("mainData", bm.detail(dto));

		
		return "ad_page/board/detail";
	}
	
	@GetMapping("/insert")
	String insert(BoardDTO dto) {
		System.out.println("insert");
		return "ad_page/board/insertForm";
	}
	
	@PostMapping("/insert")
	String  insertRes(Model mm, BoardDTO dto) {
		System.out.println("insertRes");
		bm.insert(dto);
		mm.addAttribute("msg", "입력되었습니다.");
		mm.addAttribute("goUrl","/ad_page/integrate/detail/"+dto.getNo());
		System.out.println("InsertComplete:"+dto);
		return "ad_page/product/alert";
	}
	
	@GetMapping("/modify/{no}")
	String modify(Model mm, BoardDTO dto) {
		System.out.println("수정폼");
		
		System.out.println(dto);
		mm.addAttribute("mainData", bm.detail(dto));
		return "ad_page/board/modifyForm";
	}
	
	@PostMapping("/modify/{no}")
	String modifyRes(Model mm,BoardDTO dto) {
		int cnt = bm.modify(dto);
		
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
	String delete(Model mm, BoardDTO dto) {
		
		int cnt = bm.delete(dto);
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
