<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method="get" action="http://localhost:8080/testJSP/exam/result.jsp">  
<table border="1">
	<tr>
		<td width="50" align="center">X</td>
		<td>
		<input type="text" size="10" name="x">
		</td>
	</tr>
	<tr>
		<td align="center">Y</td>
		<td>
		<input type="text" size="10" name="y">
		</td>
	</tr>
	<tr>
		<td colspan="2">
		<input type="submit" value="계산">
		<input type="reset" value="취소">
		</td>
		
	</tr>
</table>
</form>
</body>
</html>