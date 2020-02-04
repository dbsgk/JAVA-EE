<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="member.bean.AddrDTO"%>
<%@page import="java.util.List"%>
<%@page import="member.dao.MemberDAO"%>
    
<jsp:useBean id="memberDTO" class="member.bean.MemberDTO" />
<jsp:setProperty property="*" name="memberDTO" />
<%
	boolean result = MemberDAO.getInstance().update(memberDTO);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%if(result){ %>
	회원정보를 성공적으로 수정했습니다.<br>
<%} else{ %>
	회원정보를 수정하는 데 실패했습니다.
<%} %>
</body>
</html>