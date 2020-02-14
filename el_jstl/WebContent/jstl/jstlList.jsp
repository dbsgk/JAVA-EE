<%@page import="java.net.URLEncoder"%>
<%@page import="com.sun.xml.internal.fastinfoset.Encoder"%>
<%@page import="com.sun.xml.internal.bind.v2.runtime.output.Encoded"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%response.setContentType("text/html; charset=utf-8");%>
<sql:query var="rs" dataSource="jdbc/oracle">
	select * from usertable
</sql:query>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1">
	<tr>
		<!-- 필드명 -->
		<c:forEach var="colName" items="${rs.columnNames }">
			<td width="100" align="center">
				${colName}
			</td>
		</c:forEach>
		<td width="150" align="center">비고</td>
	</tr>
		<!-- 필드 -->
	<c:forEach var="row" items="${rs.rowsByIndex}"> <!-- 행 -->
		<tr>
			<c:forEach var="col" items="${row}">
				<td align="center" >${col}</td>
			</c:forEach>
			<td align="center"> 
				<input type="button" value="수정" onclick="location.href='jstlModifyForm.jsp?id=${row[1]}'">
				<input type="button" value="삭제" onclick="location.href='jstlDelete.jsp?id=${row[1]}'">
				<!-- 데이터는 id만 넘긴다. 수정시 id 변경 금지 -->
			</td>	
		</tr>
	</c:forEach>
</table>
</body>
</html>