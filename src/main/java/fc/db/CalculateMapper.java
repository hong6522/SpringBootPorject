package fc.db;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CalculateMapper {

	List<CalculateDTO> list(CalculateDTO dto);
	
}
