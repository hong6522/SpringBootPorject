package fc.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import fc.db.ProductDTO;
import fc.db.ProductMapper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;

@Controller
public class FCController {
	
	@Resource
	ProductMapper pm;
	
	@RequestMapping("/mainPage")
	String index(HttpSession session) {
		System.out.println("session:"+session.getAttribute("type"));
		return "mainPage";
	}
	
	@RequestMapping("/mainPage/{kind}")
	String index(Model mm, ProductDTO dto) {
		
		mm.addAttribute("mainData", pm.pro_select(dto));
		
		return "mainPage";
	}
}
