<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:useBean id="dataDTO" class="exam.bean.DataDTO" scope="page"/><!-- scope="session"로그인 정보가 유지될 떄까지 쓴다.  -->
<!-- useBean안쓰는 이유: 기본생성자밖에 처리하지 못해서 안쓰게 됨. -->
<jsp:setProperty property="x" name="dataDTO"/><!-- setX를 property="x"라 말한다. -->
<jsp:setProperty property="y" name="dataDTO"/><!-- setY를 property="y"라 말한다. -->

<jsp:getProperty property="x" name="dataDTO"/>+
<jsp:getProperty property="y" name="dataDTO"/>= 
<%=dataDTO.getX()+dataDTO.getY() %><br>

<input type="button" value="곱구하기" onclick="location.href='http://localhost:8080/memberJSP/exam/mul.jsp?x=<%=dataDTO.getX()%>&y=<%=dataDTO.getY() %>'">
		<input type="submit" value="곱구하기 서브밋">
</body>
</html>