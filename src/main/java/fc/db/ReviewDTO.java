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
	String fassion_name , title , content , order_Code, id , sch, kind, upfile, upfile1 , upfile2 ;
	Integer star_num , review_cnt;
	public Integer no;
	Date reg_date;
	
	MultipartFile ff1, ff2 , ff3;
	
	
	
	
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
