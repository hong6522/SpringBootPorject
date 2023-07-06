package fc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FCController {

	@RequestMapping("/mainPage")
	String index() {
		
		return "index";
	}
	
	@RequestMapping("/fc/login")
	String login() {
		
		return "fc/mem/login";
	}
	
}
