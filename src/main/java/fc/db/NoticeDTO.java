package fc.db;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Alias("noticeDTO")
@Data
public class NoticeDTO {


	String title, content, photoFile, id , sch, kind ;
	Integer notice_cnt;
	public Integer no;
	Date reg_date;
	
	public String getphotoFile() {
		if(photoFile==null||photoFile.equals("null")) {
			return "";
		}
		return photoFile;
	}
	
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
