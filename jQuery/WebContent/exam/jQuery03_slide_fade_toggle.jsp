<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<button id="btn1">slideUp</button>
<button id="btn2">slideDown</button>&nbsp;&nbsp;&nbsp;

<button id="btn3">fadeIn</button>
<button id="btn4">fadeOut</button>&nbsp;&nbsp;&nbsp;

<button id="btn5">slide toggle</button>
<button id="btn6">fade toggle</button>
<button id="btn7">toggle</button>

<p>이미지</p>
<img src="../image/img1.jpg" id="img" width="300px" height="300px" class="img">
</body>
<script type="text/javascript" src="../js/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
//slideUp(speed) : speed값을 지정해서 슬라이드업 효과를 줄 수 있다.
//speed: "slow" or "fast" or milliseconds(1/1000초)
//img 모든 이미지 태그에 속성 먹게 하겠다.
//#img id가 img인 애들한테 속성 먹이겠다.
//.img class가 img인 애들한테 속성 먹이겠다.


//img 크기(width, height) 안잡으면 왼쪽 상단으로 사라지고, 잡으면 위쪽으로 사라짐. 
$('#btn1').click(function() {
	$('#img').slideUp('slow');//숫자 입력해도 됨.
});
$('#btn2').click(function() {
	$('#img').slideDown('fast');
});

$('#btn3').click(function() {
	$('#img').fadeIn(1000);
});
$('#btn4').click(function() {
	$('#img').fadeOut(1000);
});

$('#btn5').click(function(){
	$('img').slideToggle(1000);//slideUp, slideDown 번갈아
});
$('#btn6').click(function(){
	$('img').fadeToggle(1000);//fadeIn, fadeOut 번갈아
});
$('#btn7').click(function(){
//slideUp, slildeDown, fadeIn, fadeOut  섞여있다.
//(색깔이 흐려지면서 slideUp, slideDown)
	$('img').toggle(1000);
});
</script>
</html>