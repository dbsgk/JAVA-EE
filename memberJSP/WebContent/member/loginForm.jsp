<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<!-- memberServlet의 loginForm.html의 내용과 같다 -->
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form name="loginForm" method="post" action="/memberJSP/member/login.jsp">
<table border="1" cellspacing="0" cellpadding="5">
	<tr>
		<td align="center">아이디</td>
		<td><input type="text" name="id"></td>
	</tr>
	<tr>
		<td align="center">비밀번호</td>
		<td><input type="password" name="pwd" ></td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<input type="button" value="로그인" onclick="checkLoginForm()">&emsp;
			<input type="button" value="회원가입" onclick="location.href='./writeForm.jsp'">
														<!--   -->
		</td>
	</tr>
</table>
</form>
<script type="text/javascript" src="http://localhost:8080/memberJSP/js/member.js"></script>

</body>
</html>