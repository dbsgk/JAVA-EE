<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
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
			ar[i].setMaxAge(0);
		}
		if(cookieName.equals("memId")){
			ar[i].setMaxAge(0);
		}
	}
} */
session.removeAttribute("memName");
session.removeAttribute("memId");
session.invalidate();
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
<script type="text/javascript">
	window.onload = function(){
		alert("로그아웃");
		location.href="../main/index.jsp";
	}
</script>
</html>