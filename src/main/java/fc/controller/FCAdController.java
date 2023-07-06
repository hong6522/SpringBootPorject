package fc.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import fc.db.SalesDTO;
import fc.db.SalesMapper;
import jakarta.annotation.Resource;

@Controller
public class FCAdController {
	
	@Resource
	SalesMapper sm;
	
	@RequestMapping("/ad_page/adindex")
	String adindex(Model mm, SalesDTO dto) {
		
//		그래프
		ArrayList<Integer> OutMonData = new ArrayList<>();
		ArrayList<Integer> ShiMonData = new ArrayList<>();
		ArrayList<Integer> PanMonData = new ArrayList<>();
		ArrayList<Integer> ShoMonData = new ArrayList<>();
		for (int i = 1; i <= 12; i++) {
			int outtot = 0;
			int shitot = 0;
			int pantot = 0;
			int shotot = 0;
			
			String st = "";
			if(i<10) {
				st = "0"+i;
			}else {
				st = i+"";
			}
			ArrayList<Integer> outCnt = sm.outerMonth(st);
			ArrayList<Integer> shiCnt = sm.shirtMonth(st);
			ArrayList<Integer> panCnt = sm.pantsMonth(st);
			ArrayList<Integer> shoCnt = sm.shoesMonth(st);
			for (Integer j : outCnt) {
				outtot += j;
			}
			for (Integer j : shiCnt) {
				shitot += j;
			}
			for (Integer j : panCnt) {
				pantot += j;
			}
			for (Integer j : shoCnt) {
				shotot += j;
			}
			OutMonData.add(outtot);
			ShiMonData.add(shitot);
			PanMonData.add(pantot);
			ShoMonData.add(shotot);
		}
		mm.addAttribute("OutMonData",OutMonData);
		mm.addAttribute("ShiMonData",ShiMonData);
		mm.addAttribute("PanMonData",PanMonData);
		mm.addAttribute("ShoMonData",ShoMonData);
		
//		아우터 데이터 계산
		ArrayList<SalesDTO> outerData = sm.outerList(dto);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		HashSet<String> outerArr = new HashSet<>();
		ArrayList<String> outArr = new ArrayList<>();
	 
		for (SalesDTO od : outerData) {
			od.setSal_dateStr(sdf.format(od.getSal_date()));
			outerArr.add(od.getSal_name());
		}
		
		for (String oa : outerArr) {
			outArr.add(oa);
		}
		
		ArrayList<Integer> oArr = new ArrayList<>();
		
		for (int i = 0; i < outArr.size(); i++) {
			int k = 0;
			for (SalesDTO md : outerData) {
				if(md.getSal_name().equals(outArr.get(i))) {
					k+=md.getSal_cnt();
				}
			}
			oArr.add(k);
		}

		mm.addAttribute("outerArr",outArr);
		mm.addAttribute("oArr",oArr);
		
//		상의 데이터 계산
		ArrayList<SalesDTO> shirtData = sm.shirtList(dto);
		HashSet<String> shirtrArr = new HashSet<>();
		ArrayList<String> shArr = new ArrayList<>();
		
		for (SalesDTO sd : shirtData) {
			shirtrArr.add(sd.getSal_name());
		}
		
		for (String sa : shirtrArr) {
			shArr.add(sa);
		}
		
		ArrayList<Integer> sArr = new ArrayList<>();
		
		for (int i = 0; i < shArr.size(); i++) {
			int k = 0;
			for (SalesDTO sd : shirtData) {
				if(sd.getSal_name().equals(shArr.get(i))) {
					k+=sd.getSal_cnt();
				}
			}
			sArr.add(k);
		}

		mm.addAttribute("shirtArr",shArr);
		mm.addAttribute("sArr",sArr);
		
//		하의 데이터 계산
		ArrayList<SalesDTO> pantsData = sm.pantsList(dto);
		HashSet<String> pantsrArr = new HashSet<>();
		ArrayList<String> paArr = new ArrayList<>();
		
		for (SalesDTO sd : pantsData) {
			pantsrArr.add(sd.getSal_name());
		}
		
		for (String sa : pantsrArr) {
			paArr.add(sa);
		}
		
		ArrayList<Integer> pArr = new ArrayList<>();
		
		for (int i = 0; i < paArr.size(); i++) {
			int k = 0;
			for (SalesDTO sd : pantsData) {
				if(sd.getSal_name().equals(paArr.get(i))) {
					k+=sd.getSal_cnt();
				}
			}
			pArr.add(k);
		}

		mm.addAttribute("pantsArr",paArr);
		mm.addAttribute("pArr",pArr);
		
//		신발 데이터 계산
		ArrayList<SalesDTO> shoesData = sm.shoesList(dto);
		HashSet<String> shoesrArr = new HashSet<>();
		ArrayList<String> shoeArr = new ArrayList<>();
		
		for (SalesDTO sd : shoesData) {
			shoesrArr.add(sd.getSal_name());
		}
		
		for (String sa : shoesrArr) {
			shoeArr.add(sa);
		}
		
		ArrayList<Integer> shoArr = new ArrayList<>();
		
		for (int i = 0; i < shoeArr.size(); i++) {
			int k = 0;
			for (SalesDTO sd : shoesData) {
				if(sd.getSal_name().equals(shoeArr.get(i))) {
					k+=sd.getSal_cnt();
				}
			}
			shoArr.add(k);
		}

		mm.addAttribute("shoesArr",shoeArr);
		mm.addAttribute("shoArr",shoArr);
		
		return "ad_page/adindex";
	}
}
