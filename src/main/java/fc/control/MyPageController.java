package fc.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import fc.db.QnaDTO;
import fc.db.QnaMapper;
import fc.db.ReviewDTO;
import fc.db.ReviewMapper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/myPage")
public class MyPageController {
	
@Resource
QnaMapper qm;

@Resource
ReviewMapper rm;
	
@RequestMapping("/myqna")
 String myqna(QnaDTO dto ,Model mm, HttpSession session) {
	//String id = (String)session.getAttribute("id");
	String id = "qqq";
	//dto.setId(id);
	
	System.out.println(dto.getId());
	//qm.myqnalist(dto);
	mm.addAttribute("mainData", qm.myqnalist(id));
	return "myPage/myqna";
}

@RequestMapping("/myreview")
String myqna(ReviewDTO dto ,Model mm, HttpSession session) {
	//String id = (String)session.getAttribute("id");
	String id = "qqq";
	//dto.setId(id);
	
	System.out.println(dto.getId());
	//qm.myqnalist(dto);
	mm.addAttribute("mainData", qm.myqnalist(id));
	return "myPage/myreview";
}



}
