<%@page import="board.bean.BoardDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="boardDAO" class="board.dao.BoardDAO" />  
<%
/* List<BoardDTO> list = boardDAO.getList();
 */	

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
<script type="text/javascript">
window.onload = function() {
	alert("글작성 완료");
	location.href='boardList.jsp';
}
</script>
</html>