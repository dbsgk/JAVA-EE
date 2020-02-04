<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="member.dao.MemberDAO" %>
<%@ page import="java.io.PrintWriter" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
String id = request.getParameter("id");
String pwd = request.getParameter("pwd");

MemberDAO memberDAO = MemberDAO.getInstance();
String name = memberDAO.login(id,pwd);
response.setContentType("text/html; charset=UTF-8");
if(name!=null) {
%>
<%=name %>님 로그인
<%}else { %>
아이디 또는 비밀번호가 맞지 않습니다.
<%} %>
</body>
</html>