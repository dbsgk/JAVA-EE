<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Input2</title>
</head>
<body>
<h3>자바클래스의 메소드를 이용하여 계산</h3>
	<form name="calcForm" action="elResult2.jsp">
	<table>
		<tr>
			<td class="fieldname">X</td>
			<td><input type="text" name="x"></td>
		</tr>
		<tr>
			<td class="fieldname">Y</td>
			<td><input type="text" name="y"></td>
		</tr>
		<tr>
			<td colspan="2" class="buttonField"><input type="submit" class="btn" value="계산"> <input type="reset" class="btn"></td>
		</tr>
	</table>
	</form>
</body>
</html>