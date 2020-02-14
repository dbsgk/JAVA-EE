<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	h3, div{
		text-align:center;
	}
</style>

</head>
<body>
<h3>** include directive **</h3>
<div><%@ include file="today.jsp" %> </div>

<h3>** include JSP tag **</h3>
<div><jsp:include page="image.jsp" /> </div>

<%
String name="홍길동";
%>
<div>
main.jsp name = <%=name %>
</div>
<h3>** 3초 뒤에 페이지 이동 **</h3>
<% response.setHeader("refresh", "1;url=input.jsp"); %>


</body>
</html>