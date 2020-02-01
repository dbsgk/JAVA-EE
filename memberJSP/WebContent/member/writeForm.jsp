<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<!-- memberServlet의 writeForm.html의 내용과 같다 -->

<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<style>
	
</style>
<h2>회원가입</h2>
<body>
	<form action="http://localhost:8080/memberJSP/member/write.jsp" method="post" name="writeForm">
	<table border="1">
	<tr>
		<td width="100" align="center"> 이름</td>
		<td><input type="text" placeholder="이름 입력" name="name"></td>
	</tr>
	<tr>
		<td align="center">아이디</td>
		<td>
		<input type="text" name="id">
		<input type="button" value="중복체크" onclick="javascript:checkId()">
		</td>
	</tr>
	<tr>
		<td align="center">비밀번호</td>
		<td><input type="text" name="pwd"></td>
	</tr>
	<tr>
		<td align="center">재확인</td>
		<td><input type="text" name="repwd"></td>
	</tr>
	<tr>
		<td align="center">성별</td>
		<td>
			<input type="radio" name="gender" value="남성" checked>남 
			<input type="radio" name="gender" value="여성">여
		</td>
	</tr>
	<tr>
		<td align="center">이메일</td>
		<td>
			<input type="text" name="email1">@
			<input type="text" name="email2" list="email2" placeholder="직접입력">
			<datalist id="email2">
				<option value="gmail.com">
				<option value="hanmail.net">
				<option value="naver.com">
			</datalist>
		</td>
	</tr>
	<tr>
		<td align="center">핸드폰</td>
		<td>
		<select id='tel1' name='tel1'>
			<option value='010'>010</option>
			<option value='011'>011</option>
			<option value='016'>016</option>
			<option value='017'>017</option>
			<option value='019'>019</option>
		</select>-
		<input type="text" name="tel2" size="10">-
		<input type="text" name="tel3" size="10">
		</td>
	</tr>
	<tr>
		<td rowspan="3" align="center">주소</td>
		<td>
		<input type="text" name="zipcode" id="zipcode" size="5">
		<input type="button" value="우편번호검색" onclick="checkPost()">
		</td>
	</tr>
	<tr>
		<td><input name="addr1" id="addr1" type="text" placeholder="주소" ></td>
	</tr>
	<tr>
		<td><input name="addr2" id="addr2" type="text" placeholder="상세 주소"></td>
	</tr>
	<tr>
		<td colspan="2" align="center">
		<input type="button" onclick="checkWriteForm()" value="회원가입">
		<input type="reset" value="다시작성">
		</td>
	</tr>
	</table>
	</form>
</body>
<script type="text/javascript" src="http://localhost:8080/memberJSP/js/member.js"></script>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</html>