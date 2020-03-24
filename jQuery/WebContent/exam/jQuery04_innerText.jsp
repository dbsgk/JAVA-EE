<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<input type="text" id="name" value="">
<p id="disp"><strong>값을 바꿔보시오</strong></p>
<input type="button" id="btn" value="버튼">
</body>
<script type="text/javascript" src="../js/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
$('#btn').click(function() {
	//document.getElementById('name').value='홍길동';
	//$('#name').val('또치');
	
	//var txt = document.getElementById('disp').innerText;
	//var txt = $('#disp').text();
	//alert(txt);
	
	//var html = document.getElementById('disp').innerHTML;
	//var html = $('#disp').html();
	//alert(html);
	
	//$('#disp').text('Have a nice day!!');
	//$('#disp').css('color', 'red');
	//$('#disp').css('font-weight', 'bold');
	$('#disp').html('<b style="color: blue;">Have a nice day!!</b>');
})
</script>
</html>