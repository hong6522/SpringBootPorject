package fc.db;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Alias("reviewDTO")
@Data
public class ReviewDTO {
	
	
	//MultipartFile upfile, upfile2 , upfile3;
	String fashion_Name , title , content , order_Code, id , sch, kind, upfile, upfile2 , upfile3 ;
	Integer star_num , review_cnt , no;
	Date reg_date;
	
	
//	public MultipartFile getupfile() {
//		return upfile;
//	}
//	public void setUpfile(MultipartFile upfile) {
//		this.upfile = upfile;
//	}
//	public MultipartFile getupfile2() {
//		return upfile2;
//	}
//	public void setUpfile2(MultipartFile upfile2) {
//		this.upfile2 = upfile2;
//	}
//	public MultipartFile getupfile3() {
//		return upfile2;
//	}
//	public void setUpfile3(MultipartFile upfile3) {
//		this.upfile3 = upfile3;
//	}
	
	
	public String getContentBr() {
		return content.replaceAll("\n", "<br/>");
	}
	

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	public String getReg_dateStr() {
		return sdf.format(reg_date);
	}	
	
	
	public void setReg_dateStr(String reg_date) {
		try {
			this.reg_date = sdf.parse(reg_date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
