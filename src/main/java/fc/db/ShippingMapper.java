package fc.db;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShippingMapper {

	ArrayList<ShippingDTO> todayList(ShippingDTO dto);
	
}
