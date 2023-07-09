package fc.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import fc.db.ProductDTO;
import fc.db.ProductMapper;
import jakarta.annotation.Resource;

@Controller
@RequestMapping("/fc_pro/")
public class UserProductController {
	
	@Resource
	ProductMapper pm;
	
	@RequestMapping("detail/{num}")
	String pro_detail(ProductDTO dto , Model mm) {
		
		ProductDTO pdto = pm.pro_detail(dto);
		
		mm.addAttribute("mainData", pdto);
		
		return "/fc_pro/detail";
	}
	
	
}
