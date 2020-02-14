<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1" width="50%"><!-- width="50%" 화면의 반을 차지해라 -->
	<tr>
		<th>표현식</th>
		<th>값</th>
	</tr>
	<tr>
		<td align="center">\
		${25+3}</td><!-- 이 모양 그대로 출력 -->
		<td align="center">${25+3}</td><!-- java코드로 바꿀 필요없이 바로 값 출력 -->
	</tr>
	<tr>
		<td align="center">\${25/3}</td>
		<td align="center">${25/3}</td><!-- 웹은 정수 실수 안나눔, 그냥 결과값 나옴 -->
	</tr>
	<tr>
		<td align="center">\${25 div 3 }</td>
		<td align="center">${25 div 3 }</td>
	</tr>
	<tr>
		<td align="center">\${25 % 3 }</td>
		<td align="center">${25 % 3 }</td><!-- 나머지 -->
	</tr>
	<tr>
		<td align="center">\${25 mod 3 }</td>
		<td align="center">${25 mod 3 }</td>
	</tr>
	<tr>
		<td align="center">\${25 < 3 }</td>
		<td align="center">${25 < 3 }</td>
	</tr>
	<tr>
		<!-- > gt, < lt, >= ge, <= le, == eq, != ne  이렇게 글자로도 많이 사용-->
		<td align="center">\${25 ne 3 }</td>
		<td align="center">${25 ne 3 }</td>
	</tr>
	<tr>
		<td align="center">\${header['host'] }</td>
		<td align="center">${header['host']}</td>
	</tr>
	<tr>
		<td align="center">\${header.host}</td>
		<td align="center">${header.host}</td>
	</tr>
</table>
</body>
</html>

<%=25+36%>
out.println(25+36);
컴파일
매번 톰캣 재가동 되니까 이제 ${25+36}으로 바꿔줘라.
 