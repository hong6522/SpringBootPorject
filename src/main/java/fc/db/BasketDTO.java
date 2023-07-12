package fc.db;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Alias("baskDTO")
@Data
public class BasketDTO {
	
	String id ,proName , color , size , cate , address , bundle ,uid;
	int price , totalPrice , cnt ,pno;
	Integer num;
}
