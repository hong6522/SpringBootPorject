package fc.db;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShippingMapper {

	ArrayList<ShippingDTO> todayList(ShippingDTO dto);
	
	ArrayList<ShippingDTO> MainList(ShippingDTO dto);
	
	ArrayList<ShippingDTO> beforeList(ShippingDTO dto);
	
	ArrayList<ShippingDTO> ingList(ShippingDTO dto);
	
	ArrayList<ShippingDTO> afterList(ShippingDTO dto);
	
	void Order_insert(ShippingDTO dto);
	
	void basOrder_insert(BasketDTO dto);
	
	ArrayList<ShippingDTO> myhistory(ShippingDTO dto);
	
	int update(ShippingDTO dto);
	
	int orderRefund(ShippingDTO dto);
	
}
