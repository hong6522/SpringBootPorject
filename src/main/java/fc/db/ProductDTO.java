package fc.db;

import java.io.File;
import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Alias("pDTO")
@Data
public class ProductDTO {
	String kind,proKind,proName,proDetail,proSize,pf1,pf2,reg_dateStr;
	MultipartFile pf1Str,pf2Str;
	String color,proColor,sch,start,end;
	String sel = "모두 구매가능";
	String [] select= {"모두 구매가능","회원 구매가능"};
	Date reg_date;
	Integer num,supplyPrice,sellPrice,proCnt,startPrice,endPrice,memPoint;
	
//	String on,off,out,top,bot,sho,s,m,l,xl;
}
