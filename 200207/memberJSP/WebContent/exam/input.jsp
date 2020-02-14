<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method="get" action="sum.jsp">
<table border="1" cellspacing="0" cellpadding="3">
	<tr>
		<td width="50" align="center">X</td>
		<td><input type="text" name="x"></td>
	</tr>
	<tr>
		<td align="center">Y</td>
		<td><input type="text" name="y"></td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<input type="submit" value="합구하기">
			<input type="reset" value="취소">
		</td>
	</tr>
</table>
</form>

</body>
</html>