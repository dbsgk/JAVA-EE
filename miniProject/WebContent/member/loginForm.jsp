<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<h3>로그인</h3>

<form name="loginForm" method="post" action="../member/login.do">
<table id="table-login">
	<tr>
		<td><input type="text" name="loginId" id="loginId" placeholder="아이디 입력"><div id="div-loginId"></div></td>
	</tr>
	<tr>
		<td><input type="password" name="loginPwd" id="loginPwd" placeholder="비밀번호 입력"><div id="div-loginPwd"></div></td>
	</tr>
	<tr>
		<td class="btnField">
			<input type="button" class="btn" value="로그인" id="btn-login">
			<input type="button" class="btn" value="회원가입" onclick="location.href='../member/writeForm.do'">
		</td>
	</tr>

</table>
</form>

<script type="text/javascript" src="../js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="../js/member.js"></script>

