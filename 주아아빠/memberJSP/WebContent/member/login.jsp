<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="member.bean.MemberDTO"%>
<%@page import="member.dao.MemberDAO"%>
<%request.setCharacterEncoding("UTF-8"); %>

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
//String name = memberDAO.getMemberName(id, pwd);
String name = memberDAO.getMemberName(id, pwd);
%>

<% if(name!=null){  
	response.sendRedirect("loginOk.jsp?name=" + URLEncoder.encode(name, "UTF-8") + "&id="+id);	
}else{ 
	response.sendRedirect("loginFail.jsp");
} %> 
</body>
</html>