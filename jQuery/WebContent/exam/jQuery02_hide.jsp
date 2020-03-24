<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.backColor {
	background-color: green;
}
</style>
</head>
<body>
<h1 class="back">Hello jQuery</h1>
<button id="hellobtn">헬로우 제이쿼리</button>
</body>
<script type="text/javascript" src="../js/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$('.back').addClass('backColor');
	
	//크롬에선 웹표준이 안되서 이 함수 안먹음.(버튼누르면 버튼 사라졌다가 alert 뜨고 나서 버튼 뜨게 하는 함수.)
	/* $(this) == 현재 이 글자가 있는 함수를 말함(#hellobtn) */
	/* $('#hellobtn').click(function() {
		$(this).hide();
		alert("jQuery 연습");
		$(this).show();
	}); */
	/* $('#hellobtn').click(function() {
		$(this).hide(function() {
			alert("jQuery 연습");
		}).show(function() {});
	}); */
	/* $('#hellobtn').hide(function() {
			alert("jQuery 연습");
	}).show(function() {}); */ 
	$('#hellobtn').click(function() {
		$(this).hide(100,function() {
			alert("jQuery 연습");
		}).show(function() {});
	});
});	
</script>
</html>