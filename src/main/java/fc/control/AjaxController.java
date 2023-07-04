package fc.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;

@Controller
public class AjaxController {
	
	@RequestMapping("/ajax/loginSession")
	@ResponseBody
	String session_insert(@RequestParam("type")String type,HttpSession session) {
		System.out.println("세션타입: "+type);
		session.setAttribute("type", type);
		return ""+true;
	}
	
}
