<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="boardDTO" class="board.bean.BoardDTO" />
<jsp:useBean id="boardDAO" class="board.dao.BoardDAO" />
<jsp:setProperty property="*" name="boardDTO"/>

<%
	//DB
boolean result = boardDAO.boardWrite(boardDTO);
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%if(result){ %>
	글 작성이 완료되었습니다.<br>
	<input type="button" value="글 목록" onclick="location.href='boardList.jsp?pg=1'">
<%} %>

<script>
 
</script>
</body>
</html>