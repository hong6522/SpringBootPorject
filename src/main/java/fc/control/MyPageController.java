package fc.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import fc.db.CenterPData;
import fc.db.MemberDTO;
import fc.db.MemberMapper;
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

@Resource
MemberMapper mp;

@RequestMapping("/myqna")
 String myqna(QnaDTO dto, Model mm, HttpSession session) {
	//String id = (String)session.getAttribute("id");
	MemberDTO mDTO;
	if(session.getAttribute("type")!=null) {
		mDTO = new MemberDTO();
		mDTO.setEmail((String)session.getAttribute("email"));
		MemberDTO myPage = mp.myPage(mDTO);
		mm.addAttribute("mainData", qm.myqnalist(myPage.getId()));
		return "myPage/myqna";
	}
	else {
		return "/fc/mem/login";
	}
	
}

@RequestMapping("/myqnaDetail/{no}")
String qnaDetail(Model mm, QnaDTO dto ,HttpSession session) {
	//String id = (String)session.getAttribute("id");
	MemberDTO mDTO;	
	
	qm.qcnt(dto.getNo());
//	if(session.getAttribute("type")!=null) {
//		mDTO = new MemberDTO();
//		mDTO.setEmail((String)session.getAttribute("email"));
//		MemberDTO myPage = mp.myPage(mDTO);
//		mm.addAttribute("mainData", qm.myqnalist(myPage.getId()));
//		return "myPage/myqna";
//	}
//	
	
	mm.addAttribute("mainData", qm.qdetail(dto));
	
	
	
	return "myPage/myqnaDetail";
}



@RequestMapping("/myreview")
String myreview(ReviewDTO dto ,Model mm, HttpSession session) {
	MemberDTO mDTO;
	if(session.getAttribute("type")!=null) {
		mDTO = new MemberDTO();
		mDTO.setEmail((String)session.getAttribute("email"));
		MemberDTO myPage = mp.myPage(mDTO);
		mm.addAttribute("mainData", rm.myreviewlist(myPage.getId()));
		return "myPage/myreview";
	}
	else {
		return "/fc/mem/login";
	}
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
