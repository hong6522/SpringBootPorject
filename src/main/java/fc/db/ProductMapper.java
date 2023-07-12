package fc.db;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface ProductMapper {
	List<ProductDTO> pro(ProductDTO advcx);
	
	List<ProductDTO> pro_select(ProductDTO advcx);
	
	
	ProductDTO pro_detail(ProductDTO advcx);
	
	int insert(ProductDTO desgfd);
	
	ProductDTO detail(ProductDTO bsgfd);
	
	int modify(ProductDTO afsdfsdd);
	
	void delete(ProductDTO afsdfssfdfgf);
}
