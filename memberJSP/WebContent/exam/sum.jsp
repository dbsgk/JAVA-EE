<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:useBean id="dataDTO" class="exam.bean.DataDTO" scope="session" /> <%-- 로그인 정보가 유지될 때까지 --%>
<jsp:setProperty property="x" name="dataDTO" />
<jsp:setProperty property="y" name="dataDTO" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	table{
		margin:0px auto;
		width:100%;
		border:0px solid #ddd;
		border-collapse:collapse;
	}
	td{
		border:0px solid #ddd;
		font-size:12pt;
		padding:4px;
		text-align:center;
	}

</style>

</head>
<body>
<table>
	<tr>
		<td>
			<jsp:getProperty property="x" name="dataDTO" /> + <jsp:getProperty property="y" name="dataDTO" /> = 
			<%=dataDTO.getX() + dataDTO.getY()%>
		</td>
	</tr>
	<tr>
		<td><input type="button" value="곱 구하기" onclick="location.href='mul.jsp'"></td>
	</tr>
</table>
</body>
</html>