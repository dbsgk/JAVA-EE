<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ID중복확인</title>
</head>
<body>
입력하신 아이디 ${id }는 사용 가능합니다.<br>
<input type="button" value="사용하기" onclick="idUse('${id }')">
</body>
<script type="text/javascript" src="../js/member.js" ></script>
</html>