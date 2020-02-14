<%@page import="board.bean.BoardPaging"%>
<%@page import="java.util.List"%>
<%@page import="board.dao.BoardDAO"%>
<%@page import="board.bean.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
//데이터
request.setCharacterEncoding("utf-8");

//세션
String id = (String)session.getAttribute("memId");
if(session.getAttribute("memId")!=null){
	Cookie cookie = new Cookie("memHit", "0");
	    cookie.setMaxAge(0);
		response.addCookie(cookie);
}
%>
<html>
<head>
<meta charset="UTF-8">
<title>boardList.jsp</title>
</head>
<style>
/* <link rel="stylesheet" href="../css/member.css"> */
.boardA:link {color: black; text-decoration: none;}
.boardA:visited {color: black; text-decoration: none;}
.boardA:hover {color: blue; text-decoration: underline; font-weight: bold;}
.boardA:active {color: black; text-decoration: none;}

</style>

<body>
<!-- //페이징 처리 - 1페이지당 3개씩 -->
<%
	int pg = Integer.parseInt(request.getParameter("pg"));
	System.out.println(pg);
	
	BoardDAO boardDAO = BoardDAO.getInstance();
 	int endNum = pg*5;//  !!!paging!!!
	int startNum = endNum-4;//!!!paging!!!
	
	List<BoardDTO> list = boardDAO.getList(startNum, endNum);
	
	BoardPaging boardPaging = new BoardPaging();
	int totalA = boardDAO.getBoardTotalA();//총글수
	boardPaging.setCurrentPage(pg);
	boardPaging.setPageBlock(3);
	boardPaging.setPageSize(5);//리스트에 글 몇개씩 띄울건지에 따라 여기 변경.
	boardPaging.setTotalA(totalA);
	
	boardPaging.makePagingHTML();
	int totalP = (totalA+4)/5;//(총 글의 수 +2) /3
%>
<table border="1" cellpadding="5" frame="hsides" rules="rows">
	<!-- <tr style="border-bottom:1px solid black;"> -->
		<th >글번호</th><!-- 가운데, 굵게 -->
		<th>제목</th>
		<th>작성자</th>
		<th>작성일</th>
		<th>조회수</th>
	<!-- </tr> -->

<% if(list!=null){ %> 
	<%for(BoardDTO dto : list){	%>
		<tr onclick="isLogin('<%=dto.getSeq() %>','<%=id %>','<%= pg%>') ">
		
			<%-- <td width="70" align="center"><a class="boardA" href='boardView.jsp?seq=<%=dto.getSeq() %>'><%=dto.getSeq() %></a></td>
			<td width="300" align="center"><a class="boardA" href='boardView.jsp?seq=<%=dto.getSeq() %>'><%=dto.getSubject() %></a></td>
			<td width="100" align="center"><a class="boardA" href='boardView.jsp?seq=<%=dto.getSeq() %>'><%=dto.getId() %></a></td>
			<td width="200" align="center"><a class="boardA" href='boardView.jsp?seq=<%=dto.getSeq() %>'><%=dto.getLogtime() %></a></td>
			<td width="70" align="center"><a class="boardA" href='"boardView.jsp?seq="+<%=dto.getSeq() %>+"&id="+<%=id%>"'><%=dto.getHit() %></a></td> --%>
			
			<td width="70" align="center" ><a class="boardA" href="javascript:void(0)"><%=dto.getSeq() %></a></td>
			<td width="300" align="center"><a class="boardA" href="#"><%=dto.getSubject() %></a></td>
			<td width="100" align="center"><a class="boardA" href="#"><%=dto.getId() %></a></td>
			<td width="200" align="center"><a class="boardA" href="#"><%=dto.getLogtime() %></a></td>
			<td width="70" align="center"><a class="boardA" href="#"><%=dto.getHit() %></a></td>
			
		</tr>
	
	<%}//for	%>
<%}	//list if%>	
	
</table>
	
</div>
<div style="width:200px; height:150px; float:left;">
<jsp:include page="../main/logo.jsp"></jsp:include>
</div>
<div style="display:inline-block; width:400px; height:50px; float:left; text-align:center;">
<%=boardPaging.getPagingHTML()%>
</div>
</body>
<script type="text/javascript">
function isLogin(seq,id,pg){
	if(id=='null')
		alert("먼저 로그인하세요");
	else {
		location.replace("http://localhost:8080/memberJSP_yunha3/board/boardView.jsp?seq="+seq+"&pg="+pg);
	}
}
</script>
</html>