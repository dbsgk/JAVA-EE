<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="board.bean.BoardDTO" %>
<%@ page import="board.bean.BoardPaging" %>

<jsp:useBean id="boardDAO" class="board.dao.BoardDAO"/>

<%
String id = (String)session.getAttribute("memId");

//데이터
int pg = Integer.parseInt(request.getParameter("pg"));

//DB - 1페이지당 5개씩
int endNum = pg*5;
int startNum = endNum-4;

List<BoardDTO> list = boardDAO.boardList(startNum, endNum);

//페이징 처리
BoardPaging boardPaging = new BoardPaging();
int totalA = boardDAO.getBoardTotalA(); //총글수
boardPaging.setCurrentPage(pg);
boardPaging.setPageBlock(3);
boardPaging.setPageSize(5);
boardPaging.setTotalA(totalA);

boardPaging.makePagingHTML();

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardList.jsp</title>
<style type="text/css">
#subjectA:link { color: black; text-decoration: none; }
#subjectA:visited { color: black; text-decoration: none; }
#subjectA:hover { color: blue; text-decoration: underline; }
#subjectA:active { color: black; text-decoration: none; }

#paging {
	color: black;
	text-decoration: none;
	cursor: pointer;
}
#currentPaging {
	color: red;
	text-decoration: underline;
	cursor: pointer;
}
</style>
</head>
<body>
<table border="1" cellpadding="5" frame="hsides" rules="rows">
	<th>글번호</th><!-- 가운데, 굵게 -->
	<th>제목</th>
	<th>작성자</th>
	<th>작성일</th>
	<th>조회수</th>
<%if(list!=null){ %>
	<%for(BoardDTO boardDTO : list){ %>
		<tr>
			<td width="70" align="center"><%=boardDTO.getSeq() %></td>
			<td width="300">
			<a href="javascript:isLogin('<%=id %>',<%=boardDTO.getSeq() %>,<%=pg%>)" id="subjectA"><%=boardDTO.getSubject() %></a>
			</td>
			<td width="100" align="center"><%=boardDTO.getId() %></td>
			<td width="200" align="center"><%=boardDTO.getLogtime() %></td>
			<td width="70" align="center"><%=boardDTO.getHit() %></td>
		</tr>
	<%}//for %>
<%}//if %>
</table>
<div style="float: left; width: 70px;">
	<jsp:include page="../main/logo.jsp"/>
</div>
<!-- <div style="float: left;  border: 1px solid blue; width: 300px;"></div> -->
<div  style="display:inline-block; width: 700px; text-align: center;"><%=boardPaging.getPagingHTML() %></div>
</body>
<script type="text/javascript">
function isLogin(id,seq,pg){
	if(id=='null')
		alert("먼저 로그인하세요");
	else
		location.href="boardView.jsp?seq="+seq+"&pg="+pg;
}
</script>
</html>




















