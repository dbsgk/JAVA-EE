<%@page import="board.bean.BoardDTO"%>
<%@page import="board.dao.BoardDAO"%>
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
%>  
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 쓰기</title>
<link rel="stylesheet" type="text/css" href="../css/member.css"> 
<style type="text/css">
	
</style>
</head>
<body>

<h2 class="h-center">글 수정</h2>
<form name="modifyForm" action="boardModify.jsp" method="post">
<input type="hidden" name="seq" value="<%=seq %>">
<input type="hidden" name="pg" value="<%=pg %>">
<input type="hidden" name="subject" id="subject" value="<%=boardDTO.getSubject() %>"> 
<input type="hidden" name="content" id="content" value="<%=boardDTO.getContent() %>">

<table id="table-boardView">
	<tr>
		<td class="fieldName">글 번호</td>
		<td class="h-center" style="width:50px;"><%=boardDTO.getSeq() %> </td> 
		<td class="fieldName">작성일</td>
		<td class="h-center" style="width:170px;"><%=boardDTO.getLogtime().toString() %> </td>
		<td class="fieldName">작성자</td>
		<td class="h-center"><%=boardDTO.getName() %> </td>
		<td class="fieldName">조회수</td>
		<td class="h-center"><%=boardDTO.getHit() %> </td>
	</tr>
	<tr>
		<td class="fieldName">제 목</td>
		<td colspan="7" class="td-subject"><input type="text" name="subject" id="subject" value="<%=boardDTO.getSubject() %>"></td>
	</tr>
	<tr>
		<td class="fieldName">내 용</td>
		<td colspan="7" width="500px" class="content">
			<textarea name="content" id="content"><%=boardDTO.getContent() %></textarea>
		</td>
	</tr>
	<tr>
		<td class="h-center" colspan="8">
			<input type="button" id="modify" value="수정" onclick="checkBoardModify(<%=pg %>, <%=seq %>)">
			<input type="button" value="취소" onclick="location.href='boardList.jsp?pg=<%=pg %>'">
		</td>
	</tr>

</table>
</form>

</body>
<script type="text/javascript">
function checkBoardModify(){
	if(document.modifyForm.subject.value=""){
		document.modifyForm.subject.focus();
		alert('제목을 입력하세요!');
	}else if(document.modifyForm.content.value=""){
		document.modifyForm.subject.focus();
		alert('내용을 입력하세요!');
	}else{
		document.modifyForm.submit();
	}
}
</script>

</body>
</html>