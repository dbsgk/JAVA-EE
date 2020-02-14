<%@page import="board.dao.BoardDAO"%>
<%@page import="board.bean.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
String id = (String) session.getAttribute("memId");
int seq = Integer.parseInt(request.getParameter("seq"));
int pg = Integer.parseInt(request.getParameter("pg"));
String subject = request.getParameter("subject");
String content = request.getParameter("content");

BoardDAO boardDAO = BoardDAO.getInstance();
BoardDTO boardDTO = boardDAO.selectOne(seq, false);

String result = "";
if(!boardDTO.getId().equals(id)){
	result = "권한이 없습니다.";
}else{
	boardDTO.setSubject(subject);
	boardDTO.setContent(content);
	boardDAO.update(boardDTO);
	result = "게시글을 수정했습니다.";
}

%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 수정</title>
</head>
<body onload = "updateCheck('<%=result %>', <%=pg %>, <%=seq %>)">

</body>

<script type="text/javascript">
	function updateCheck(str, pg, seq){
		alert(str);
		location.href="boardView.jsp?pg="+pg+"&seq="+seq;
	}

</script>

</html>