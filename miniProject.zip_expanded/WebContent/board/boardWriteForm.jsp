<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
<style type="text/css">
	body{
	}
	h2{
		text-align:center;
	}
	table{
		margin:10px auto;
		border-collapse:collapse;
		border:1px solid #ddd;
	}
	td{
		padding:4px;
		border:1px solid #ddd;
	}
	.fieldName{
		text-align:center;
		width:80px;
	}
	.field{
		width:96%;
	}
</style>

</head>
<body>

<h2>글쓰기</h2>
<form method="post" action="boardWrite.do">
<input type="hidden" name="id" value="<%=session.getAttribute("memId") %>">
<input type="hidden" name="name" value="<%=session.getAttribute("memName") %>">
<input type="hidden" name="email" value="<%=session.getAttribute("memEmail") %>">
<table>
	<tr>
		<td class="fieldName">제 목</td>
		<td>
			<input type="text" name="subject" class="field" placeholder="제목 입력"> 
		</td>
	</tr>
	<tr>
		<td class="fieldName">내 용</td>
		<td width="500px">
			<textarea class="field" name="content" rows="20" placeholder="내용 입력"></textarea>
		</td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<input type="button" value="글쓰기" onclick="checkBoardWriteForm()">
			<input type="reset" value="다시작성">
		</td>
	</tr>

</table>
</form>

</body>
<script type="text/javascript">
	function checkBoardWriteForm(){
		// 유효성 검사 (제목, 내용)
		if(document.forms[0].subject.value==""){
			alert("제목을 입력해주세요.");
		}else if(document.forms[0].content.value==""){
			alert("내용을 입력해주세요.");
		}else{
			document.forms[0].submit();
		}
	}

</script>

</html>