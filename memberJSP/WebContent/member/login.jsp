<%@page import="java.net.URLEncoder"%>
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
<%
request.setCharacterEncoding("utf-8");
request.setAttribute("name", name);//(변수명, 값)

%>

<%if(name!=null) {
	response.sendRedirect("loginOk.jsp?name="+URLEncoder.encode(name,"UTF-8")+"&id="+URLEncoder.encode(id,"UTF-8"));
}else{
	response.sendRedirect("loginFail.jsp");
} %>
</body>
</html>












