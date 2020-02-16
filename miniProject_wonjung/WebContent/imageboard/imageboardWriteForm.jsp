<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h2>상품 등록</h2>
<form name="imageboardForm" enctype="multipart/form-data" method="post" action="/miniProject/imageboard/imageboardWrite.do">
<table id="table-imageboard">
<tr>
	<td class="fieldName">상품코드</td>
	<td class="field1"><input type="text" name="imageId" id="imageId" placeholder="상품코드 입력" value="img_"></td>
</tr>
<tr>
	<td class="fieldName">상품명</td>
	<td class="field1"><input type="text" name="imageName" id="imageName" placeholder="상품명 입력"></td>
</tr>
<tr>
	<td class="fieldName">단가</td>
	<td class="field1"><input type="text" name="imagePrice" id="imagePrice" placeholder="단가 입력"></td>
</tr>
<tr>
	<td class="fieldName">개수</td>
	<td class="field1"><input type="text" name="imageQty" id="imageQty" placeholder="개수 입력"></td>
</tr>
<tr>
	<td class="fieldName">내용</td>
	<td><textarea name="imageContent">내용 입력</textarea></td>
</tr>
<tr>
	<td colspan="2" class="fieldName"><input type="file" name="imagefile"></td>
</tr>
<tr>
	<td colspan="2" class="fieldName"><input type="button" value="이미지 등록" id="imageUpload"><input type="reset" value="다시작성"></td>
</tr>

</table>
</form>

<script type="text/javascript" src="../js/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
$('#imageUpload').click(function(){
	// 유효성 검사 (제목, 내용)
	if(document.imageboardForm.imageId.value=='img_'){
		alert("상품 코드가 올바르지 않습니다.");
	}else if(document.imageboardForm.imageName.value==''){
		alert("상품명을 입력해주세요.");
	}else if(document.imageboardForm.imagePrice.value==''){
		alert("가격을 입력해주세요.");
	}else if(document.imageboardForm.imageQty.value==''){
		alert("상품 수량을 입력해주세요.");
	}else if(document.imageboardForm.imageContent.value==''){
		alert("내용을 입력해주세요.");
	}else{
		document.imageboardForm.submit();
	}
});
</script>
