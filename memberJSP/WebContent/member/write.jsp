<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="member.dao.MemberDAO" %>
<%
request.setCharacterEncoding("utf-8");
%>

<jsp:useBean id="memberDTO" class="member.bean.MemberDTO" />
<jsp:setProperty property="*" name="memberDTO" />

<%
//DB
MemberDAO memberDAO = MemberDAO.getInstance();
int su = memberDAO.write(memberDTO);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%if(su==1){ %>
	회원가입 성공
<%}else{ %>
	회원가입 실패
<%} %>
</body>
</html>















