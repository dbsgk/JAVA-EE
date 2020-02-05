<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8"); %>
<%
String name=null;
String id=null;
//쿠키
// 특정 쿠키를 얻지 못하고 모든 쿠키를 얻어오기
/* Cookie[] ar = request.getCookies();
if(ar!=null){
	for(int i = 0; i < ar.length; i++){
		String cookieName = ar[i].getName(); // 쿠키명
		String cookieValue = ar[i].getValue(); // 값
		
		if(cookieName.equals("memName")){
			name = cookieValue;
		}
		if(cookieName.equals("memId")){
			id = cookieValue;
		}
	}
} */
name = (String) session.getAttribute("memName");
id = (String) session.getAttribute("memId");
 
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<a href="../main/index.jsp">
<jsp:include page="../main/logo.jsp" /></a>
<br>
<%=name %>님 환영합니다!<br>
<form method="post" action='modifyForm.jsp'>
	<input type="button" value="로그아웃" onclick="location.href='logout.jsp'">
	<input type="button" value="회원정보 수정" onclick="location.href='../main/logo.jsp'">
</form>
</body>
</html>