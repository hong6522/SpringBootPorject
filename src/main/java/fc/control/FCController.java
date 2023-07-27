package fc.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@RequestMapping("/mainPage/{proKind}")
	String index(Model mm, ProductDTO dto,@PathVariable String proKind) {
		System.out.println(proKind);
		mm.addAttribute("mainData", pm.pro_select(dto));
		mm.addAttribute("cate", proKind.toUpperCase());
		
		
		return "mainPage";
	}
}