<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">
	h2{
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
	select, input[type=text], input[type=password]{
		padding:4px;
		display:inline-block;
	} 
	.fieldName{
		text-align:center;
	}
	.t1{
		width:200px;
	}
	.tel{
		width:70px;
	}
	.addr{
		width:100%;
		margin-top:5px;
	}
	.btn{
		padding:4px;
	}

</style>

</head>
<body>
<h2>회원가입</h2>

<form name="writeForm" method="post" action="write.jsp">
<table>
	<tr>
		<td class="fieldName">이름</td>
		<td><input type="text" name="name" id="name" placeholder="이름 입력" class="t1"></td>
	</tr>
	<tr>
		<td class="fieldName">아이디</td>
		<td><input type="text" name="id" id="id" placeholder="아이디 입력" class="t1">
			<input type="button" value="중복체크" class="" onclick="idCheck()" style="padding:2px;"></td>
	</tr>
	<tr>
		<td class="fieldName">비밀번호</td>
		<td><input type="password" name="pwd" id="pwd" placeholder="비밀번호 입력" class="t1"></td>
	</tr>
	<tr>
		<td class="fieldName">재확인</td>
		<td><input type="password" name="pwdcheck" id="pwdcheck" placeholder="비밀번호 확인" class="t1"></td>
	</tr>
	<tr>
		<td class="fieldName">성별</td>
		<td><input type="radio" name="gender" value="0" checked>남
			<input type="radio" name="gender" value="1">여
		</td>
	</tr>
	<tr>
		<td class="fieldName">이메일</td>
		<td><input type="text" name="email1" style="width:100px;">@<input type="text" name="email2" placeholder="직접 입력">
		</td>
	</tr>
	<tr>
		<td class="fieldName">핸드폰</td>
		<td>
			<select name="tel1">
				<option value="010">010
				<option value="011">011
				<option value="016">016
				<option value="017">017
				<option value="019">019
			</select>-
			<input type="text" name="tel2" class="tel">-
			<input type="text" name="tel3" class="tel">
		</td>
	</tr>
	<tr>
		<td class="fieldName">주소</td>
		<td>
			<input type="text" name="zipcode" id="zipcode" style="width:80px;">
			<input type="button" value="우편번호검색" onclick="checkPost()"><br>
			<input type="text" name="addr1" id="addr1" class="addr" placeholder="주소"><br>
			<input type="text" name="addr2" id="addr2" class="addr" placeholder="상세주소">
		</td>
	</tr>
	<tr>
		<td colspan="2" style="text-align:center;">
			<input type="button" value="회원가입" class="btn" onclick="signUpCheck()">
			<input type="reset" value="다시작성" class="btn">
			<input type="button" value="로그인 페이지" class="btn" onclick="location.href='loginForm.jsp'";">
		</td>
	</tr>
	
</table>
</form>
</body>
<script type="text/javascript" src="../js/member.js"></script>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

</html>