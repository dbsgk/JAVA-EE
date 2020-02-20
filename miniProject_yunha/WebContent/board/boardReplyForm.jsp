<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>답글쓰기</title>
</head>
<h2>답글쓰기</h2>
<form action="boardReply.do" id="boardReplyForm" name="boardReplyForm" method="post">
<input type="hidden" name="pseq" value="${pseq }">
<input type="hidden" name="pg" value="${pg }">
<input type="hidden" name="email" value="<%=session.getAttribute("memEmail") %>">
<table>
	<tr>
		<td class="fieldName">제 목</td>
		<td>
			<input type="text" name="subject" class="field" placeholder="제목 입력"> 
			<div id="div_subject"></div>
		</td>
	</tr>
	<tr>
		<td class="fieldName">내 용</td>
		<td width="500px">
			<textarea class="field" name="content" rows="20" placeholder="내용 입력"></textarea>
			<div id="div_content"></div>
		</td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<input type="button" value="답글쓰기" id="boardFormBtn" >
			<input type="reset" value="다시작성">
		</td>
	</tr>

</table>
</form>
</body>
<script type="text/javascript" src="../js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="../js/board.js"></script>
</html>