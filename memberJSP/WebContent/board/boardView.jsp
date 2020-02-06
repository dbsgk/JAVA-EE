<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="board.bean.BoardDTO" %>

<jsp:useBean id="boardDAO" class="board.dao.BoardDAO" />

<%
//데이터
String id = (String)session.getAttribute("memId");
int seq = Integer.parseInt(request.getParameter("seq"));

//DB
BoardDTO boardDTO = boardDAO.getBoard(seq);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>BoardView</h2>
<form name="boardWriteForm" method="post" action="boardWrite.jsp">
<table border="1">
	<tr>
		<td width="70" align="center">제목</td>
		<td>
			<input type="text"  name="subject"  value="<%=boardDTO.getSubject() %>"size="50" readonly>
		</td>
	</tr>

	<tr>
		<td align="center" width="70">내용</td>
		<td>
			<textarea name="content" rows="15" cols="40" readonly><%=boardDTO.getContent() %></textarea>
		</td>
	</tr>
	<tr>
		<td colspan="2" align="center">
		<input type="button" value="수정" onclick="checkBoardWriteForm()"> 
		<input type="button" value="삭제">
		<input type="button" value="목록" onclick="history.back(1)"></td>
	</tr>
</table>
</form>
</body>
</html>