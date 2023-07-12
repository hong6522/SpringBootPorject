package fc.control;


import java.util.List;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fc.db.BasketDTO;
import fc.db.BasketMapper;
import fc.db.MemberDTO;
import fc.db.MemberMapper;
import fc.db.ShippingDTO;
import fc.db.ShippingMapper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("fc_mem/")
public class MemController {
	
	@Resource
	MemberMapper mp;
	
	@Resource
	BasketMapper bm;
	
	@Resource
	ShippingMapper sm;
	
	@RequestMapping("logOut")
	String session_Out(HttpSession session) {
		
		session.invalidate();
		
		return "/fc_mem/login";
	}

	@GetMapping("login")
	String login() {
		
		return "/fc_mem/login";
	}
	
	@PostMapping("login")
	String login_complate(Model mm,HttpSession session,MemberDTO dto) {
		System.out.println(dto);
		MemberDTO mdto = mp.login(dto);
		System.out.println(mdto);
		
		if(mdto!=null) {
			session.setAttribute("type", "nomal");
			session.setAttribute("address", mdto.getAddress1());
			session.setAttribute("id", mdto.getId());
			
			mm.addAttribute("msg", mdto.getName()+" 님 환영합니다.");
			mm.addAttribute("goUrl", "/mainPage/best");
		}
		else {
			mm.addAttribute("msg", "아이디 혹은 비밀번호 오류입니다.");
			mm.addAttribute("goUrl", "/fc_mem/login");
		}
		return "alert";
	}
	
	@GetMapping("join")
	String mem_join() {
		
		return "/fc_mem/join";
	}
	
	@PostMapping("join")
	String mem_join_com(Model mm,MemberDTO dto) {
		
		int cnt = mp.join(dto);
		System.out.println(cnt);
		if(cnt==1) {
		mm.addAttribute("msg", "회원가입 완료");
		mm.addAttribute("goUrl", "/fc_mem/login");
		return "alert";
		}
		else {
			mm.addAttribute("msg", "회원가입 실패");
			mm.addAttribute("goUrl", "/fc_mem/join");
			return "alert";
		}
	}
	
	@RequestMapping("modifyForm")
	String modifyForm() {
		
		return "/fc_mem/modifyForm";
	}
	
	@GetMapping("modifyGo")
	String modifyGo_get(Model mm, MemberDTO dto , HttpSession session) {
		System.out.println("go페이지 진입"+dto);
		dto.setId((String)session.getAttribute("id"));
		System.out.println("세션 id 값 삽입 후"+dto);
		MemberDTO mdto = mp.login(dto);
		if(mdto!=null) {
		
			mm.addAttribute("mainData", mdto);
		}
		
		
		return "/fc_mem/modifyGo";
	}
	
	@PostMapping("modifyGo")
	String modifyGo_post(Model mm, MemberDTO dto) {
		
		System.out.println("포스트dto"+dto);
		
		int cnt = mp.mem_modify(dto);
		if(cnt==1) {
			mm.addAttribute("msg","수정 완료");
			mm.addAttribute("goUrl" , "/fc_mem/myPage");
		}
		else {
			mm.addAttribute("msg","수정 실패");
			mm.addAttribute("goUrl" , "/fc_mem/modifyGo");
		}
		return "/alert";
	}
	
	@RequestMapping("myPage")
	String myPage(Model mm,HttpSession session ,MemberDTO dto) {
		if(session.getAttribute("id")!=null) {
		dto.setId((String)session.getAttribute("id"));
		MemberDTO member = mp.myPage(dto);
		mm.addAttribute("mainData", member);
		return "/fc_mem/myPage";
		}
		else {
			mm.addAttribute("msg", "로그인이 필요한 서비스입니다.");
			mm.addAttribute("goUrl", "/fc_mem/login");
			return "alert";
		}
	}
	
	@RequestMapping("myBasket")
	String myBasket(Model mm,HttpSession session) {
		MemberDTO mDTO;
		if(session.getAttribute("id")!=null) {
			mDTO = new MemberDTO();
			mDTO.setId((String)session.getAttribute("id"));
			MemberDTO member = mp.myPage(mDTO);
			mm.addAttribute("mainData", bm.basket_list(member));
			
		}
		
		return "/fc_mem/myBasket";
	}
	
	@RequestMapping("basketDelete")
	String basketDelete(Model mm,HttpSession session , BasketDTO dto) {
		dto.setId((String)session.getAttribute("id"));
		System.out.println(dto);
		int cnt = bm.basketDelete(dto);
		System.out.println(cnt);
		MemberDTO mDTO;
		
		if(session.getAttribute("id")!=null) {
			mDTO = new MemberDTO();
			mDTO.setId((String)session.getAttribute("id"));
			MemberDTO member = mp.myPage(mDTO);
			mm.addAttribute("mainData", bm.basket_list(member));
			
		}
		if(cnt==1) {
			mm.addAttribute("msg", "삭제 완료");
			mm.addAttribute("goUrl", "/fc_mem/myBasket");
		}
		else {
			mm.addAttribute("msg", "삭제 실패");
			mm.addAttribute("goUrl", "/fc_mem/myBasket");
		}
		return "/alert";
	}
	
	@RequestMapping("basketModify")
	String basketModify(Model mm,HttpSession session , BasketDTO dto) {
		dto.setId((String)session.getAttribute("id"));
		System.out.println(dto);
		int cnt = bm.basket_modify(dto);
		System.out.println(cnt);
		MemberDTO mDTO;
//		
		if(session.getAttribute("id")!=null) {
			mDTO = new MemberDTO();
			mDTO.setId((String)session.getAttribute("id"));
			MemberDTO member = mp.myPage(mDTO);
			mm.addAttribute("mainData", bm.basket_list(member));
//			
		}
		if(cnt==1) {
			mm.addAttribute("msg", "수정 완료");
			mm.addAttribute("goUrl", "/fc_mem/myBasket");
		}
		else {
			mm.addAttribute("msg", "수정 실패");
			mm.addAttribute("goUrl", "/fc_mem/myBasket");
		}
//			mm.addAttribute("msg", "테스트");
//			mm.addAttribute("goUrl", "/fc_mem/myBasket");
		
		return "/alert" ;
	}
	
	@RequestMapping("goOrder")
	String goOrder(Model mm,HttpSession session , ShippingDTO dto) {
		String str = dto.getUid();
		
		if(session.getAttribute("id")!=null) {
			System.out.println("들어옴?");
			dto.setOrder_place((String)session.getAttribute("address"));
			dto.setOrder_ID((String)session.getAttribute("id"));
			dto.setUid(str+Math.random()*100);
			System.out.println(dto);
			sm.Order_insert(dto);
			mm.addAttribute("msg", "결제완료");
			mm.addAttribute("goUrl", "/fc_mem/history");
		}
		
		
		return "/alert";
		
	}
	
	@RequestMapping("listGoOrder")
	String listGoOrder(Model mm,HttpSession session , 
			@RequestParam("pno") List<Integer> pno,
			@RequestParam("uid") String uid) {
		System.out.println("listOrder:"+uid);
		
		for (Integer no : pno) {
			BasketDTO dto = bm.order_move(no);
			dto.setAddress((String)session.getAttribute("address"));
			dto.setUid(uid);
			sm.basOrder_insert(dto);
			dto.setId((String)session.getAttribute("id"));
			bm.basketDelete(dto);
		}
		
			mm.addAttribute("msg", "결제성공");
			mm.addAttribute("goUrl", "/fc_mem/history");
		
		
		return "/alert";
		
	}
	
	@RequestMapping("orderModify")
	String orderModify(Model mm,HttpSession session ,ShippingDTO dto ) {
		System.out.println("환불완료로 수정전:"+dto);
		
		if(dto.getOrder_shipping().equals("배송준비중") || dto.getOrder_shipping().equals("결제완료")) {
			dto.setOrder_shipping("환불완료");
			int cnt = sm.orderRefund(dto);
			
			System.out.println(cnt+"환불완료로 수정후:"+dto);
			mm.addAttribute("msg", "결제 취소 되었습니다 (상태가 환불로 변경됩니다)");
			mm.addAttribute("goUrl", "/fc_mem/history");

		}
		else {
			mm.addAttribute("msg", "배송상태가 배송준비중 이거나 결제완료 에서만 가능합니다 (관리자에게 문의 부탁드립니다)");
			mm.addAttribute("goUrl", "/fc_mem/history");
		}
		
		
		
		return "/alert";
		
	}
	
	@RequestMapping("history")
	String myhistory(HttpSession session,Model mm , ShippingDTO dto) {
		MemberDTO mDTO;
		if(session.getAttribute("id")!=null) {
			mDTO = new MemberDTO();
			mDTO.setId((String)session.getAttribute("id"));
			MemberDTO member = mp.myPage(mDTO);
		
		dto.setOrder_ID(member.getId());
		mm.addAttribute("mainData", sm.myhistory(dto));
		}
		return "/fc_mem/history";
	}
	
}
