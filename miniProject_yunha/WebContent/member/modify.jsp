<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

