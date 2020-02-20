$('#boardFormBtn').click(function(){
	$('#div_subject').empty();
	$('#div_content').empty();
	
	if($('#subject').val()=='')	{
		$('#div_subject').text('제목을 입력하세요');
		$('#div_subject').css('color','red');
		$('#div_subject').css('font-size','8pt');
		$('#div_subject').css('font-weight','bold');
	}else if($('#content').val()==''){
		$('#div_content').text('내용을 입력하세요');
		$('#div_content').css('color','red');
		$('#div_content').css('font-size','8pt');
		$('#div_content').css('font-weight','bold');
	}else
		document.forms[0].submit();
});