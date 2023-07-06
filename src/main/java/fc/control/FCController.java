package fc.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FCController {

	@RequestMapping("/mainPage")
	String index() {
		
		return "mainPage";
	}
	
	@RequestMapping("/fc/mem/login")
	String login() {
		
		return "fc/mem/login";
	}
	
}
