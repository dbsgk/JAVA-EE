package imageboard.bean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageboardPaging {

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
//			pagingHTML.append("<td class='prep'><a href=\"imageboardList.do?pg=" + (startPage-1) + "\">이전</a></td>");
			pagingHTML.append("[<span id='paging' onclick='imageboardPaging("+(startPage-1)+")'>이전</span>]");
		for(int i = startPage; i <= endPage; i++) {
			if(i == currentPage) {
//				pagingHTML.append("<td><a id='currentPaging' href='imageboardList.do?pg=" + i + "'>" + i + "</a></td>");
				pagingHTML.append("[<span id='currentPaging' onclick='imageboardPaging("+i+")'>" +i+ "</span>]");

			}else {
//				pagingHTML.append("<td><a id='paging' href='imageboardList.do?pg=" + i + "'>" + i + "</a></td>");
				pagingHTML.append("[<span id='paging' onclick='imageboardPaging("+i+")'>" +i+ "</span>]");

			}
		}
		
		if(endPage < totalP)
//			pagingHTML.append("<td><a href=\"imageboardList.do?pg=" + (endPage+1) + "\">다음</a></td>");
			pagingHTML.append("[<span id='paging' onclick='imageboardPaging("+(endPage+1)+")'>다음</span>]");

	}
}
