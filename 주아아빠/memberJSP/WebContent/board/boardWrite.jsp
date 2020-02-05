<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="boardDTO" class="board.bean.BoardDTO" />
<jsp:useBean id="boardDAO" class="board.dao.BoardDAO" />
<jsp:setProperty property="*" name="boardDTO"/>
<%
boolean result = boardDAO.insert(boardDTO);
System.out.println(boardDTO.getId());
System.out.println(boardDTO.getName());
System.out.println(boardDTO.getEmail());
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%if(result){ %>
	글 작성이 완료되었습니다.
<%} %>

<script>
 
</script>
</body>
</html>