package fc.db;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SalesMapper {

	ArrayList<SalesDTO> outerList(SalesDTO dto);
	
	ArrayList<SalesDTO> shirtList(SalesDTO dto);
	
	ArrayList<SalesDTO> pantsList(SalesDTO dto);
	
	ArrayList<SalesDTO> shoesList(SalesDTO dto);
	
	ArrayList<Integer> outerMonth(String ss);
	
	ArrayList<Integer> shirtMonth(String ss);
	
	ArrayList<Integer> pantsMonth(String ss);
	
	ArrayList<Integer> shoesMonth(String ss);
	
}
