<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<input type="button" id="btn1" value="commit_1" onclick="process()">
<input type="button" id="btn2" value="commit_2">
<input type="button" id="btn3" value="commit_3">
</body>
<script type="text/javascript" src="../js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.4.1.min.js"></script><!-- 위 아래 둘 중에 하나만 써도 됨 -->
<script type="text/javascript">
function process(){
	alert("commit_1 호출");
}
/* window.onload JQuery방법   */
$(document).ready(function(){
	$('#btn2').click(function(){
	//selector(선택자).이벤터(함수)
		alert("commit_2 호출");
	});
	
	$('#btn3').bind('click',function(){
		alert('commit_3 호출');
	});
});/* jQuery는 마지막에 세미콜론 붙는데 빼먹을 수 있으니까 처음부터 붙여놓고 안에 채워라. */


</script>
</html>