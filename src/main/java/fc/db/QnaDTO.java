package fc.db;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Alias("qnaDTO")
@Data
public class QnaDTO {

	
	public Integer no , qna_cnt;
	boolean secret_chk;

	
	String title , type , content , id , getAnswer , sch , kind ,admin_Id , pw ;
	Date reg_date , answer_Date;

	
	
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

