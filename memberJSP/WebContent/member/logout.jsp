<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//쿠키
/* Cookie[] ar = request.getCookies();
if(ar!=null){
	for(int i=0;i<ar.length;i++){
		if(ar[i].getName().equals("memName")){
			ar[i].setMaxAge(0);//쿠키 삭제
			response.addCookie(ar[i]);//클라이언트에게 보내기
		}
		if(ar[i].getName().equals("memId")){
			ar[i].setMaxAge(0);//쿠키 삭제
			response.addCookie(ar[i]);//클라이언트에게 보내기
		}
	}//for
} */

//세션
session.removeAttribute("memName");//이렇게 하나씩 없애도 되고
session.removeAttribute("memId");

session.invalidate();//무효화 ; 이렇게 한번에 없애도 됨.
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
로그아웃
</body>

<script type="text/javascript">
window.onload= function() {
	alert("로그아웃");
	location.href="../main/index.jsp";
}
</script>
</html>