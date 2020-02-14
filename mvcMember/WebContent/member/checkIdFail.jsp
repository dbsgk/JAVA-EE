<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
입력하신 아이디 ${id }은(는) 이미 사용중인 아이디입니다.
<form action="/mvcMember/member/checkId.do">
	<input type="text" name="id">
	<input type="submit" value="중복체크" >
	</form>
</body>
</html>