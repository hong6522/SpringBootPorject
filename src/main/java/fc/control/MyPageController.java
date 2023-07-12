package fc.control;

import java.io.File;
import java.io.FileOutputStream;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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
	if(session.getAttribute("id")!=null) {
		mm.addAttribute("mainData", qm.myqnalist((String)session.getAttribute("id")));
		return "myPage/myqna";
	}
	else {
		return "/fc/mem/login";
	}
	
}


@RequestMapping("/myqnaDetail/{no}")
String myqnaDetail(Model mm, QnaDTO dto , HttpServletRequest request , HttpSession session) {

	qm.qcnt(dto.getNo());

	if(session.getAttribute("id")!=null) {

	mm.addAttribute("mainData", qm.qdetail(dto));
	mm.addAttribute("id", (String)session.getAttribute("id"));
	mm.addAttribute("no", dto.no);
	return "myPage/myqnaDetail";
    }
	else {
		return "/fc/mem/login";
	}
	
}



@RequestMapping("/myreview")
String myreview(ReviewDTO dto ,Model mm, HttpSession session) {

	if(session.getAttribute("id")!=null) {

		mm.addAttribute("mainData", rm.myreviewlist((String)session.getAttribute("id")));
	
		return "myPage/myreview";
	    }
		else {
			return "/fc/mem/login";
		}
	
}


@RequestMapping("/myreviewDetail/{no}")
String myreviewDetail(Model mm , ReviewDTO dto , MultipartFile ff ,  HttpSession session ) {
	rm.rcnt(dto.getNo());
	
	if(session.getAttribute("id")!=null) {

		mm.addAttribute("mainData", rm.rdetail(dto));
		mm.addAttribute("id", (String)session.getAttribute("id"));
		return "myPage/myreviewDetail";
	    }
		else {
			return "/fc/mem/login";
		}
	
//	System.out.println(upfile+","+upfile2+","+upfile3);
	
//System.out.println(mm.getAttribute("mainData"));
}

//@RequestMapping("/myqnaDetail/{no}/{nowpage}")
//String myqnaDetail(Model mm,@PathVariable int nowpage, QnaDTO dto , HttpServletRequest request , HttpSession session) {
//	//String id = (String)session.getAttribute("id");
//	//String id = "qqq";	
//	System.out.println("sssssssssss"+dto.no);
//	new CenterPData(request);
//	CenterPData pd = (CenterPData)request.getAttribute("pd");
//	qm.qcnt(dto.getNo());
//	int ntotal = qm.qtotalcount();
//	pd.setTotal(ntotal);
//	pd.setNowPage(nowpage);
//	
//	MemberDTO mDTO;
//	if(session.getAttribute("type")!=null) {
//
//		mDTO = new MemberDTO();
//		mDTO.setEmail((String)session.getAttribute("email"));
//		MemberDTO myPage = mp.myPage(mDTO);
//		mm.addAttribute("pdata", pd);
//		mm.addAttribute("mainData", qm.qdetail(dto));
//		mm.addAttribute("id", myPage.getId());
//		mm.addAttribute("no", dto.getNo() );
//		return "myPage/myqnaDetail";
//	    }
//		else {
//			return "/fc/mem/login";
//		}	
//	
//
//}


@GetMapping("/myqnaInsert")
String myqnaInsert(QnaDTO dto , Model mm,  HttpSession session  , HttpServletRequest request ) {

	
	if(session.getAttribute("id")!=null) {

		mm.addAttribute("id", (String)session.getAttribute("id"));
	
		return "myPage/myqnaInsert";
	    }
		else {
			return "/fc/mem/login";
		}
	
}


@PostMapping("/myqnaInsert")
String  myqnaInsertComplete(Model mm, QnaDTO dto) {
	
	qm.qinsert(dto);
	//System.out.println(dto.no);	
	mm.addAttribute("msg", "입력되었습니다.");
	mm.addAttribute("goUrl", "myqnaDetail/"+dto.getNo());
	return "center/alert";
}



@GetMapping("/myqnaModify/{no}")
String myqnaModify(QnaDTO dto , Model mm , HttpServletRequest request) {
	//String id = (String)session.getAttribute("id");
	//String id ="qqq";
	
	mm.addAttribute("mainData", qm.qdetail(dto));
	
	System.out.println(mm.getAttribute("mainData"));
	return "myPage/myqnaModify";
}

@PostMapping("/myqnaModify/{no}")
public String myqnaModifyComplete(Model mm, QnaDTO dto) {
	
	
	System.out.println("dd");
    int cnt = qm.qmodify(dto);
    String msg = "수정 불가";
    String goUrl = "/myPage/myqnaModify/" + dto.getNo();
    
    if (cnt > 0) {
        msg = "수정되었습니다.";
        goUrl = "/myPage/myqnaDetail/" + dto.getNo();
        mm.addAttribute("msg", msg);
        mm.addAttribute("goUrl", goUrl);
    }

    mm.addAttribute("msg", msg);
    mm.addAttribute("goUrl", goUrl);

    return "center/alert";
}



@RequestMapping(value = "myqnaDelete/{no}", method = RequestMethod.GET )
String myqnaDelete(QnaDTO dto , Model mm) {
	 int cnt = qm.qdelete(dto.no);
	    String msg = "";//"삭제 불가";
		String goUrl = "";//"/myPage/myqnaDelete/"+dto.getNo();
	   System.out.println(dto.getId());
	  
	    if (cnt > 0) {
	        msg = "삭제되었습니다.";;
	        goUrl = "/myPage/myqna";
	        mm.addAttribute("msg", msg);
	        mm.addAttribute("goUrl", goUrl);
	    }
	    mm.addAttribute("msg", msg);
        mm.addAttribute("goUrl", goUrl);
	    
	    return "center/alert";
}



@GetMapping("/myreviewModify/{no}")
String qnaModify(ReviewDTO dto , Model mm ) {
			
	mm.addAttribute("mainData", rm.rdetail(dto));

//	mm.addAttribute("id",id );

	return "myPage/myreviewModify";
}


@RequestMapping("/myreviewModify/{no}")
String  myreviewModifyComplete(Model mm, ReviewDTO dto , MultipartHttpServletRequest mulRR , HttpServletRequest request) {
    	
		System.out.println(dto.getFf1().getName());
	
		fileSave(dto.getFf1(), request);
		fileSave(dto.getFf2(), request);
		fileSave(dto.getFf3(), request);
		//System.out.println(dto.getFf1());
				
		dto.setUpfile(dto.getFf1().getOriginalFilename());
		dto.setUpfile1(dto.getFf2().getOriginalFilename());
		dto.setUpfile2(dto.getFf3().getOriginalFilename());
	
	
    int cnt = rm.rmodify(dto);
    String msg = "수정 불가";   
	String goUrl = "/myPage/myreviewModify/"+dto.getNo();
   
	  
    
    if (cnt > 0) {
        msg = "수정되었습니다.";
        goUrl = "/myPage/myreviewDetail/"+dto.getNo();
        mm.addAttribute("msg", msg);
        mm.addAttribute("goUrl", goUrl);
    }
    mm.addAttribute("msg", msg);
    mm.addAttribute("goUrl", goUrl);
   
    
    return "center/alert";
}





@GetMapping("/myreviewInsert")
String reviewInsert(ReviewDTO dto , Model mm,  HttpSession session  , HttpServletRequest request) {
	//String id = (String)session.getAttribute("id");
						
	
	
	
	
	if(session.getAttribute("type")!=null) {
		
		
		mm.addAttribute("mainData", rm.rdetail(dto));
		mm.addAttribute("id", (String)session.getAttribute("id"));
	
	}
		
	return "myPage/myreviewInsert";
}


@PostMapping("/myreviewInsert")
String  myreviewInsertComplete(Model mm, ReviewDTO dto , HttpServletRequest request ,  MultipartHttpServletRequest mulRR) {
	System.out.println(dto);
	//dto.setFf1(dto.getUpfile());
//	System.out.println(dto.getFf1());
	System.out.println("텍스트진입");
    fileSave(dto.getFf1(), request);
    fileSave(dto.getFf2(), request);
    fileSave(dto.getFf3(), request);
	//System.out.println(dto.getFf1());
   
	
	
	dto.setUpfile(dto.getFf1().getOriginalFilename());
	dto.setUpfile1(dto.getFf2().getOriginalFilename());
	dto.setUpfile2(dto.getFf3().getOriginalFilename());
	
	rm.rinsert(dto);	   
	//System.out.println(dto.no);
	//dto.setFf1(dto.getFf1());
	mm.addAttribute("msg", "입력되었습니다.");
	mm.addAttribute("goUrl", "/myPage/myreviewDetail/"+dto.no);
	return "center/alert";
}


@RequestMapping(value = "/myreviewDelete/{no}", method = RequestMethod.GET)
public String myreviewDelete(@PathVariable("no") int no, Model model, HttpServletRequest request) {
    ReviewDTO dto = rm.getreview(no);
    int cnt = rm.rdelete(no);
    String msg = "";
    String goUrl = "";

    if (dto != null) {
        String fileName = dto.getUpfile();
        String fileName1 = dto.getUpfile1();
        String fileName2 = dto.getUpfile2();
        if (fileName != null && !fileName.isEmpty()) {
            boolean deleted = fileDelete(fileName, request);
            dto.setUpfile(""); // 파일명 초기화
            if (deleted) {
                msg = "삭제되었습니다.";
                goUrl = "/myPage/myreview";
            } else {
                msg = "파일 삭제 실패";
                goUrl = "/myPage/myreview/" + no;
            }
        }
        
        if (fileName1 != null && !fileName1.isEmpty()) {
        	boolean    deleted = fileDelete(fileName1, request);
            dto.setUpfile1(""); // 파일명 초기화
            if (deleted) {
                msg = "삭제되었습니다.";
                goUrl = "/myPage/myreview";
            } else {
                msg = "파일 삭제 실패";
                goUrl = "/myPage/myreview/" + no;
            }
            
            if (fileName2 != null && !fileName2.isEmpty()) {
                 deleted = fileDelete(fileName2, request);
                dto.setUpfile2(""); // 파일명 초기화
                if (deleted) {
                    msg = "삭제되었습니다.";
                    goUrl = "/myPage/myreview";
                } else {
                    msg = "파일 삭제 실패";
                    goUrl = "/myPage/myreview/" + no;
                }
	                }
	            
	            
	        }
	    }
		
		    if (cnt > 0) {
		        msg = "삭제되었습니다.";
		        goUrl = "/myPage/myreview";
		    } else {
		        msg = "삭제 불가";
		        goUrl = "/myPage/myreview/" + no;
		    }

    model.addAttribute("msg", msg);
    model.addAttribute("goUrl", goUrl);

    return "center/alert";
}



void fileSave(MultipartFile ff,	HttpServletRequest request) {
	String path = request.getServletContext().getRealPath("img");
	//System.out.println(path);
	try {
		FileOutputStream fos = new FileOutputStream(path+"\\"+ff.getOriginalFilename());
		fos.write(ff.getBytes());
		fos.close();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
}


	
private boolean fileDelete(String fileName, HttpServletRequest request) {
    String path = request.getServletContext().getRealPath("img");
    String filePath = path + "/" + fileName;
    File file = new File(filePath);

    if (file.exists()) {
        if (file.delete()) {
            System.out.println("파일이 삭제되었습니다.");
            return true;
        } else {
            System.out.println("파일 삭제에 실패했습니다.");
            return false;
        }
    } else {
        System.out.println("파일이 존재하지 않습니다.");
        return false;
    }
}





	

}