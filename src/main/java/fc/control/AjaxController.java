package fc.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import fc.db.BasketDTO;
import fc.db.BasketMapper;
import fc.db.MemberDTO;
import fc.db.MemberMapper;
import fc.email.RMail;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;

@Controller
public class AjaxController {
	
	@Resource
	BasketMapper bm;
	
	@Resource
	MemberMapper mp;
	
	@Autowired
	RMail rMail;
	
	@RequestMapping("/ajax/loginSession")
	@ResponseBody
	String session_insert(@RequestParam("type")String type,HttpSession session) {
		System.out.println("세션타입: "+type);
		session.setAttribute("type", type);
		return ""+true;
	}
	
	@RequestMapping("/ajax/idChk")
	@ResponseBody
	String idChk(MemberDTO dto) {
		
		System.out.println(mp.idChk(dto));
		
		return "";
	}
	
	@RequestMapping("/ajax/addBasket")
	@ResponseBody
	String add_baskit(HttpSession session,BasketDTO dto) {
//		System.out.println("시작?");
//		
//		MemberDTO mDTO;
		if(session.getAttribute("id")!=null) {
//			mDTO = new MemberDTO();
//			mDTO.setEmail((String)session.getAttribute("id"));
//			MemberDTO member = mp.myPage(mDTO);
			
		dto.setAddress((String)session.getAttribute("address"));
		dto.setId((String)session.getAttribute("id"));
		System.out.println("장바구니dto: "+dto);
		bm.add_basket(dto);
		}
		System.out.println(dto);
		
		return true+"";
	}
	
	// 이메일 인증
	
	@RequestMapping("/ajax/mailConfirm")
	@ResponseBody
	String mailConfirm(@RequestParam("email") String email) throws Exception {
		System.out.println("아작스이메일 왔냐?");
	   String code = rMail.sendSimpleMessage(email);
//	   System.out.println("인증코드 : " + code);
	   return code;
	}
	
//	@RequestMapping("mapp")
//	@ResponseBody
//	Object mapp() {
//		
//		HashMap map 
//		
//		return map;
//	}
	
	
	
}
