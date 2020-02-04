<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="member.bean.MemberDTO"%>
<%@page import="member.dao.MemberDAO"%>
<%request.setCharacterEncoding("UTF-8"); %>

<jsp:useBean id="memberDTO" class="member.bean.MemberDTO" />
<jsp:setProperty property="*" name="memberDTO" />
<%
	boolean result = MemberDAO.getInstance().insert(memberDTO);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%if(result) { %>
	회원가입 성공
<%} else{ %>
	회원가입 실패
<%} %>
</body>
</html>