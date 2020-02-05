<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>
<h3>글쓰기</h3>
<form action="boardWrite.jsp" name="boardWriteForm">
<table>
  <tr>
    <td>제목</td>
    <td><input type="text" name="title" id="title"></td>
  </tr>
  <tr>
    <td>내용</td>
    <td>
    <textarea rows="200" cols="20"></textarea>
    </td>
  </tr>
    <tr>
    <td>
	<input type="button" value="글쓰기" onclick="checkBoardWriteForm()">
	<input type="reset" value="다시작성">
	</td>
  </tr>
</table>


</form>
<script type="text/javascript" src="../js/member.js"></script>
</body>

</html>