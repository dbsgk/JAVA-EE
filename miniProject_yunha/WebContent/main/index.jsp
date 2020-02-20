<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1" width="100%">
	<tr>
		<td colspan="2" align="center"><jsp:include page="../template/top.jsp"></jsp:include></td>
	</tr>
	<tr>
		<td width="10%" height="400" valign="top"><jsp:include page="../template/left.jsp"></jsp:include></td>
		<!-- body부분이 계속 바뀐다. 그래서 고정값 안해두고 변수로. --><td width="70%"><jsp:include page="${display}"></jsp:include></td>
	</tr>
	<tr>
		<td colspan="2"><jsp:include page="../template/bottom.jsp"></jsp:include></td>
	</tr>
</table>
</body>
</html>