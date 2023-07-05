package fc.db;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface ReviewMapper {
	
	List<ReviewDTO> rlist(CenterPData pd);
	
	List<ReviewDTO> myreviewlist(String id);
		
	ReviewDTO rdetail(ReviewDTO bdedsde);
	
	int rinsert(ReviewDTO bdedsde);
	
	int rtotalcount(); //리스트 갯수

	void rcnt(int no);

	int rmodify(ReviewDTO dto);
	
	int rdelete(ReviewDTO dto);
	

}
