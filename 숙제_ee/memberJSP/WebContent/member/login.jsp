<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="member.dao.MemberDAO" %>
<%
//데이터
String id = request.getParameter("id");
String pwd = request.getParameter("pwd");
		
//DB
MemberDAO memberDAO = MemberDAO.getInstance();
String name = memberDAO.login(id, pwd);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%if(name!=null) { %>
	<%=name %>님 로그인
	
<%}else{ %>
	로그인 실패
	
<%} %>
</body>
</html>












