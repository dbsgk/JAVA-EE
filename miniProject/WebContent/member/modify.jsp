<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="member.bean.AddrDTO"%>
<%@page import="java.util.List"%>
<%@page import="member.dao.MemberDAO"%>
<%request.setCharacterEncoding("UTF-8"); %>
    
<jsp:useBean id="memberDTO" class="member.bean.MemberDTO" />
<jsp:setProperty property="*" name="memberDTO" />
<%
	boolean result = MemberDAO.getInstance().update(memberDTO);
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
		alert("회원정보를 수정했습니다.");
		session.setAttribute("memName", name);
		session.setAttribute("memId", id);
		session.setAttribute("memEmail", memberDTO.getEmail1() + "@" + memberDTO.getEmail2());
		location.href="../main/index.jsp";
	}
</script>

</html>