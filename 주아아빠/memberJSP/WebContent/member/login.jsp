<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="member.bean.MemberDTO"%>
<%@page import="member.dao.MemberDAO"%>
<%request.setCharacterEncoding("UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
String id = request.getParameter("id");
String pwd = request.getParameter("pwd");

MemberDAO memberDAO = MemberDAO.getInstance();
String name = memberDAO.getMemberName(id, pwd);
%>

<% if(name!=null){  
	// response.sendRedirect("loginOk.jsp?name=" + URLEncoder.encode(name, "UTF-8") + "&id="+id);	
	
	// 쿠키
	/* Cookie cookie = new Cookie("memName", name);
	cookie.setMaxAge(30); // 초 단위
	response.addCookie(cookie); // 클라이언트에게 보내기
	
	Cookie cookie2 = new Cookie("memId", id);
	cookie2.setMaxAge(30);
	response.addCookie(cookie2); */
	
	// 세션
	//HttpSession session = request.getSession();
	MemberDTO memberDTO = memberDAO.getMember(id);
	
	session.setAttribute("memName", name);
	session.setAttribute("memId", id);
	session.setAttribute("memEmail", memberDTO.getEmail1() + "@" + memberDTO.getEmail2());
	
	response.sendRedirect("loginOk.jsp?");
}else{ 
	response.sendRedirect("loginFail.jsp");
} %> 
</body>
</html>