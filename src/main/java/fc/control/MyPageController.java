package fc.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import fc.db.CenterPData;
import fc.db.QnaDTO;
import fc.db.QnaMapper;
import fc.db.ReviewDTO;
import fc.db.ReviewMapper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/myPage")
public class MyPageController {
	
@Resource
QnaMapper qm;

@Resource
ReviewMapper rm;
	
@RequestMapping("/myqna")
 String myqna(QnaDTO dto, Model mm, HttpSession session) {
	//String id = (String)session.getAttribute("id");
	String id = "qqq";
	
	//dto.setId(id);
	//qm.myqnalist(dto);
	mm.addAttribute("mainData", qm.myqnalist(id));
	return "myPage/myqna";
}

@RequestMapping("/myqnaDetail/{no}")
String qnaDetail(Model mm, QnaDTO dto , HttpServletRequest request) {
	//String id = (String)session.getAttribute("id");
	String id = "qqq";	
	
	qm.qcnt(dto.getNo());
	
	
	
	mm.addAttribute("mainData", qm.qdetail(dto));
	mm.addAttribute("id", id); //세션아이디
	
	
	return "myPage/myqnaDetail";
}



@RequestMapping("/myreview")
String myreview(ReviewDTO dto ,Model mm, HttpSession session) {
	//String id = (String)session.getAttribute("id");
	String id = "qqq";
    
	//qm.myqnalist(dto);
	
	
	mm.addAttribute("mainData", rm.myreviewlist(id));
	return "myPage/myreview";
}

@RequestMapping("/myreviewDetail/{no}/{order_code}")
String reviewDetail(Model mm , ReviewDTO dto , MultipartFile ff  ) {
	//String id = (String)session.getAttribute("id");
	String id = "qqq"	;		
	
	rm.rcnt(dto.getNo());
	
	System.out.println(dto.getNo());

//	System.out.println(upfile+","+upfile2+","+upfile3);


	mm.addAttribute("mainData", rm.rdetail(dto));
	
//System.out.println(mm.getAttribute("mainData"));
	mm.addAttribute("id", id); //세션아이디
	
	return "myPage/myreviewDetail";
}




}
