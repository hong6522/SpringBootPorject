package fc.db;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShippingMapper {

	ArrayList<ShippingDTO> todayList(ShippingDTO dto);
	
	ArrayList<ShippingDTO> MainList(ShippingDTO dto);
	
	ArrayList<ShippingDTO> completeList(ShippingDTO dto);
	
	ArrayList<ShippingDTO> beforeList(ShippingDTO dto);
	
	ArrayList<ShippingDTO> ingList(ShippingDTO dto);
	
	ArrayList<ShippingDTO> afterList(ShippingDTO dto);
	
	ArrayList<ShippingDTO> myPageMainList(MemberDTO dto);
	
	ArrayList<ShippingDTO> shDTO(ShippingDTO dto);
	
	ShippingDTO myPageCompleteList(MemberDTO dto);
	
	int RmyPageBefore(MemberDTO dto);
	
	int BasUpdate(ShippingDTO dto);
	
	int RmyPageAfter(MemberDTO dto);
	
	int reviewModify(ShippingDTO dto);
	
	ShippingDTO reviewSel(ShippingDTO dto);
	
	ShippingDTO myPageIngList(MemberDTO dto);
	
	ShippingDTO myPageCalList(MemberDTO dto);
	
	ShippingDTO myPageDecideList(MemberDTO dto);
	
	ShippingDTO myPageExchangeRequestList(MemberDTO dto);
	
	ShippingDTO myPageExchangeComList(MemberDTO dto);
	
	ShippingDTO myPageRefundRequestList(MemberDTO dto);
	
	ShippingDTO myPageRefundComList(MemberDTO dto);
	
	void Order_insert(ShippingDTO dto);
	
	void basOrder_insert(BasketDTO dto);
	
	ArrayList<ShippingDTO> myhistory(ShippingDTO dto);
	
	int update(ShippingDTO dto);
	
	int orderRefund(ShippingDTO dto);
	
	int sellDecide(ShippingDTO dto);
	
}
