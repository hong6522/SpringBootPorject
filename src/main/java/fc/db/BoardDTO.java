package fc.db;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Alias("bDTO")
@Data
public class BoardDTO {
	Integer no,cnt;
	String title,content,pid;
	
	
}
