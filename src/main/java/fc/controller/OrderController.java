package fc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ad_page/order/")
public class OrderController {
	@RequestMapping("order")
	String order() {
		return "ad_page/order";
	}
}
