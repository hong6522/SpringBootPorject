package fc.db;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Alias("baskDTO")
@Data
public class BasketDTO {
	
	String id ,proName , color , size , cate , address , bundle ,uid ,pf1 ,pf2 ,pf3 ,addDateStr;
	int price , totalPrice , cnt ,pno;
	Integer num;
	Date addDate;
}
