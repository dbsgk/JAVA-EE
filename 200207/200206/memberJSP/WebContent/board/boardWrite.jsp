<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
  
<jsp:useBean id="boardDAO" class="board.dao.BoardDAO"/>

<%
//데이터
request.setCharacterEncoding("utf-8");

String subject = request.getParameter("subject");
String content = request.getParameter("content");

//세션
String id = (String)session.getAttribute("memId");
String name = (String)session.getAttribute("memName");
String email = (String)session.getAttribute("memEmail");

Map<String, String> map = new HashMap<String, String>();
map.put("id", id);
map.put("name", name);
map.put("email", email);
map.put("subject", subject);
map.put("content", content);

//DB
boardDAO.boardWrite(map);

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardWrite.jsp</title>
</head>
<body>

</body>
<script type="text/javascript">
window.onload=function(){
	alert("글작성 완료");
	location.href='boardList.jsp?pg=1';
}
</script>
</html>


















