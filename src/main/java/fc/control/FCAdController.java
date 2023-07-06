package fc.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FCAdController {
	
	@RequestMapping("/")
	String adindex() {
		return "adindex";
	}
}
