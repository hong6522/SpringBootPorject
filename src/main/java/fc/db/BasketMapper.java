package fc.db;



import java.util.List;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface BasketMapper {
	
	List<BasketDTO> basket_list(MemberDTO dto);
	
	void add_basket(BasketDTO dto);
	
}
