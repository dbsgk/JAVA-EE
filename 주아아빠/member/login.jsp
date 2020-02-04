<%@page import="member.dao.MemberDAO"%>
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
String id = request.getParameter("id");
String pwd = request.getParameter("pwd");

MemberDAO memberDAO = MemberDAO.getInstance();
String result = memberDAO.getMemberName(id, pwd);
%>
<% if(result!=null){ %> 
	<%=result %>님 환영합니다! <br>
	<!-- <input type="button" value="글쓰기" onclick="location.href='writeForm.jsp'">  -->
<%} else{ %>
	로그인 실패 <br>
	<input type="button" value="돌아가기" onclick="window.history.back()">
<%} %>
</body>
</html>