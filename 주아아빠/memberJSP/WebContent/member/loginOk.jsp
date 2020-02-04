<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8"); %>
<%

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%=request.getParameter("name") %>님 환영합니다!<br>
<form method="post" action='modifyForm.jsp'>
	<input type="hidden" name="id" value="<%=request.getParameter("id") %>">
	<input type="submit" value="회원정보 수정">
</form>
</body>
</html>