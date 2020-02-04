<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="member.dao.MemberDAO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
String id = request.getParameter("id");

MemberDAO memberDAO = MemberDAO.getInstance();
boolean exist = memberDAO.isExistId(id);

response.setContentType("text/html;charset=UTF-8");//모든 컨텐츠타입을 html, utf-8로 알겠다.
%>
<% if(exist) {%>
<form method='get' action='http://localhost:8080/memberJSP/member/checkId.jsp'>
<%=id %>는(은) 사용 불가능<br><br><br>
아이디
<input type='text' name='id'>
<input type='submit' value='중복체크'>
<%}else if(!exist) {%>
<%=id %>는(은) 사용 가능 <br>
<input type='button' value='사용하기' onClick="checkIdClose('<%=id%>')"> 
<%}%>
</form>
<script type="text/javascript" src='/memberJSP/js/member.js'>
</script>
</body>
</html>