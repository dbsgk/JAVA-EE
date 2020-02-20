<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
<link rel="stylesheet" type="text/css" href="../css/member.css"> 
<h2 class="h-center">글 수정</h2>
<form name="modifyForm" action="boardModify.do" method="post">
<input type="hidden" name="seq" value="${boardDTO.seq }">
<input type="hidden" name="pg" value="${pg }">
<input type="hidden" name="subject" id="subject" value="${boardDTO.subject}"> 
<input type="hidden" name="content" id="content" value="${boardDTO.content}">

<table id="table-boardView">
	<tr>
		<td class="fieldName">글 번호</td>
		<td class="h-center" style="width:50px;">${boardDTO.seq} </td> 
		<td class="fieldName">작성일</td>
		<td class="h-center" style="width:170px;"><fmt:formatDate value="${boardDTO.logtime}" pattern="YYYY.MM.dd" /> </td>
		<td class="fieldName">작성자</td>
		<td class="h-center">${boardDTO.name} </td>
		<td class="fieldName">조회수</td>
		<td class="h-center">${boardDTO.hit} </td>
	</tr>
	<tr>
		<td class="fieldName">제 목</td>
		<td colspan="7" class="td-subject"><input type="text" name="subject" id="subject" value="${boardDTO.subject}"></td>
	</tr>
	<tr>
		<td class="fieldName">내 용</td>
		<td colspan="7" width="500px" class="content">
			<textarea name="content" id="content">${boardDTO.content}</textarea>
		</td>
	</tr>
	<tr>
		<td class="h-center" colspan="8">
			<input type="button" id="modify" value="수정" onclick="checkBoardModify(${pg }, ${seq })">
			<input type="button" value="취소" onclick="location.href='boardView.do?pg=${pg}&seq=${seq}'">
		</td>
	</tr>

</table>
</form>

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

