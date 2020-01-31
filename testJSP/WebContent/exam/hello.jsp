<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%!
//전역, 1번(서버시작시)
String name = "홍길동";
int age=25;
%>
<%
//지역, 요청(호출)시마다
age++;
%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- Hello JSP!!<br> html주석-->
<%-- 안녕하세요 JSP!!<br> jsp주석--%>

나의 이름은 <%= name %> 입니다.<br>
<!-- 내 나이는 <%= age %>살 입니다.<br> -->

<%-- <% out.println("내 나이는 "+age+"살 입니다."); %> --%>
<!-- jsp 내장객체라서 out.println먹힘 -->
</body>
</html>