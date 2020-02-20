<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<h2>답글쓰기</h2>
<form name="boardReplyForm" method="post" action="/miniProject/board/boardReply.do">
<input type="hidden" name="pg" value=${pg }>
<input type="hidden" name="pseq" value=${pseq }>
<table id="table-boardReply">
	<tr>
		<td>제목</td>
		<td><input type="text" name="subject" value="" placeholder="제목 입력"></td>
	</tr>
	<tr>
		<td>내용</td>
		<td><textarea name="content" rows="" cols="" placeholder="내용 입력"></textarea></td>
	</tr>
</table>
<div id="div-replyBtn">
	<input type="button" id="boardBtn" value="답글쓰기">
	<input type="reset" value="다시작성">
</div>
</form>

<script type="text/javascript" src="../js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="../js/board.js"></script>