package fc.db;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QnaMapper {
	
	List<QnaDTO> qlist(CenterPData pd);
	
	List<QnaDTO> adqlist(AdQnaPData pd);
	
	List<QnaDTO> adqlist2(QnaDTO qnadto);
	
	List<QnaDTO> myqnalist(String id);
	
	//QnaDTO myqnalist(QnaDTO bdedsde);
	
	
	QnaDTO qdetail(QnaDTO bdedsde);
	
	int qinsert(QnaDTO bdedsde);
	
	int qtotalcount(); //리스트 갯수

	void qcnt(int no);

	int qmodify(QnaDTO dto);
	
	int qdelete(int no);
	
	
    int pwchk(QnaDTO dto);
	
    int adreply (QnaDTO dto);
	
    int  admodify(QnaDTO dto);

}
