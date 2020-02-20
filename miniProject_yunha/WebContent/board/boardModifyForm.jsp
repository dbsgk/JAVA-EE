<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="../css/member.css"> 
<style type="text/css">
	
</style>
</head>
<body>

<h2 class="h-center">글 수정</h2>
<form name="boardModifyForm" action="boardModify.do" method="post">
<input type="hidden" name="seq" value="${seq }">
<input type="hidden" name="pg" value="${pg }">
<input type="hidden" name="subject" id="subject" value="${subject }"> 
<input type="hidden" name="content" id="content" value="${content }">

<table id="table-boardView">
	<tr>
		<td class="fieldName">글 번호</td>
		<td class="h-center" style="width:50px;">${seq } </td> 
		<td class="fieldName">작성일</td>
		<td class="h-center" style="width:170px;">${boardDTO.logtime}</td>
		<td class="fieldName">작성자</td>
		<td class="h-center">${boardDTO.name} </td>
		<td class="fieldName">조회수</td>
		<td class="h-center">${boardDTO.hit} </td>
	</tr>
	<tr>
		<td class="fieldName">제 목</td>
		<td colspan="7" class="td-subject"><input type="text" name="subject" id="subject" value="${subject }"></td>
	</tr>
	<tr>
		<td class="fieldName">내 용</td>
		<td colspan="7" width="500px" class="content">
			<textarea name="content" id="content">${content }</textarea>
		</td>
	</tr>
	<tr>
		<td class="h-center" colspan="8">
			<input type="button" id="modify" value="수정" onclick="checkBoardModify(${pg }, ${seq })">
			<input type="button" value="취소" onclick="location.href='boardList.jsp?pg=${pg }'">
		</td>
	</tr>

</table>
</form>

</body>
<script type="text/javascript">
function checkBoardModify(){
	if(document.boardModifyForm.subject.value=""){
		document.boardModifyForm.subject.focus();
		alert('제목을 입력하세요!');
	}else if(document.boardModifyForm.content.value=""){
		document.boardModifyForm.subject.focus();
		alert('내용을 입력하세요!');
	}else{
		document.boardModifyForm.submit();
	}
}
</script>

</body>
</html>