package fc.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import fc.db.BasketDTO;
import fc.db.MemberDTO;
import fc.db.MemberMapper;
import fc.db.QnaDTO;
import fc.db.QnaMapper;
import fc.db.ShippingDTO;
import jakarta.annotation.Resource;
@Controller
@RequestMapping("/ad_page/member/")
public class MemberController {
	
	@Resource
	MemberMapper mp;
	
	@Resource
	QnaMapper qna;

	@RequestMapping("rating")
	String rating(Model mm,MemberDTO dto) {
		List<MemberDTO> mainData = mp.mem(dto);
		System.out.println(mainData);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");
//		DecimalFormat df = new DecimalFormat("#.###"); 
		for(MemberDTO dd : mainData) {
			dd.setJoinDateStr(sdf.format(dd.getJoindate()));
//			String price = df.format(dto.getSellPrice());
//			System.out.println("-------"+dd);
		}
		
		mm.addAttribute("mainData",mainData);
		return "ad_page/member/rating";
	}
	
	@RequestMapping("ratingDetail")
	String ratingDetail(Model mm,MemberDTO dto) {
		List<MemberDTO> mainData = mp.mem(dto);
		System.out.println(mainData);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");
		for(MemberDTO dd : mainData) {
			dd.setJoinDateStr(sdf.format(dd.getJoindate()));
		}
		
		mm.addAttribute("mainData",mainData);
		return "ad_page/member/ratingDetail";
	}
	
	@RequestMapping("/member/rating")
	String kind(Model mm, MemberDTO dto) {
		
		List<MemberDTO> mainData = mp.mem(dto);
		mm.addAttribute("mainData", mainData);
		return "ad_page/member/rating";
	}
	
	@RequestMapping("ratingDetail/{id}")
	String id(Model mm,MemberDTO dto,QnaDTO qdto) {
		
		MemberDTO adt = mp.addetail(dto);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");
		adt.setJoinDateStr(sdf.format(adt.getJoindate()));
		List<ShippingDTO> orderlist = mp.orderlist(dto);
		List<BasketDTO> basket = mp.basket(dto);
		List<QnaDTO> qnaData = qna.adqlist2(qdto);
		
		mm.addAttribute("qnaData",qnaData);
		mm.addAttribute("basket",basket);
		mm.addAttribute("orderlist", orderlist);
		mm.addAttribute("mainData",adt);
		return "ad_page/member/ratingDetail";
	}
	@RequestMapping("/changerank")
	String changerank(Model mm,MemberDTO dto) {
		System.out.println(" changerank 진입");
		System.out.println("-------------------------"+dto);
		int cnt = mp.changerank(dto);
		System.out.println(cnt);
		System.out.println("카운트 구간 다음이냐?");
		
		if(cnt==1) {
			mm.addAttribute("mainData","id");
			mm.addAttribute("msg","등급이 변경되었습니다");
			mm.addAttribute("goUrl","rating");
		}else {
			mm.addAttribute("msg","등급 변경에 실패하였습니다");
			mm.addAttribute("goUrl","ratingDetail/"+dto.getId());
		}
		
		return "ad_page/product/alert";
	}
	
	@RequestMapping("qnainfo/{id}")
	String qnainfo(Model mm,MemberDTO dto) {
		MemberDTO adt = mp.addetail(dto);
		mm.addAttribute("mainData",adt);
		return "ad_page/member/qnainfo";
	}

	
}
