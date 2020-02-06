<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>main</title>
</head>
<body>
<h3>** include directive **</h3> <!-- include directive: include 파일과 나를 합하는 거라서 같은 변수 있으면 컴파일 에러가 뜸 -->
<%
String name="홍길동";
%>
<%@ include file="today.jsp" %>


<h3>** include JSP tag **</h3><!-- include JSP tag: 각각 컴파일 거라서 같은 변수 있어도 컴파일 에러가 안뜸 -->
<jsp:include page="img.jsp"/>
<br>
main.jsp name = <%= name %>

<h3>** 3초 뒤에 페이지 이동</h3>
<% response.setHeader("Refresh", "3;url=input.jsp"); %><!-- url=내가 원하는 파일의 위치 -->

<br>
*******************URI와 URL차이점*******************************<br>
URI<br>
http://localhost:8080/memberJSP/exam/input.jsp?apple="사과""<br>
<br>
URL -> 파일 위치<br>
http://localhost:8080/memberJSP/exam/input.jsp<br>
***************************************************************<br>
</body>
</html>