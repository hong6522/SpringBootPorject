package fc.db;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface ProductMapper {
	List<ProductDTO> pro(ProductDTO advcx);
	
	int insert(ProductDTO desgfd);
}
