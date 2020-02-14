<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<br>
${sessionScope.memName }님 환영합니다!<br>
<form method="post" action='modifyForm.jsp'>
	<input type="button" value="로그아웃" onclick="location.href='/mvcMember/member/logout.do'">
	<input type="button" value="회원정보 수정" onclick="location.href='modifyForm.jsp'">
</form>
</body>
</html>