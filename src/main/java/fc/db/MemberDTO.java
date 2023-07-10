package fc.db;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Alias("mDTO")
@Data
public class MemberDTO {
	Integer age;
	String name,id,rank,memo,address1,kind,pid,pname,sch;
	boolean gender;
	Date joindate;
	
	public String getGenderStr() {
		
		if(gender) {
			return "남자";
		}else {
			return "여자";
		}
	}
}
