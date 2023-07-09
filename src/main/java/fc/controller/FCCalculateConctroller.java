package fc.controller;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import fc.db.CalculateDTO;
import fc.db.CalculateMapper;
import jakarta.annotation.Resource;

@Controller
public class FCCalculateConctroller {

	@Resource
	CalculateMapper cm;
	
	@RequestMapping("/ad_page/calculate")
	String calculate(Model mm, CalculateDTO dto) {
		int oriTot = 0, celTot = 0;
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		NumberFormat nf = NumberFormat.getNumberInstance();
		List<CalculateDTO> mainData = cm.list(dto);
		for (CalculateDTO cd : mainData) {
			celTot+=cd.getCal_celPri()*cd.getCal_cnt();
			oriTot+=cd.getCal_oriPri()*cd.getCal_cnt();
			if(cd.getCal_refund()) {
				cd.setRefundStr("O");
			}else {
				cd.setRefundStr("X");
			}
		}
		mm.addAttribute("mainData",mainData);
		mm.addAttribute("oriTot", oriTot);
		mm.addAttribute("celTot", celTot);
		mm.addAttribute("today", sdf.format(today));
		
		System.out.println("오늘날짜: "+sdf.format(today));
		return "ad_page/calculate";
	}
	
}
