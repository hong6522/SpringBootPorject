package fc.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import fc.db.ShippingDTO;
import fc.db.ShippingMapper;
import jakarta.annotation.Resource;

@Controller
public class ShippingController {

	@Resource
	ShippingMapper shim;
	
	@RequestMapping("/ad_page/order/orderList")
	String orderList(Model mm, ShippingDTO dto) {
		
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		dto.setToday(sdf.format(today));
		
		ArrayList<ShippingDTO> todayData = shim.todayList(dto);
		for (ShippingDTO td : todayData) {
			td.setOrder_dateStr(sdf.format(td.getOrder_date()));
		}
		
		mm.addAttribute("todayData", todayData);
		System.out.println(today);
		System.out.println(todayData);
		
		return "ad_page/order/orderList";
		
	}
	
}
