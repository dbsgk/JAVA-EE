package board.bean;

public class BoardPaging {

	private int currentPage; 	// 현재 페이지
	private int pageBlock; 		// [이전][1][2][3][다음]
	private int pageSize;		// 1페이지당 5개씩
	private int totalA;			// 총 글 수
	private StringBuffer pagingHTML;
	
	public void makePagingHTML() {
		pagingHTML = new StringBuffer();
		
		int totalP = (totalA + pageSize - 1) / pageSize;
		int startPage = (currentPage - 1) / pageBlock * pageBlock + 1;;
		int endPage = startPage + pageBlock - 1;
		if(endPage > totalP)
			endPage = totalP;
		
		if(startPage > pageBlock)
			pagingHTML.append("<td class='prep'><a href=\"boardList.do?pg=" + (startPage-1) + "\">이전</a></td>");
		
		for(int i = startPage; i <= endPage; i++) {
			if(i == currentPage) {
				pagingHTML.append("<td><a id='currentPaging' href='boardList.do?pg=" + i + "'>" + i + "</a></td>");
			}else {
				pagingHTML.append("<td><a id='paging' href='boardList.do?pg=" + i + "'>" + i + "</a></td>");
			}
		}
		
		if(endPage < totalP)
			pagingHTML.append("<td><a href=\"boardList.do?pg=" + (endPage+1) + "\">다음</a></td>");
		
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageBlock() {
		return pageBlock;
	}

	public void setPageBlock(int pageBlock) {
		this.pageBlock = pageBlock;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalA() {
		return totalA;
	}

	public void setTotalA(int totalA) {
		this.totalA = totalA;
	}

	public StringBuffer getPagingHTML() {
		return pagingHTML;
	}

	public void setPagingHTML(StringBuffer pagingHTML) {
		this.pagingHTML = pagingHTML;
	}
	
}
