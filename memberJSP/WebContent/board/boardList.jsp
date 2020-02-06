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
%>
<html>
<head>
<meta charset="UTF-8">
<title>boardList.jsp</title>
</head>
<style>
<link rel="stylesheet" href="../css/member.css">
</style>

<body>
<!-- //페이징 처리 - 1페이지당 3개씩 -->
<%
	
	int pg = Integer.parseInt(request.getParameter("pg"));
	System.out.println(pg);
	
	BoardDAO boardDAO = BoardDAO.getInstance();
 	int endNum = pg*5;//  !!!paging!!!
	int startNum = endNum-4;//!!!paging!!!
	int totalA = boardDAO.getTotalA();
	int totalP = (totalA+4)/5;//(총 글의 수 +2) /3  
	List<BoardDTO> list = boardDAO.getList(startNum, endNum);
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
		<tr onclick="checkBoardLogin('<%=dto.getSeq() %>','<%=id %>') ">
		
			<%-- <td width="70" align="center"><a class="boardA" href='boardView.jsp?seq=<%=dto.getSeq() %>'><%=dto.getSeq() %></a></td>
			<td width="300" align="center"><a class="boardA" href='boardView.jsp?seq=<%=dto.getSeq() %>'><%=dto.getSubject() %></a></td>
			<td width="100" align="center"><a class="boardA" href='boardView.jsp?seq=<%=dto.getSeq() %>'><%=dto.getId() %></a></td>
			<td width="200" align="center"><a class="boardA" href='boardView.jsp?seq=<%=dto.getSeq() %>'><%=dto.getLogtime() %></a></td>
			<td width="70" align="center"><a class="boardA" href='"boardView.jsp?seq="+<%=dto.getSeq() %>+"&id="+<%=id%>"'><%=dto.getHit() %></a></td> --%>
			
			<td width="70" align="center" ><a class="boardA" href="#" ><%=dto.getSeq() %></a></td>
			<td width="300" align="center"><a class="boardA" href='boardView.jsp?seq=<%=dto.getSeq() %>'><%=dto.getSubject() %></a></td>
			<td width="100" align="center"><a class="boardA" href='boardView.jsp?seq=<%=dto.getSeq() %>'><%=dto.getId() %></a></td>
			<td width="200" align="center"><a class="boardA" href='boardView.jsp?seq=<%=dto.getSeq() %>'><%=dto.getLogtime() %></a></td>
			<td width="70" align="center"><a class="boardA" href='"boardView.jsp?seq="+<%=dto.getSeq() %>+"&id="+<%=id%>"'><%=dto.getHit() %></a></td>
			
		</tr>
	
	<%}//for	%>
<%}	//list if%>	
	
</table>
	
</div>
<div style="width:200px; height:150px; border:1px solid red; float:left;">
<jsp:include page="../main/logo.jsp"></jsp:include>
</div>
<div style="width:200px; height:150px; border:1px solid blue; float:left;">
파란색
</div>
</body>
<script type="text/javascript">
function checkBoardLogin(seq,id){
	if(id=="null")
		alert("먼저 로그인하세요");
	else {
		location.replace("http://localhost:8080/memberJSP/board/boardView.jsp?seq="+seq);
	}
}
</script>
</html>