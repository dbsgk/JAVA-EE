<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
	#id, #name{
		width:96%;
	}
	.addr{
		width:96%;
		margin-top:5px;
	}
	.btn{
		padding:4px;
	}

</style>

</head>
<body>
<h2>회원정보 수정</h2>

<form name="modifyForm" method="post" action="modify.do">
<table>
	<tr>
		<td class="fieldName">이름</td>
		<td><input type="text" name="modify_name" id="modify_name" value="${memberDTO.name}" class="t1"></td>
	</tr>
	<tr>
		<td class="fieldName">아이디</td>
		<td><input type="text" name="modify_id" id="modify_id" value="${memberDTO.id}" class="t1" readonly
			 style="background-color:#eee;"></td>
	</tr>
	<tr>
		<td class="fieldName">비밀번호</td>
		<td><input type="password" name="modify_pwd" id="modify_pwd" placeholder="비밀번호 입력" class="t1" onchange="passwordCheck()"></td>
	</tr>
	<tr>
		<td class="fieldName">재확인</td>
		<td><input type="password" name="modify_pwdcheck" id="modify_pwdcheck" placeholder="비밀번호 확인" class="t1" onchange="passwordCheck()">
			<span id="span_pwd"></span>
		</td>
	</tr>
	<tr>
		<td class="fieldName">성별</td>
		<td><input type="radio" name="gender" id="male" value="0" checked>남
			<input type="radio" name="gender" id="female" value="1">여
		</td>
	</tr>
	<tr>
		<td class="fieldName">이메일</td>
		<td><input type="text" name="email1" style="width:100px;"
				value="${memberDTO.email1}">@
			<input type="text" name="email2">
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
			<input type="text" name="tel2" class="tel" value="${memberDTO.tel2}">-
			<input type="text" name="tel3" class="tel" value="${memberDTO.tel3}">
		</td>
	</tr>
	<tr>
		<td class="fieldName">주소</td>
		<td>
			<input type="text" name="zipcode" id="zipcode" style="width:80px;"
				value="${memberDTO.zipcode}" readonly>
				
			<input type="button" value="우편번호검색" onclick="checkPost()"><br>
			
			<input type="text" name="addr1" id="addr1" class="addr" 
				value="${memberDTO.addr1}"><br>
				
			<input type="text" name="addr2" id="addr2" class="addr" 
				value="${memberDTO.addr2}">
		</td>
	</tr>
	<tr>
		<td colspan="2" style="text-align:center;">
			<!-- <input type="button" value="회원정보수정" class="btn" onclick = "updateCheck()" id="modifyFormBtn"> -->
			<input type="button" value="회원정보수정" class="btn" id="modifyFormBtn">
			<input type="reset" value="다시작성" class="btn">
		</td>
	</tr>
	
</table>
</form>
</body>
<script type="text/javascript" src="../js/member.js"></script>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">
	window.onload = function(){
		document.modifyForm.gender['${memberDTO.gender}'].checked = true;
		document.modifyForm.email2.value = '${memberDTO.email2}';
		document.modifyForm.tel1.value = '${memberDTO.tel1}';
	};
	function passwordCheck(){
		if(document.modifyForm.pwd.value == document.modifyForm.pwdcheck.value){
			document.getElementById('span_pwd').innerHTML = "<font color = 'blue'>" + "일치함</font>";
			document.modifyForm.submit();
		}else{
			document.getElementById('span_pwd').innerHTML = "<font color = 'red'>" + "불일치</font>";
		}
	}
</script>

</html>























