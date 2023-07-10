package fc.db;



import java.util.List;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface MemberMapper {
	List<MemberDTO> mem(MemberDTO advcx);
	
	MemberDTO addetail(MemberDTO djsafsnj);
	
	int changerank(MemberDTO gfsbf);
}
