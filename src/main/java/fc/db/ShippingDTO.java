package fc.db;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Alias("shipDTO")
@Data
public class ShippingDTO {
	
	String order_ID, order_name, order_place, order_cate, order_product, order_shipping, order_state, order_dateStr ,
	today, schShpping, cancleStr,shippingChk, schNo , order_color , order_size;
	int order_no, order_cnt, order_price ,order_num;
	Date order_date, order_cancleDate;

}
