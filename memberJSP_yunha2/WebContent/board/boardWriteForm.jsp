<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>글쓰기</h2>
	<form name="boardWriteForm" method="post" action="boardWrite.jsp">
		
		<table border="2">
			<tr>
				<td width="70" align="center">제목</td>
				<td><input type="text" placeholder="제목을 입력하시오" name="subject" size="50"></td>
			</tr>

			<tr>
				<td align="center" width="70">내용</td>
				<td><textarea placeholder="내용을 입력하시오" name="content" rows="15" cols="40"></textarea></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
				<input type="button" value="글쓰기" onclick="checkBoardWriteForm()"> 
				<input type="reset" value="다시작성"></td>
			</tr>
		</table>
	</form>
</body>
<script type="text/javascript">
	function checkBoardWriteForm() {
		//유효성 체크 
		if (document.boardWriteForm.subject.value =="")
			alert("제목을 입력하세요.");
		else if (document.boardWriteForm.content.value =="")
			alert("내용을 입력하세요.");
		else
			document.boardWriteForm.submit();
	}
</script>
</html>