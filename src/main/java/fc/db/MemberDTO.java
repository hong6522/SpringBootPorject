package fc.db;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Alias("mDTO")
@Data
public class MemberDTO {
	Integer age;
	String name,id,rank,gender,memo,address1,kind,pid,pname,sch;
	Date joindate;
	
}
