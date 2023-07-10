package fc.db;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RefundMapper {

	ArrayList<RefundDTO> waitList(RefundDTO dto);
	
	ArrayList<RefundDTO> possList(RefundDTO dto);
	
	ArrayList<RefundDTO> impoList(RefundDTO dto);
	
	RefundDTO detail(RefundDTO dto);
	
	int update(RefundDTO dto);
	
}
