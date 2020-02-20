<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
입력하신 아이디 ${id}은(는) 사용 가능합니다.<br><!-- request.id인데 어차피 알아서 뒤져서 찾으니까 id만 입력해도 됨. 원중이 형은 sessionScope.id 나는 param.id -->
	<input type="button" value="사용하기" onclick="idUse('${id}')">
</body>
<script type="text/javascript" src="../js/member.js">
</script>
</html>