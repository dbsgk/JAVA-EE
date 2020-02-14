<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:requestEncoding value="UTF-8"/>
<sql:query var="rs" dataSource="jdbc/oracle">
	select * from usertable where id='${param['id']}'
</sql:query>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div>
<form name="writeForm" method="post" action="jstlModify.jsp">
<!-- rs.rowsByIndex -->
	<h2>회원정보 수정</h2>
	<table border="1" cellspacing="0">
	<c:forEach var="row" items="${rs.rowsByIndex }"><!-- 배열로 온다 -->
	<tr>
		<td>이름</td>
		<td><input type="text" name="name" value=""></td>
	</tr>
	</c:forEach>
		<tr> 
			<td align="center">이름</td>
			<td><input type="text" name="name" size="10" value="<c:out value="${rs.rows[0].name}"/>"></td>
		</tr>
		<tr>
			<td align="center">아이디</td>
			<td><input type="text" name="id" id="id" size ="12" value="<c:out value="${rs.rows[0].id}"/>" readonly></td>
		<tr>
			<td align="center">비밀번호</td>
			<td><input type="password" name="pwd" id="pwd" size ="15" value="<c:out value="${rs.rows[0].pwd}"/>"></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
			<input type="submit" value="수정">
			<input type="reset" name="reset" value="다시작성">
			</td>
		</tr>
	</table>
</form>
</div>
</body>
</html>