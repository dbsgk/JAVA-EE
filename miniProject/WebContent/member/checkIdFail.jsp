<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
입력하신 아이디 ${id }는 이미 사용중인 아이디입니다.<br>
<input type="text" name="id" id="id">
<input type="button" value="중복체크" onclick="checkId()">
</body>

<script type="text/javascript">
function checkId(){
	if(document.getElementById("id").value == "")
		alert("Id를 입력하세요!");
	else
		location.href="/miniProject/member/checkId.do?id=" + document.getElementById("id").value;
}

</script>


</html>