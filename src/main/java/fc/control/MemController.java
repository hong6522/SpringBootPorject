package fc.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("fc/mem/")
public class MemController {
	
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
	String login_complate() {
		
		return "fc/mem/login";
	}
	
}
