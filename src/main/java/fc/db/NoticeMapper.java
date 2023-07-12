package fc.db;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface NoticeMapper {
	
	List<NoticeDTO> adNoticeList(NoticeDTO board);
	
	int adNoticeDelete(NoticeDTO board);
	
	NoticeDTO adNoticeDetail(NoticeDTO board);
	
	int adNoticeInsert(NoticeDTO board);
	
	int adNoticeModify(NoticeDTO board);
	
	List<NoticeDTO> nlist(CenterPData pd);
		
	NoticeDTO ndetail(NoticeDTO bdedsde);
	
	int ncnt(int no);
		
	int ntotalcount(); //리스트 갯수
		
	
	

	

}
