package fc.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
			session.setAttribute("email", mdto.getEmail());
			
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
		System.out.println(dto);
		
		int cnt = mp.join(dto);
		
		System.out.println(cnt);
		mm.addAttribute("msg", "회원가입 완료");
		mm.addAttribute("goUrl", "/fc_mem/login");
		return "alert";
		
	}
	
	@RequestMapping("myPage")
	String myPage(Model mm,HttpSession session ,MemberDTO dto) {
		
		dto.setEmail((String)session.getAttribute("email"));
		MemberDTO myPage = mp.myPage(dto);
		mm.addAttribute("mainData", myPage);
		return "/fc_mem/myPage";
	}
	
	@RequestMapping("myBasket")
	String myBasket(Model mm,HttpSession session) {
		MemberDTO mDTO;
		if(session.getAttribute("type")!=null) {
			mDTO = new MemberDTO();
			mDTO.setEmail((String)session.getAttribute("email"));
			MemberDTO member = mp.myPage(mDTO);
			mm.addAttribute("mainData", bm.basket_list(member));
			
		}
		
		return "/fc_mem/myBasket";
		
	}
	
	@RequestMapping("goOrder")
	String goOrder(Model mm,HttpSession session , ShippingDTO dto) {
		System.out.println(dto);
		MemberDTO mDTO;
		if(session.getAttribute("type")!=null) {
			System.out.println("들어옴?");
			mDTO = new MemberDTO();
			mDTO.setEmail((String)session.getAttribute("email"));
			MemberDTO member = mp.myPage(mDTO);
			dto.setOrder_ID(member.getId());
			System.out.println(dto);
			sm.Order_insert(dto);
			mm.addAttribute("msg", "결제완료");
			mm.addAttribute("goUrl", "/mainPage/best");
		}
		
		
		return "/alert";
		
	}
	
	@RequestMapping("history")
	String myhistory(HttpSession session,Model mm , ShippingDTO dto) {
		MemberDTO mDTO;
		if(session.getAttribute("type")!=null) {
			mDTO = new MemberDTO();
			mDTO.setEmail((String)session.getAttribute("email"));
			MemberDTO member = mp.myPage(mDTO);
		
		dto.setOrder_ID(member.getId());
		dto.setOrder_place(member.getAddress1());
		mm.addAttribute("mainData", sm.myhistory(dto));
		}
		return "/fc_mem/history";
	}
	
}
