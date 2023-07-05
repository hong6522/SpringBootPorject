package fc.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fc.db.MemberDTO;
import fc.db.MemberMapper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("fc/mem/")
public class MemController {
	
	@Resource
	MemberMapper mm;
	
	@RequestMapping("logOut")
	String session_Out(HttpSession session) {
		
		session.invalidate();
		
		return "/fc/mem/login";
	}

	@GetMapping("login")
	String login() {
		
		return "fc/mem/login";
	}
	
	@PostMapping("login")
	String login_complate(HttpSession session,MemberDTO dto) {
		System.out.println(dto);
		MemberDTO mdto = mm.login(dto);
		System.out.println(mdto);
		session.setAttribute("type", "nomal");
		session.setAttribute("email", mdto.getEmail());
		
		return "mainPage";
	}
	
	@GetMapping("join")
	String mem_join() {
		
		return "/fc/mem/join";
	}
	
	@PostMapping("join")
	String mem_join_com(MemberDTO dto) {
		System.out.println(dto);
		
		return "mainPage";
	}
	
}
