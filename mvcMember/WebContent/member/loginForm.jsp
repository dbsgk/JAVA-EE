<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">
	h2, .btnField{
		text-align:center;
	}
	table{
		margin:10px auto;
		border-collapse:collapse;
	}
	td{
		padding:10px;
		border:1px solid #ddd;
	}
	input[type="text"], input[type="password"]{
		width:200px;
		padding:5px;
	}
	.fieldName{
		width:100px;
		text-align:center;
	}
	.btn{
		padding:4px;
	}
</style>

</head>
<body>
<h2>로그인</h2>

<form name="loginForm" method="post" action="/mvcMember/member/login.do">
<table>
	<tr>
		<td class="fieldName">아이디</td>
		<td><input type="text" name="id" id="id"></td>
	</tr>
	<tr>
		<td class="fieldName">비밀번호</td>
		<td><input type="password" name="pwd" id="pwd"></td>
	</tr>
	<tr>
		<td colspan="2" class="btnField">
			<input type="button" class="btn" value="로그인" onclick="loginCheck()">
			<input type="button" class="btn" value="회원가입" onclick="javascript:location.href='/mvcMember/member/writeForm.do'">
		</td>
	</tr>

</table>
</form>

</body>
<script type="text/javascript" src="../js/member.js"></script>

</html>