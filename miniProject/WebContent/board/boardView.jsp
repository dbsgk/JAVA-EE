<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style type="text/css">
.contents {
	white-space: pre-line;
	word-break: break-all;
	width: 450px;
}
</style>

<form name="boardViewForm">
<input type="hidden" name="seq" value="${boardDTO.seq }">
<input type="hidden" name="pg" value="${pg }">
<input type="hidden"  name="subject" value="${boardDTO.subject }">
<input type="hidden" name="content" value="${boardDTO.content }">

<table  border="1" cellpadding="5" frame="hsides" rules="rows">
	<tr>
		<td colspan="3">
			<h3>${boardDTO.subject }</h3>
		</td>
	</tr>
	<tr>
		<td width="150">글번호 : ${boardDTO.seq }</td>
		<td width="150">작성자 : ${boardDTO.id }</td>
		<td width="150">조회수 : ${boardDTO.hit }</td>
	</tr>
	<tr>
		<td colspan="3" height="200" valign="top">
			<pre class="contents">${boardDTO.content }</pre>
		</td>
	</tr>
</table>
<input type="button" value="목록" onclick="location.href='boardList.do?pg=${pg}'">
<input type="button" value="답글" onclick="location.href='boardReplyForm.do?seq=${seq}&pg=${pg }'">
<c:if test="${memId == boardDTO.id }">
	<input type="button" value="글수정" onclick="mode(1)">
	<input type="button" value="글삭제" onclick="mode(2)">
</c:if>
</form>

<script type="text/javascript">
function mode(num){
	if(num==1){
		document.boardViewForm.method = 'post';
		document.boardViewForm.action = 'boardModifyForm.do';
		document.boardViewForm.submit();
		
	}else if(num==2){
		document.boardViewForm.method = 'post';
		//document.boardViewForm.action = 'boardDelete.jsp';
		document.boardViewForm.submit();
	}
}
</script>
</html>