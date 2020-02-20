<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style type="text/css">
.contents {
	white-space: pre-line;
	word-break: break-all;
	width: 450px;
}
</style>

<form name="imageboardViewForm">
<input type="hidden" name="seq" value="">
<input type="hidden" name="pg" value="${pg }">
<input type="hidden"  name="subject" value="${imageboardDTO.subject }">
<input type="hidden" name="content" value="${imageboardDTO.content }">
<input type="hidden" name="image1" value="${imageboardDTO.image1 }">

<table  border="1" cellpadding="5" frame="hsides" rules="rows">
	<tr>
		<td width="150" colspan="3">
		
		번호 : ${imageboardDTO.seq }</td>
	</tr>
	<tr>
		<td width="500" rowspan="4" height="500"><img src="D://java_ee/workspace/miniProject/WebContent/image/image1.jpg">
		</td>
		<td width="150">상품명 : ${imageboardDTO.imageName }</td>
	</tr>
	<tr>
		<td width="150" >단가 : ${imageboardDTO.imagePrice}
		</td>
	</tr>
	<tr>
		<td width="150">개수 : ${imageboardDTO.imageQty }</td>
		<td width="150">합계 : ${imageboardDTO.imagePrice * imageboardDTO.Qty }</td>
		<td colspan="3" height="200" valign="top">
			<pre class="contents">${imageboardDTO.content }</pre>
		</td>
	</tr>
</table>
<input type="button" value="목록" onclick="location.href='imageboardList.do?pg=${pg}'">
<input type="button" value="답글" onclick="location.href='boardReplyForm.do?pseq=${imageboardDTO.seq}&pg=${pg }'">
<c:if test="${memId == 'admin' }">
	<input type="button" value="글수정" onclick="mode(1)">
	<input type="button" value="글삭제" onclick="mode(2)">
</c:if>
</form>

<script type="text/javascript">
function mode(num){
	if(num==1){
		document.boardViewForm.method = 'post';
		document.boardViewForm.action = 'boardModifyForm.do';
		document.boardViewForm.submit();
		
	}else if(num==2){
		document.boardViewForm.method = 'post';
		//document.boardViewForm.action = 'boardDelete.jsp';
		document.boardViewForm.submit();
	}
}
</script>