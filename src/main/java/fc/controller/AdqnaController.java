package fc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fc.db.AdQnaPData;
import fc.db.CenterPData;
import fc.db.MemberDTO;
import fc.db.MemberMapper;
import fc.db.QnaDTO;
import fc.db.QnaMapper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/ad_page/integrate/")
public class AdqnaController {

	@Resource	
	QnaMapper qm;
	
	@Resource	
	MemberMapper mp;
	
	@RequestMapping("memqna")
	String ad_qnalist(Model mm , QnaDTO dto , HttpServletRequest request) {
		System.out.print("전유택 진입");
		String msg="";
		
		
		new AdQnaPData(request);
		AdQnaPData pd = (AdQnaPData)request.getAttribute("pd");
		int qtotal = qm.qtotalcount();
		  pd.setTotal(qtotal);
		List<QnaDTO> mainData = qm.adqlist(pd);
		 String sch = request.getParameter("sch");
		System.out.println(pd.getStartPage());
		

	
		//검색 종류
		  if ("title".equals(dto.getKind())) {
		        pd.setKind("title");
		        pd.setSch(dto.getSch());
		        mainData = qm.adqlist(pd);
		    } else if ("id".equals(dto.getKind())) {
		        pd.setKind("id");
		        pd.setSch(dto.getSch());
		        mainData = qm.adqlist(pd);
		    } else {
		        mainData = qm.adqlist(pd);
		    }
		  
		  
		//mm.addAttribute("mainData", waitingList); 
		mm.addAttribute("mainData", mainData);// 합친 리스트를 View로 전달
		mm.addAttribute("pdata", pd);
		
						
		return "ad_page/integrate/memqna";
	}
	
	
	@RequestMapping("/memqnaDetail/{no}/{nowpage}")
	String ad_qnadetail(Model mm,@PathVariable int nowpage, QnaDTO dto , HttpServletRequest request ,HttpSession session) {
		
		new AdQnaPData(request);
		AdQnaPData pd = (AdQnaPData)request.getAttribute("pd");
		int qtotal = qm.qtotalcount();
		pd.setTotal(qtotal);	
		pd.setNowPage(nowpage);
		//System.out.println(dto);
		mm.addAttribute("pdata", pd);
		mm.addAttribute("mainData", qm.qdetail(dto));
	
			return "ad_page/integrate/memqnaDetail";
		}
	
	
	@GetMapping("/memqnaModify/{no}/{nowpage}")
	String memqnaModify(QnaDTO dto , Model mm ,@PathVariable int nowpage, HttpServletRequest request) {
		//String id = (String)session.getAttribute("id");
		
		new AdQnaPData(request);
		AdQnaPData pd = (AdQnaPData)request.getAttribute("pd");
	
		int qtotal = qm.qtotalcount();
		  pd.setTotal(qtotal);
		  pd.setNowPage(nowpage);
		  
		  
		  
		System.out.println(qm.qdetail(dto));
		mm.addAttribute("mainData", qm.qdetail(dto));
		mm.addAttribute("pdata", pd);
	//	mm.addAttribute("id",id );

		return "ad_page/integrate/memqnaModify";
	}
	
	
	@PostMapping("/memqnaModify/{no}/{nowpage}")
	String  qnaModifyComplete(Model mm, QnaDTO dto ,@PathVariable int nowpage ,HttpServletRequest request) {
	
		new AdQnaPData(request);
		AdQnaPData pd = (AdQnaPData)request.getAttribute("pd");
	    int cnt = qm.admodify(dto);
	    pd.setNowPage(nowpage);
	    String msg = "수정 불가";   
		String goUrl = "/ad_page/integrate/memqnaModify/"+dto.getNo()+"/"+nowpage;
	   
	    
	    if (cnt > 0) {
	        msg = "수정되었습니다.";
	        goUrl = "/ad_page/integrate/memqnaDetail/"+dto.getNo()+"/"+nowpage;
	        mm.addAttribute("msg", msg);
	        mm.addAttribute("goUrl", goUrl);
	    }
	    mm.addAttribute("msg", msg);
        mm.addAttribute("goUrl", goUrl);
	   
	    
	    return "ad_page/integrate/alert";
	}
	

	
	@RequestMapping(value = "memqnaDelete/{no}", method = RequestMethod.GET )
	String memqnaDelete(QnaDTO dto , Model mm) {
		 int cnt = qm.qdelete(dto.no);
		    String msg = "";
			String goUrl = "";
		   
		  
		    if (cnt > 0) {
		        msg = "삭제되었습니다.";
		        goUrl = "/ad_page/integrate/memqna";
		        mm.addAttribute("msg", msg);
		        mm.addAttribute("goUrl", goUrl);
		    }
		    mm.addAttribute("msg", msg);
	        mm.addAttribute("goUrl", goUrl);
		   
		    
		    return "ad_page/integrate/alert";
	}
	
	@GetMapping("/memqnaReply/{no}/{nowpage}")
	String memreply(QnaDTO dto , Model mm , @PathVariable int nowpage , HttpServletRequest request , HttpSession session) {
		
		
		new AdQnaPData(request);
		AdQnaPData pd = (AdQnaPData)request.getAttribute("pd");
		int qtotal = qm.qtotalcount();
	
		pd.setTotal(qtotal);	
		pd.setNowPage(nowpage);
		
				
		mm.addAttribute("pdata" , pd);

						
			return "/ad_page/integrate/memqnaReply";
		}
	
	
	@PostMapping("/memqnaReply/{no}/{nowpage}")
	String memreplyCom(QnaDTO dto , Model mm , @PathVariable int nowpage , HttpServletRequest request) {
		String msg="";
		String goUrl="/ad_page/integrate/memqnaReply/"+dto.getNo()+"/"+nowpage;
		new AdQnaPData(request);
		AdQnaPData pd = (AdQnaPData)request.getAttribute("pd");

		int qtotal = qm.qtotalcount();
		pd.setTotal(qtotal);	
		pd.setNowPage(nowpage);
		
		System.out.println("관리자답변: " + dto);
		int cnt = qm.adreply(dto);
			
		  if (cnt > 0) {
			  	
		        msg = "답글작성완료.";
		        goUrl = "/ad_page/integrate/memqnaDetail/"+dto.getNo()+"/"+nowpage;
		        mm.addAttribute("msg", msg);
		        mm.addAttribute("goUrl", goUrl);
		    	mm.addAttribute("pdata" , pd);
		    }
	
		
		
		return "ad_page/integrate/alert";
	}
}

