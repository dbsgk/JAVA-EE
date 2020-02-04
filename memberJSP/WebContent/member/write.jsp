<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="member.dao.MemberDAO" %>
<%
//데이터
request.setCharacterEncoding("utf-8");
%>

<jsp:useBean id="memberDTO" class="member.bean.MemberDTO"/><!-- 데이터 가져오기  -->
<jsp:setProperty property="*" name="memberDTO"/>
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
	<input type="button" value="로그인" onclick="location.href='loginForm.jsp'">
<%}else{ %>
	회원가입 실패
<%} %>
</body>
</html>















