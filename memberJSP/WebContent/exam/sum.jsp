<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="mul.jsp">
<%int x= Integer.parseInt(request.getParameter("x")); %>
<%int y= Integer.parseInt(request.getParameter("y")); %>
<input type="hidden" name="x" value="<%=x%>">
<input type="hidden" name="y" value="<%=y%>">
<%=x %> + <%=y %> = <%=x+y %><br>
<input type="button" value="곱구하기" onclick="location.href='http://localhost:8080/memberJSP/exam/mul.jsp?x=<%=x%>&y=<%=y%>'">
		<input type="submit" value="곱구하기 서브밋">
</form>
</body>
</html>