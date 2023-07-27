package fc.control;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fc.db.BasketDTO;
import fc.db.BasketMapper;
import fc.db.CalculateMapper;
import fc.db.MemberDTO;
import fc.db.MemberMapper;
import fc.db.QnaMapper;
import fc.db.ReviewMapper;
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
	
	@Resource
	QnaMapper qm;
	
	@Resource
	CalculateMapper cm;
	
	@GetMapping("pwModify")
	String pwModify_get(MemberDTO dto) {
		
		System.out.println(dto.getId());
		
		return "/fc_mem/pwModify";
	}
	
	@PostMapping("pwModify")
	String pwModify_post(MemberDTO dto , Model mm) {
		
		System.out.println(dto);
		
		mp.mem_pwModify(dto);
		
		mm.addAttribute("msg","비밀번호 수정완료");
		mm.addAttribute("goUrl", "/fc_mem/login");
		
		return "/alert";
	}
	
	@RequestMapping("pwForm")
	String pwForm() {
		
		
		return "/fc_mem/pwForm";
	}
	
	@RequestMapping("logOut")
	String session_Out(HttpSession session , Model mm) {
		
		
		session.removeAttribute("type");
		session.removeAttribute("id");
		session.invalidate();
		mm.addAttribute("msg", "로그아웃 되었습니다.");
		mm.addAttribute("goUrl","/fc_mem/login");
		
		return "alert";
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
		if(mdto==null) {
			mm.addAttribute("msg", "아이디 혹은 비밀번호 오류입니다.");
			mm.addAttribute("goUrl", "/fc_mem/login");
			return "alert";
		}
		else if(mdto!=null) {
			if(mdto.getRank().equals("회원탈퇴")){
				mm.addAttribute("msg", "탈퇴된 회원입니다.");
				mm.addAttribute("goUrl", "/fc_mem/login");
				return "alert";
			}
			if(!mdto.getRank().equals("블랙리스트") || !mdto.getRank().equals("회원탈퇴")) {
			session.setAttribute("type", "nomal");
			session.setAttribute("address", mdto.getAddress1());
			session.setAttribute("id", mdto.getId());
			
			mm.addAttribute("msg", mdto.getName()+" 님 환영합니다.");
			mm.addAttribute("goUrl", "/mainPage/best");
			return "alert";
			}
		}
		if(mdto.getRank().equals("블랙리스트")){
			mm.addAttribute("msg", "블랙리스트 처리되어 로그인할수 없습니다.");
			mm.addAttribute("goUrl", "/fc_mem/login");
			return "alert";
		}
		

		
		return "alert";
	}
	
	@RequestMapping("memDelete")
	String memDelete(Model mm,MemberDTO dto) {
		System.out.println(dto);
		
		int cnt = mp.memDelete(dto);
		System.out.println(cnt);
		
		if(cnt==1) {
			mm.addAttribute("msg","회원탈퇴가 완료 되었습니다");
			mm.addAttribute("goUrl","/fc_mem/login");
		}
		else {
			mm.addAttribute("msg","회원정보가 일치하지 않습니다");
			mm.addAttribute("goUrl","/fc_mem/login");
		}
		return "/alert";
	}
	
	@GetMapping("join")
	String mem_join(Model mm) {
		
		Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String today = sdf.format(now);
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.YEAR, -14);
        Date fourteenYearsAgo = calendar.getTime();
        String age14 = sdf.format(fourteenYearsAgo);
		
        System.out.println(today);
		System.out.println(age14);
        
		mm.addAttribute("today", today);
		mm.addAttribute("age14", age14);
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
		if(mdto==null) {
			
			mm.addAttribute("msg", "비밀번호가 일치하지 않습니다.");
			mm.addAttribute("goUrl", "/fc_mem/modifyForm");
			return "/alert";
		}
		if(mdto!=null) {
		
			mm.addAttribute("mainData", mdto);
		}
		
		
		return "/fc_mem/modifyGo";
	}
	
	@PostMapping("modifyGo")
	String modifyGo_post(Model mm, MemberDTO dto) {
		
		System.out.println("포스트dto: "+dto);
		if(dto.getNewPw() == null || dto.getNewPw() == "") {
			int cnt = mp.mem_modify_addr(dto);
			System.out.println("===================================addr실행");
			if(cnt==1) {
				mm.addAttribute("msg","수정 완료");
				mm.addAttribute("goUrl" , "/fc_mem/myPage");
			}
			else {
				mm.addAttribute("msg","수정 실패");
				mm.addAttribute("goUrl" , "/fc_mem/modifyGo");
			}
		}
		else {
			int cnt = mp.mem_modify_pw(dto);
			System.out.println("=====================================pw실행");
			if(cnt==1) {
				mm.addAttribute("msg","비밀번호가 변경되어 재 로그인이 필요합니다.");
				mm.addAttribute("goUrl" , "/fc_mem/logOut");
			}
			else {
				mm.addAttribute("msg","수정 실패");
				mm.addAttribute("goUrl" , "/fc_mem/modifyGo");
			}
			
		}
		
		
		return "/alert";
	}
	
	@RequestMapping("myPage")
	String myPage(Model mm,HttpSession session ,MemberDTO dto) {
		
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		if(session.getAttribute("id")!=null) {
		dto.setId((String)session.getAttribute("id"));
		MemberDTO member = mp.myPage(dto);
		mm.addAttribute("mainData", member);
		mm.addAttribute("myPageCompleteList", sm.myPageCompleteList(member));
		mm.addAttribute("myPageIngList", sm.myPageIngList(member));
		mm.addAttribute("myPageCalList", sm.myPageCalList(member));
		mm.addAttribute("myPageDecideList", sm.myPageDecideList(member));
		mm.addAttribute("myPageExchangeRequestList", sm.myPageExchangeRequestList(member));
		mm.addAttribute("myPageExchangeComList", sm.myPageExchangeComList(member));
		mm.addAttribute("myPageRefundRequestList", sm.myPageRefundRequestList(member));
		mm.addAttribute("myPageRefundComList", sm.myPageRefundComList(member));
		mm.addAttribute("QmyPageBefore", qm.myPageBefore(member));
		mm.addAttribute("QmyPageAfter", qm.myPageAfter(member));
		mm.addAttribute("RmyPageAfter", sm.RmyPageAfter(member));	// 후기 작성 후
		mm.addAttribute("RmyPageBefore", sm.RmyPageBefore(member)); // 후기 작성 전
		
		ArrayList<ShippingDTO> myPageMainList = sm.myPageMainList(member);
		for (ShippingDTO sDTO : myPageMainList) {
			sDTO.setOrder_dateStr(sdf.format(sDTO.getOrder_date()));
		}
		
		ArrayList<BasketDTO> myPageBasket = bm.myPageBasket(member);
		for (BasketDTO bDTO : myPageBasket) {
			bDTO.setAddDateStr(sdf.format(bDTO.getAddDate()));
		}
		
		mm.addAttribute("myPageMainList",myPageMainList );
		mm.addAttribute("myPageBasket",myPageBasket );
		
		
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
		MemberDTO mdto = new MemberDTO();
		mdto.setId((String)session.getAttribute("id"));
		MemberDTO member = mp.myPage(mdto);
		if(session.getAttribute("id")!=null) {
			System.out.println("들어옴?");
			dto.setOrder_place(member.getAddress1());
			dto.setOrder_ID(member.getId());
			dto.setOrder_name(member.getName());
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
		//System.out.println("listOrder:"+uid);
		MemberDTO mdto = new MemberDTO();
		mdto.setId((String)session.getAttribute("id"));
		MemberDTO member = mp.myPage(mdto);
		//System.out.println(member);
		
		for (Integer no : pno) {
			BasketDTO dto = bm.order_move(no);
			dto.setAddress((String)session.getAttribute("address"));
			dto.setUid(uid);
			sm.basOrder_insert(dto);
			ShippingDTO sdto = new ShippingDTO();
			sdto.setOrder_ID((String)session.getAttribute("id"));
			sdto.setUid(uid);
			sdto.setOrder_name(member.getName());
			
			sm.BasUpdate(sdto);
			
			dto.setId((String)session.getAttribute("id"));
			bm.basketDelete(dto);
			
		}
		
			mm.addAttribute("msg", "결제성공");
			mm.addAttribute("goUrl", "/fc_mem/history");
		
		
		return "/alert";
		
	}
	
	@RequestMapping("orderModify")
	String orderModify(Model mm,HttpSession session ,ShippingDTO dto ) {

			int cnt = sm.orderRefund(dto);
		
			mm.addAttribute("msg", "결제 취소 되었습니다");
			mm.addAttribute("goUrl", "/fc_mem/history");
	
		return "/alert";
		
	}
	
	@RequestMapping("sellDecide")
	String sellDecide(Model mm,HttpSession session ,ShippingDTO dto ) {
			
		
			System.out.println("구매확정: "+dto);
			
			ArrayList<ShippingDTO> list = sm.shDTO(dto);
			
			
			
			int cnt = sm.sellDecide(dto);
			
			for (ShippingDTO shippingDTO : list) {
				int aCnt = cm.saleAdd(shippingDTO);
			}
		
			mm.addAttribute("msg", "구매확정 되었습니다. 감사합니다.");
			mm.addAttribute("goUrl", "/fc_mem/history");
	
		return "/alert";
		
	}
	
	@RequestMapping("history")
	String myhistory(HttpSession session,Model mm , ShippingDTO dto) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		LinkedHashMap<String, ArrayList<ShippingDTO>> uidMap = new LinkedHashMap<>();
		ArrayList<ShippingDTO> Sarr = new ArrayList<>();
		MemberDTO mDTO;
		
		if(session.getAttribute("id")==null) {
			mm.addAttribute("msg", "로그인이 필요한 시스템입니다.");
			mm.addAttribute("goUrl", "/fc_mem/login");
			
			return "/alert";
		}
		else if(session.getAttribute("id")!=null) {
			mDTO = new MemberDTO();
			mDTO.setId((String)session.getAttribute("id"));
			MemberDTO member = mp.myPage(mDTO);
		
		dto.setOrder_ID(member.getId());
		ArrayList<ShippingDTO> history = sm.myhistory(dto);
		
		for (ShippingDTO sdto : history) {
			
			sdto.setOrder_dateStr(sdf.format(sdto.getOrder_date()));
//			System.out.println("sdto: " + sdto);
			
			if(uidMap.containsKey(sdto.getUid())) {
				Sarr.add(sdto);
			}
			else {
				Sarr = new ArrayList<>();
				Sarr.add(sdto);
				uidMap.put(sdto.getUid(), Sarr);
			}
			
		}
		
		mm.addAttribute("historyMap", uidMap);
		
		
		}
		return "/fc_mem/history";
		
	}
	
}
