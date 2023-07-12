package fc.db;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Alias("reDTO")
@Data
public class RefundDTO {

	int orderNo;
	String re_orderID, re_cate, re_name, re_state, re_reason, re_answer, re_impossible, YN;
	
}
