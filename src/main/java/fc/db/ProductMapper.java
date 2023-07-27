package fc.db;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface ProductMapper {
	List<ProductDTO> pro(ProductDTO pd, ListPData pdDTO);
	
	List<ProductDTO> pro_select(ProductDTO advcx);
	
	ProductDTO pro_detail(ProductDTO advcx);
	
	int insert(ProductDTO desgfd);
	
	ProductDTO detail(ProductDTO bsgfd);
	
	int modify(ProductDTO afsdfsdd);
	
	void delete(ProductDTO afsdfssfdfgf);
	
	int prdDelete(ProductDTO dto);
	
	List<ProductDTO> propagelist(@Param("pd") AdQnaPData pd,@Param("pDTO") ProductDTO dto);
	int protalcount(); //리스트 갯수
	
}
