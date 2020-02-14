<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="boardDAO" class="board.dao.BoardDAO"/>
<%
request.setCharacterEncoding("UTF-8");
String seq = request.getParameter("seq");
String pg = request.getParameter("pg");
String subject = request.getParameter("subject");
String content = request.getParameter("content");

Map<String, String> map = new HashMap<String, String>();
map.put("seq", seq);
map.put("subject", subject);
map.put("content", content);
boardDAO.boardModify(map);
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
window.onload=function(){
	alert('글수정 완료');	
	location.href='boardView.jsp?seq='+seq+'&pg='+pg;
}
</script>
</html>