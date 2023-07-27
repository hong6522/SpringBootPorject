package fc.db;



import java.util.List;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface MemberMapper {
	List<MemberDTO> mem(MemberDTO advcx);
	
	MemberDTO addetail(MemberDTO djsafsnj);
	
	int memDelete(MemberDTO dto);
	
	int changerank(MemberDTO gfsbf);
	
	MemberDTO login(MemberDTO dto);
	
	int mem_pwModify(MemberDTO dto);
	
	int join(MemberDTO dto);
	
	int mem_modify_addr(MemberDTO dto);
	
	int mem_modify_pw(MemberDTO dto);
	
	MemberDTO myPage(MemberDTO dto);
	
	boolean idChk(MemberDTO dto);
	
	List<ShippingDTO> orderlist(MemberDTO dsfsdgvfd);
	
	List<BasketDTO> basket(MemberDTO cfcgvhds);
}
