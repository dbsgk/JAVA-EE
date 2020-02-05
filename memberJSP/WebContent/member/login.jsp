<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="member.dao.MemberDAO" %>
<%
//데이터
String id = request.getParameter("id");
String pwd = request.getParameter("pwd");
		
//DB
MemberDAO memberDAO = MemberDAO.getInstance();
String name = memberDAO.login(id, pwd);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
request.setCharacterEncoding("utf-8");
request.setAttribute("name", name);//(변수명, 값)

%>

<%if(name!=null) {
	/* response.sendRedirect("loginOk.jsp?name="+URLEncoder.encode(name,"UTF-8")+"&id="+id); */
	
	//쿠키(쿠키는 클래스)
	/* Cookie cookie = new Cookie("memName", name);//("쿠키명, "값")
	cookie.setMaxAge(30*60);//쿠키살아있는 시간(시간) 시간은 초단위로; 시간설정안해두면 0으로 되어서 안된대.
	response.addCookie(cookie);//클라이언트에게 보내기
	
	Cookie cookie2 = new Cookie("memId",id);
	cookie2.setMaxAge(30*60);
	response.addCookie(cookie2); */
	
	//세션(세션은 인터페이스)
	//HttpSession session = request.getSession();//세션은 인터페이스라서 new안됨. 그리고 지금은 중복이라고 안되는데 왜냐하면 session이 jsp내장객체로 존재해서 이건 나중에 MVC할때 하자.
	session.setAttribute("memName", name);
	session.setAttribute("memId", id);
	
	
	
	response.sendRedirect("loginOk.jsp");
}else{
	response.sendRedirect("loginFail.jsp");
} %>
</body>
</html>












