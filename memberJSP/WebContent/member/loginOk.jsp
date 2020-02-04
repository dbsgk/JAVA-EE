<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
request.setCharacterEncoding("utf-8");
String name = request.getParameter("name");
String id = request.getParameter("id");

%>
<form action="modifyForm.jsp">
<%=name %>님 로그인
	<input type="hidden" name="id" value="<%=id%>">
	<input type="submit" value="회원정보수정" >
</form>
</body>
</html>