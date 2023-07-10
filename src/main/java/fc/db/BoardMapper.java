package fc.db;



import java.util.List;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface BoardMapper {
	List<BoardDTO> boardlist(BoardDTO board);
	
	int insert(BoardDTO board);

	BoardDTO detail(BoardDTO board);
	
	int modify(BoardDTO board);

	int delete(BoardDTO board);



}
