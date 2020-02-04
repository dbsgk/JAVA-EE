<%@page import="member.bean.MemberDTO"%>
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
MemberDAO memberDAO = MemberDAO.getInstance();

String id = request.getParameter("id");
String pwd = request.getParameter("pwd");
String name = request.getParameter("name");

String gender = request.getParameter("gender");
String tel1 = request.getParameter("tel1");
String tel2 = request.getParameter("tel2");
String tel3 = request.getParameter("tel3");

String email1 = request.getParameter("email1");
String email2 = request.getParameter("email2");

String zipcode = request.getParameter("zipcode");
String addr1 = request.getParameter("addr1");
String addr2 = request.getParameter("addr2");

MemberDTO memberDTO = new MemberDTO(name, id, pwd, tel1, tel2, tel3, gender, email1, email2, zipcode, addr1, addr2);
boolean result = memberDAO.insert(memberDTO);
if(result){%>
	회원가입 성공
<%} else{ %>
	회원가입 실패
<%} %>


</body>
</html>