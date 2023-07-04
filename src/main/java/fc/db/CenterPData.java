package fc.db;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;

@Data
public class CenterPData {
    int limit = 3, pageLimit = 4, start, nowPage;
    int startPage, endPage, totalPage;
    public String sch = null;
    String kind;

    public CenterPData(HttpServletRequest request) {
		nowPage = 1;
		if(request.getParameter("nowPage")!=null) {
			nowPage = Integer.parseInt(request.getParameter("nowPage"));
		}
		
		sch = request.getParameter("sch");
		
		System.out.println("sch:"+sch);
		
		if( sch==null || sch.equals("null")) {
			sch = "";
		}

		System.out.println("ss"+kind);
		request.setAttribute("pd", this);
	}

    public void setTotal(int total) {
        totalPage = total / limit;
        if (total % limit != 0) {
            totalPage++;
        }

        start = (nowPage - 1) * limit;
        startPage = (nowPage - 1) / pageLimit * pageLimit + 1;
        endPage = startPage + pageLimit - 1;

        if (endPage > totalPage) {
            endPage = totalPage;
        }
    }

    public String getSch() {
		return sch;
	}

	public int getLimit() {
		return limit;
	}

	public int getStart() {
		return start;
	}
	
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}

	public int getNowPage() {
		return nowPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public int getTotalPage() {
		return totalPage;
	}
}