package fc.db;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;

@Data
public class ListPData {
    int limit = 5, pageLimit = 5, Pstart, nowPage;
    int startPage, endPage, totalPage;
    public String sch = null;
    String kind;

    public ListPData(HttpServletRequest request) {
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

        Pstart = (nowPage - 1) * limit;
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

	public int getPstart() {
		return Pstart;
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