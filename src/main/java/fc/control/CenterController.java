package fc.control;


import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import fc.db.NoticeDTO;
import fc.db.NoticeMapper;
import fc.db.CenterPData;
import fc.db.QnaDTO;
import fc.db.QnaMapper;
import fc.db.ReviewDTO;
import fc.db.ReviewMapper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/center")
public class CenterController {
	
	@Resource
	NoticeMapper cm;
	
	@Resource
	QnaMapper qm;
	
	@Resource
	ReviewMapper rm;
	
	@RequestMapping("/notice")
	String centerList(Model mm ,NoticeDTO dto,  HttpServletRequest request) {	
		//System.out.println(dto);
		new CenterPData(request);
		CenterPData pd = (CenterPData)request.getAttribute("pd");
		int ntotal = cm.ntotalcount();
		  pd.setTotal(ntotal);
		List<NoticeDTO> mainData = cm.nlist(pd);
		 String sch = request.getParameter("sch");
		 System.out.println(sch);
		
		  pd.setTotal(ntotal);
		   // or "id" based on the selected option
		  if ("title".equals(dto.getKind())) {
		        pd.setKind("title");
		        pd.setSch(dto.getSch());
		        mainData = cm.nlist(pd);
		    } else if ("id".equals(dto.getKind())) {
		        pd.setKind("id");
		        pd.setSch(dto.getSch());
		        mainData = cm.nlist(pd);
		    } else {
		        mainData = cm.nlist(pd);
		    }
		   
		mm.addAttribute("pdata", pd);
		mm.addAttribute("mainData", mainData);
		return "center/notice";		
	}
	
	@RequestMapping("/noticeDetail/{no}")
	String centerDetail(Model mm, NoticeDTO dto) {
		//System.out.println(dto.getId());
		System.out.println(dto);
		cm.ncnt(dto.no);
		mm.addAttribute("mainData", cm.ndetail(dto));
		NoticeDTO aa = cm.ndetail(dto);
		System.out.println(aa);
		return "center/noticeDetail";
	}
	
	@RequestMapping("/qna")
	String qnaList(Model mm ,QnaDTO dto , HttpServletRequest request ) {	
		String msg="";
		//String id  = (String)session.getAttribute("id");
		String id="asd";
		new CenterPData(request);
		CenterPData pd = (CenterPData)request.getAttribute("pd");
		int qtotal = qm.qtotalcount();
		  pd.setTotal(qtotal);
		List<QnaDTO> mainData = qm.qlist(pd);
		
		//검색 종류
		  if ("title".equals(dto.getKind())) {
		        pd.setKind("title");
		        pd.setSch(dto.getSch());
		        mainData = qm.qlist(pd);
		    } else if ("id".equals(dto.getKind())) {
		        pd.setKind("id");
		        pd.setSch(dto.getSch());
		        mainData = qm.qlist(pd);
		    } else {
		        mainData = qm.qlist(pd);
		    }
		
		mm.addAttribute("id", id);
		mm.addAttribute("pdata", pd);
		mm.addAttribute("mainData", mainData);
		return "center/qna";		
	}
	
	
	@RequestMapping("/qnaDetail/{no}")
	String qnaDetail(Model mm, QnaDTO dto) {
		//String id = (String)session.getAttribute("id");
		String id = "qqq";	
		
		qm.qcnt(dto.getNo());
		
		mm.addAttribute("mainData", qm.qdetail(dto));
		mm.addAttribute("id", id); //세션아이디
		
		
		return "center/qnaDetail";
	}
	
	
	@GetMapping("/qnaInsert")
	String qnaInsert(QnaDTO dto , Model mm,  HttpSession session) {
		//String id = (String)session.getAttribute("id");
		String id = "qqq";						
		mm.addAttribute("id",id );
		
		return "center/qnaInsert";
	}
	
	
	@PostMapping("/qnaInsert")
	String  qnaInsertComplete(Model mm, QnaDTO dto) {
		qm.qinsert(dto);
		//System.out.println(dto.no);
		mm.addAttribute("msg", "입력되었습니다.");
		mm.addAttribute("goUrl", "qnaDetail/"+dto.getNo());
		return "center/alert";
	}
	
	
	
	@GetMapping("/qnaModify/{no}")
	String qnaModify(QnaDTO dto , Model mm) {
		//String id = (String)session.getAttribute("id");
		String id = "qqq";	
		
		
		mm.addAttribute("mainData", qm.qdetail(dto));
	//	mm.addAttribute("id",id );
	//	System.out.println(dto.getNo());
		return "center/qnaModify";
	}
	
	
	@PostMapping("/qnaModify/{no}")
	String  qnaModifyComplete(Model mm, QnaDTO dto) {
	
	    
	    int cnt = qm.qmodify(dto);
	    String msg = "수정 불가";
		String goUrl = "/center/qnaModify/"+dto.getNo();
	   
	    
	    if (cnt > 0) {
	        msg = "수정되었습니다.";
	        goUrl = "/center/qnaDetail/"+dto.getNo();
	        mm.addAttribute("msg", msg);
	        mm.addAttribute("goUrl", goUrl);
	    }
	    mm.addAttribute("msg", msg);
        mm.addAttribute("goUrl", goUrl);
	   
	    
	    return "center/alert";
	}
	
//	@GetMapping("/qnaDelete/{no}")
//	String qnaDelete(QnaDTO dto , Model mm) {
//		//String id = (String)session.getAttribute("id");
//		//String id = "qqq";	
//		
//		
//		mm.addAttribute("mainData", qm.qdetail(dto));
//	//	mm.addAttribute("id",id );
//		return "center/qnaDelete";
//	}
//	
//	
//	
//	@PostMapping("/qnaDelete/{no}")
//	String  qnaDeleteComplete(Model mm, QnaDTO dto) {
//	
//	    
//	    int cnt = qm.qdelete(dto);
//	    String msg = "삭제 불가";
//		String goUrl = "/center/qnaDelete/"+dto.getNo();
//	   
//	  
//	    if (cnt > 0) {
//	        msg = "삭제되었습니다.";
//	        goUrl = "/center/qna";
//	        mm.addAttribute("msg", msg);
//	        mm.addAttribute("goUrl", goUrl);
//	    }
//	    mm.addAttribute("msg", msg);
//        mm.addAttribute("goUrl", goUrl);
//	   
//	    
//	    return "center/alert";
//	}
	
	@RequestMapping(value = "qnaDelete/{no}", method = RequestMethod.GET )
	String qnaDelete(QnaDTO dto , Model mm) {
		 int cnt = qm.qdelete(dto.no);
		    String msg = "삭제 불가";
			String goUrl = "/center/qnaDelete/"+dto.getNo();
		   
		  
		    if (cnt > 0) {
		        msg = "삭제되었습니다.";
		        goUrl = "/center/qna";
		        mm.addAttribute("msg", msg);
		        mm.addAttribute("goUrl", goUrl);
		    }
		    mm.addAttribute("msg", msg);
	        mm.addAttribute("goUrl", goUrl);
		   
		    
		    return "center/alert";
	}
	@GetMapping("/secretForm/{no}")
	String secretFormInsert(QnaDTO dto , Model mm) {
		//String id = (String)session.getAttribute("id");
		
		
		mm.addAttribute("mainData", qm.qdetail(dto));
		return "center/secretForm";
	}
	
	@PostMapping("/secretForm/{no}")
	String secretFormComple(QnaDTO dto , Model mm) {
		//String id = (String)session.getAttribute("id");
		String msg = "암호인증실패";
		String goUrl="/center/secretForm/"+dto.getNo();
		
		 int cnt = qm.pwchk(dto);
		 System.out.println(cnt);
		 if (cnt > 0) {
		        msg = "암호인증성공";
		        goUrl = "/center/qnaDetail/"+dto.getNo();
		        mm.addAttribute("msg", msg);
		        mm.addAttribute("goUrl", goUrl);
		    }
		 mm.addAttribute("msg", msg);
	     mm.addAttribute("goUrl", goUrl);
		 
		return "center/alert";
	}
	
	
	
			
	@RequestMapping("/review")
	String reviewList(Model mm ,ReviewDTO dto,  HttpServletRequest request) {	
		//System.out.println(dto);
		new CenterPData(request);
		CenterPData pd = (CenterPData)request.getAttribute("pd");
		int rtotal = rm.rtotalcount();
		  pd.setTotal(rtotal);
		List<ReviewDTO> mainData = rm.rlist(pd);
		 String sch = request.getParameter("sch");
		
		
		  pd.setTotal(rtotal);
		   // or "id" based on the selected option
		  if ("title".equals(dto.getKind())) {
		        pd.setKind("title");
		        pd.setSch(dto.getSch());
		        mainData = rm.rlist(pd);
		    } else if ("id".equals(dto.getKind())) {
		        pd.setKind("id");
		        pd.setSch(dto.getSch());
		        mainData = rm.rlist(pd);
		    } else {
		        mainData = rm.rlist(pd);
		    }
		   
		mm.addAttribute("pdata", pd);
		mm.addAttribute("mainData", mainData);
		return "center/review";		
	}
	
	
	@RequestMapping("/reviewDetail/{no}/{order_code}")
	String reviewDetail(Model mm , ReviewDTO dto , MultipartFile ff  ) {
		//String id = (String)session.getAttribute("id");
		String id = "qqq"	;
				
		
		
		
		rm.rcnt(dto.getNo());
		
//		System.out.println(upfile+","+upfile2+","+upfile3);
//		System.out.println(upfile+","+upfile2+","+upfile3);
//		System.out.println(upfile+","+upfile2+","+upfile3);
//		System.out.println(upfile+","+upfile2+","+upfile3);
//		System.out.println(upfile+","+upfile2+","+upfile3);
		System.out.println(dto.getNo());
		System.out.println(dto.getUpfile());
		mm.addAttribute("mainData", rm.rdetail(dto));
		
	//System.out.println(mm.getAttribute("mainData"));
		mm.addAttribute("id", id); //세션아이디
		
		return "center/reviewDetail";
	}
	
}
