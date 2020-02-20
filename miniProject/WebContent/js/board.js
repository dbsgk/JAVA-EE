$('#boardBtn').click(function(){
	var div_sub = $('#subjectDiv');
	var div_con = $('#contentDiv');
	
	div_sub.empty();
	div_con.empty();
	
	if($('#subject').val()==''){
		div_sub.text("제목을 입력하세요.").css('color', 'red').css('font-size','8pt');
	}else if($('#content').val()==''){
		div_sub.text("내용을 입력하세요.").css('color', 'red').css('font-size','8pt');
	}else{
		document.forms[0].submit();
	}
	
});

/*$('#image_checkAll').click(function(){
	$('input[type=checkbox]').prop('checked', $('#image_checkAll').prop('checked'));
});*/

function checkboxClicked(){
	var chkboxs = document.getElementsByName('image_check');
	var chkValue = document.getElementById('image_checkAll').checked
	for(var i = 0; i < chkboxs.length; i++){
		chkboxs[i].checked = chkValue;
	}
}

function imageboardDelete(){
	
	/* Jquery
	var items = [];
	$('input[id=image_check]:checkbox:checked').each(function(){items.push($(this).val())})
	*/
	
	var chkboxs = document.getElementsByName('image_check');
	var items = [];
	for(var i = 0; i < chkboxs.length; i++){
		if(chkboxs[i].checked){
			items.push(chkboxs[i].value);
		}
	}
	
	if(items.length == 0){
		alert('선택된 항목이 없습니다.');
		return;
	}
	if(confirm('정말로 삭제하시겠습니까?')){
		location.href="imageboardDelete.do?seqs=" + items.join(",");
	}
};

function imageboardPaging(pg){
	location.href="imageboardList.do?pg="+pg;
	
}