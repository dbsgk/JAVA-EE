<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
request.setCharacterEncoding("utf-8");
String name = null;
String id = null;

//쿠키
//쿠키특징: 특정 쿠키를 얻지 못하고 모든 쿠키를 얻어오기
/* Cookie[] ar = request.getCookies();
if(ar!=null){
	for(int i=0;i<ar.length;i++){
		 String cookieName = ar[i].getName();//쿠키명
		String cookieValue = ar[i].getValue();//값
		
		System.out.println("쿠키명 = "+cookieName);
		System.out.println("쿠키값 = "+cookieValue);
		System.out.println("<br>"); 
		if(ar[i].getName().equals("memName"))
			name = ar[i].getValue();
		if(ar[i].getName().equals("memId"))
			id = ar[i].getValue();
	}//for
}*/
//세션
name = (String)session.getAttribute("memName");
			
%>
<form action="modifyForm.jsp">
<jsp:include page="../exam/img.jsp"/>
<%=name %>님 로그인<br>
<input type="button" value="로그아웃" onclick="location.href='logout.jsp'">
	<input type="button" value="회원정보수정" onclick="location.href='modifyForm.jsp'">
</form>
</body>
</html>