package fc.db;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Alias("mDTO")
@Data
public class MemberDTO {
	Integer age;
	String name,tell,birth,email,id,pw,rank,gender,memo,address1,address2,address3,address4,address5,kind,pid,pname,sch;
	Date joindate;
	
	
	
}
