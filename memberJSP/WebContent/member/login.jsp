<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="member.dao.MemberDAO" %>
<%@ page import="member.bean.MemberDTO" %>
<%@ page import="java.net.URLEncoder" %>
<%
//데이터
String id = request.getParameter("id");
String pwd = request.getParameter("pwd");
		
//DB
MemberDAO memberDAO = MemberDAO.getInstance();
MemberDTO memberDTO = memberDAO.login(id, pwd);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login.jsp</title>
</head>
<body>
<%
if(memberDTO!=null) { 
	//response.sendRedirect("loginOk.jsp?name="+ URLEncoder.encode(name,"UTF-8")+"&id="+id);
	
	//쿠키
	/* Cookie cookie = new Cookie("memName", name);
	cookie.setMaxAge(30*60);//초 단위
	response.addCookie(cookie);//클라이언트에게 보내기
	
	Cookie cookie2 = new Cookie("memId", id);
	cookie2.setMaxAge(30*60);//초 단위
	response.addCookie(cookie2);//클라이언트에게 보내기 */
	
	//세션
	//HttpSession session = request.getSession();
	session.setAttribute("memName", memberDTO.getName());
	session.setAttribute("memId", id);
	session.setAttribute("memEmail", memberDTO.getEmail1()+"@"+memberDTO.getEmail2());
	
	session.setAttribute("memDTO", memberDTO);
		
	response.sendRedirect("loginOk.jsp");
	
}else{
	response.sendRedirect("loginFail.jsp");
}
%>
</body>
</html>
































