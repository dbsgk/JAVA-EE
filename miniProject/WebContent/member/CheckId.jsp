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
MemberDAO memberDAO = MemberDAO.getInstance();
if(!memberDAO.checkId(id)){ %>
	입력하신 아이디 <%=id %>은(는) 사용 가능합니다.<br>
	<input type="button" value="사용하기" onclick="idUse(<%=id %>)">
<%}else{ %>
	입력하신 아이디 <%=id %>은(는) 이미 사용중인 아이디입니다.<br>
	<form action="CheckId.jsp">
	<input type="text" name="id">
	<input type="submit" value="중복체크" >
	</form>
<% }%>


</body>
<script type="text/javascript" src="../js/member.js"></script>

</html>