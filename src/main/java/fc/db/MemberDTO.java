package fc.db;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Alias("mDTO")
@Data
public class MemberDTO {
	Integer age;
	String name,tell,birth,id,pw,rank,memo,address1,address2,address3,address4,address5,kind,pid,pname,sch,joinDateStr;
	boolean gender;
	Date joindate;
	
	public String getGenderStr() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");
		if(gender) {
			return "남자";
		}else {
			return "여자";
		}
	}
	
}
