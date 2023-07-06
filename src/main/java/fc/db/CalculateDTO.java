package fc.db;

import java.sql.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Alias("calDTO")
@Data
public class CalculateDTO {

	String cal_cate, cal_name, cal_kind, cal_retext, cal_size, cal_color ,refundStr, sch, beforeDate, afterDate;
	Integer cal_oriPri, cal_celPri, cal_code, cal_cnt;
	Boolean cal_refund;
	Date cal_date;
	
	
	
}
