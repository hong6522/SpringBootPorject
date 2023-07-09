package fc.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fc.db.RefundDTO;
import fc.db.RefundMapper;
import fc.db.ShippingDTO;
import fc.db.ShippingMapper;
import jakarta.annotation.Resource;

@Controller
public class ShippingController {

	@Resource
	ShippingMapper shim;
	@Resource
	RefundMapper refm;
	
	@RequestMapping("/ad_page/order/orderList")
	String orderList(Model mm, ShippingDTO dto) {
		
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:sss");
		dto.setToday(sdf.format(today));
		
		System.out.println(dto.getToday());
		
		ArrayList<ShippingDTO> todayData = shim.todayList(dto);
		ArrayList<ShippingDTO> mainData = shim.MainList(dto);
		for (ShippingDTO td : todayData) {
			td.setOrder_dateStr(sdf2.format(td.getOrder_date()));
			
			if(td.getOrder_cancleDate()==null) {
				td.setCancleStr("환불정보가 없습니다.");
			}
			
		}
		
		for (ShippingDTO td : mainData) {
			td.setOrder_dateStr(sdf2.format(td.getOrder_date()));
			
			if(td.getOrder_cancleDate()==null) {
				td.setCancleStr("환불정보가 없습니다.");
			}else {
				td.setCancleStr(sdf2.format(td.getOrder_cancleDate()));
			}
			
		}
		
		
		mm.addAttribute("todayData", todayData);
		mm.addAttribute("mainData", mainData);
		System.out.println(todayData);
		
		return "ad_page/order/orderList";
		
	}
	
	@GetMapping("/ad_page/order/shippingList")
	String shippingList(Model mm, ShippingDTO dto) {
		
		ArrayList<ShippingDTO> beforeData = shim.beforeList(dto);
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:sss");
		for (ShippingDTO td : beforeData) {
			td.setOrder_dateStr(sdf2.format(td.getOrder_date()));
			if(td.getOrder_cancleDate()==null) {
				td.setCancleStr("환불정보가 없습니다.");
			}else {
				td.setCancleStr(sdf2.format(td.getOrder_cancleDate()));
			}
		}
		
		
		
		ArrayList<ShippingDTO> ingData = shim.ingList(dto);
		for (ShippingDTO td : ingData) {
			td.setOrder_dateStr(sdf2.format(td.getOrder_date()));
			if(td.getOrder_cancleDate()==null) {
				td.setCancleStr("환불정보가 없습니다.");
			}else {
				td.setCancleStr(sdf2.format(td.getOrder_cancleDate()));
			}
		}
		
		ArrayList<ShippingDTO> afterData = shim.afterList(dto);
		for (ShippingDTO td : afterData) {
			td.setOrder_dateStr(sdf2.format(td.getOrder_date()));
			if(td.getOrder_cancleDate()==null) {
				td.setCancleStr("환불정보가 없습니다.");
			}else {
				td.setCancleStr(sdf2.format(td.getOrder_cancleDate()));
			}
		}
		
		mm.addAttribute("beforeData", beforeData);
		mm.addAttribute("ingData", ingData);
		mm.addAttribute("afterData", afterData);
		
		return "ad_page/order/shippingList";
	}
	
	@PostMapping("/ad_page/order/shippingList")
	String shippingComplete(Model mm, ShippingDTO dto) {
		System.out.println(dto.getSchNo()+","+dto.getShippingChk());
		int res = shim.update(dto);
		System.out.println(res);
		String msg = "수정에 실패하였습니다.";
		String goUrl = "/ad_page/order/shippingList";
		if(res==1) {
			msg = "수정완료하였습니다.";
		}
		
		mm.addAttribute("msg", msg);
		mm.addAttribute("goUrl", goUrl);
		
		return "/alert";
	}
	
	@RequestMapping("/ad_page/order/refundList")
	String refundList(Model mm, RefundDTO dto) {
		
		ArrayList<RefundDTO> waitData = refm.waitList(dto);
		ArrayList<RefundDTO> possData = refm.possList(dto);
		ArrayList<RefundDTO> impoData = refm.impoList(dto);
		
		System.out.println(waitData);
		
		mm.addAttribute("waitData", waitData);
		mm.addAttribute("possData", possData);
		mm.addAttribute("impoData", impoData);
		
		return "ad_page/order/refundList";
	}
	
	@GetMapping("/ad_page/order/refundDetail/{orderNo}")
	String refundDetail(Model mm, RefundDTO dto) {
		
		RefundDTO mainData = refm.detail(dto); 
		
		mm.addAttribute("mainData", mainData);
		
		return "ad_page/order/refundDetail";
	}
	
	@PostMapping("/ad_page/order/refundDetail/{orderNo}")
	String refundComplete(Model mm, RefundDTO dto) {
		
		System.out.println(dto);
		int res = refm.update(dto);
		System.out.println(res);
		String msg = "오류가 발생하였습니다.";
		String goUrl = "/ad_page/order/refundList";
		if(res==1) {
			msg = "답변이 저장되었습니다.";
		}
		
		mm.addAttribute("msg", msg);
		mm.addAttribute("goUrl", goUrl);
		
		return "/alert";
	}
	
}
