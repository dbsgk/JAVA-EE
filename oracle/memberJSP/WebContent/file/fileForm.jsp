<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method="post" enctype="multipart/form-data" action="fileUpload.jsp">
<table border="1">
	<tr>
		<td width="70" align="center">제목</td>
		<td>
			<input type="text" placeholder="제목을 입력하시오" name="subject" size="50">
		</td>
	</tr>

	<tr>
		<td align="center" width="70">내용</td>
		<td>
			<textarea placeholder="내용을 입력하시오" name="content" rows="15" cols="50"></textarea>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="file" name="upload1" size="50"> 
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="file" name="upload2" size="50"> 
		</td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<input type="submit" value="파일업로드"> 
			<input type="reset" value="취소"> 
		</td>
	</tr>
</table>
</form>
</body>
</html>