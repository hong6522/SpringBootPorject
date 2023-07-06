package fc.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import fc.db.MemberDTO;
import fc.db.MemberMapper;
import jakarta.annotation.Resource;
@Controller
@RequestMapping("/ad_page/member/")
public class MemberController {
	
	@Resource
	MemberMapper mp;
	
	@RequestMapping("rating")
	String rating(Model mm,MemberDTO dto) {
		List<MemberDTO> mainData = mp.mem(dto);
		System.out.println(mainData);
		mm.addAttribute("mainData",mainData);
		return "ad_page/member/rating";
	}
	
	@RequestMapping("ratingDetail")
	String ratingDetail(Model mm,MemberDTO dto) {
		List<MemberDTO> mainData = mp.mem(dto);
		System.out.println(mainData);
		mm.addAttribute("mainData",mainData);
		return "ad_page/member/ratingDetail";
	}
	
	@RequestMapping("/member/rating")
	String kind(Model mm, MemberDTO dto) {
		
		List<MemberDTO> mainData = mp.mem(dto);
		mm.addAttribute("mainData", mainData);
		return "ad_page/member/rating";
	}
	
}
