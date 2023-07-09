package fc.db;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Alias("salDTO")
@Data
public class SalesDTO {

	String sal_cate, sal_name, sal_dateStr, schDate;
	int sal_cnt, sal_sPri, sal_oPri, sal_bPri;
	Date sal_date;
	
}
