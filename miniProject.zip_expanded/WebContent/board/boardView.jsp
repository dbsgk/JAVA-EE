<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>

<h2 class="h-center">글 보기</h2>
<form name="modifyForm" action="boardModifyForm.jsp" method="post">
<input type="hidden" name="seq" value="${param.seq }">
<input type="hidden" name="pg" value="${param.pg }">

<table id="table-boardView">
	<tr>
		<td class="fieldName">글 번호</td>
		<td class="h-center" style="width:50px;">${boardDTO.seq } </td> 
		<td class="fieldName">작성일</td>
		<td class="h-center" style="width:170px;">${boardDTO.logtime } </td>
		<td class="fieldName">작성자</td>
		<td class="h-center">${boardDTO.name } </td>
		<td class="fieldName">조회수</td>
		<td class="h-center">${boardDTO.hit } </td>
	</tr>
	<tr>
		<td class="fieldName">제 목</td>
		<td colspan="7" class="td-subject">${boardDTO.subject }</td>
	</tr>
	<tr>
		<td class="fieldName">내 용</td>
		<td colspan="7" width="500px" class="content">
			<div id="pp">${boardDTO.content }</div>
			<%-- <pre id="pp">${boardDTO.content }</pre> --%>
		</td>
	</tr>
	<tr>
		<td class="h-center" colspan="8">
		<c:if test = "${id == boardDTO.id }">
			<input type="button" id="modify" value="수정" onclick="boardUpdate()">
			<input type="button" id="delete" value="삭제" onclick="checkBoardDelete(${param.pg }, ${param.seq })">
		</c:if>
			<input type="button" value="돌아가기" onclick="location.href='boardList.do?pg=${param.pg }'">
		</td>
	</tr>

</table>
</form>

</body>
<script type="text/javascript">
function boardUpdate(){
	document.modifyForm.submit();
}

function checkBoardDelete(pg, seq){
	alert('삭제는 나중에~ 선생님과 함께');
	//location.href="boardUpdate.jsp?pg=" + pg + "seq=" + seq;
}
</script>

</html>