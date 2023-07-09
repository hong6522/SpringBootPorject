package fc.db;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Alias("bDTO")
@Data
public class BasketDTO {
	
	String id ;
	int price , totalPrice , cnt;
	Integer num;
}
