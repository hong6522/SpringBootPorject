package fc.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class FCController {

	@RequestMapping("/mainPage")
	String index(HttpSession session) {
		System.out.println("session:"+session.getAttribute("type"));
		return "mainPage";
	}
	
	
}
