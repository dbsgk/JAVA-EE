<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:useBean id="dataDTO" class="exam.bean.DataDTO" scope="session" />
<jsp:setProperty property="x" name="dataDTO" />
<jsp:setProperty property="y" name="dataDTO" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:getProperty property="x" name="dataDTO"/> + 
<jsp:getProperty property="y" name="dataDTO"/> = 
<%=dataDTO.getX()+dataDTO.getY() %>
<br><br>
<input type="button" value="곱구하기" onclick="location.href='mul.jsp'">
</body>
</html>
















