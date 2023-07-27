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
	
	@RequestMapping("detail/{proName}")
    String pro_detail(ProductDTO dto , Model mm) {

        System.out.println(dto);
        ProductDTO pdto = pm.pro_detail(dto);

        if(pdto == null) {
            System.out.println("존재하지 않는 상품입니다.");
            mm.addAttribute("msg", "존재하지 않는 상품입니다.");
            mm.addAttribute("goUrl", "/fc_mem/history");

            return "center/alert";

        }

        mm.addAttribute("mainData", pdto);

        return "/fc_pro/detail";
    }
	
	
}
