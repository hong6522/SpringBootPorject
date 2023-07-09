package fc.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:sss");
		dto.setToday(sdf.format(today));
		
		ArrayList<ShippingDTO> todayData = shim.todayList(dto);
		ArrayList<ShippingDTO> mainData = shim.MainList(dto);
		for (ShippingDTO td : todayData) {
			td.setOrder_dateStr(sdf.format(td.getOrder_date()));
			
			if(td.getOrder_cancleDate()==null) {
				td.setCancleStr("환불정보가 없습니다.");
			}
			
		}
		
		for (ShippingDTO td : mainData) {
			td.setOrder_dateStr(sdf.format(td.getOrder_date()));
			
			if(td.getOrder_cancleDate()==null) {
				td.setCancleStr("환불정보가 없습니다.");
			}else {
				td.setCancleStr(sdf.format(td.getOrder_cancleDate()));
			}
			
		}
		
		
		mm.addAttribute("todayData", todayData);
		mm.addAttribute("mainData", mainData);
		System.out.println(today);
		System.out.println(todayData);
		
		return "ad_page/order/orderList";
		
	}
	
	@RequestMapping("/ad_page/order/shippingList")
	String shippingList(Model mm, ShippingDTO dto) {
		
		return "ad_page/order/shippingList";
	}
	
}
