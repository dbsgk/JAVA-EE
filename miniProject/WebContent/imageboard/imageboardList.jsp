<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri ="http://java.sun.com/jsp/jstl/fmt" %>
<style>
#paging{
	color: black;
	text-decoration: none;
	cursor: pointer;
}
#currentPaging{
	color: red;
	text-decoration: underline;
	cursor: pointer;
}
</style>
<table id="table-imageList">
	<thead>
		<tr>
			<td colspan="7">
				<input type="button" value="선택항목 삭제" onclick="imageboardDelete()">
			</td>
		</tr>
	</thead>
	<tbody>
	<tr>
		<td class="check"><input type="checkbox" id="image_checkAll" class="image_check" onclick="checkboxClicked()"></td>
		<td class="number">번호</td>
		<td class="imgFieldName">이미지</td>
		<td class="imgName1">상품명</td>
		<td class="imgFieldName">단가</td>
		<td class="imgFieldName">개수</td>
		<td class="imgFieldName">합계</td>
	</tr>
	<c:forEach var="data" items = "${list }" varStatus="i">
		<tr>
			<td class="check"><input type="checkbox" name="image_check" id="image_check" class="image_check" value="${data.seq }"></td>
			<td class="number">${data.seq }</td>
			<td class="field1"><a href="imageboardView.do?seq=${data.seq }"><img src="${data.image1 }"></a></td>
			<td class="imgName2"><a href="imageboardView.do?seq=${data.seq }">${data.imageName }</a></td>
			
			<td class="field2" id="imagePrice"><fmt:formatNumber type="number" value="${data.imagePrice }" pattern="#,###,###" /></td>
			<td class="td-quantity">
				<span class="quantity">
				<input type="hidden" id="imagePrice${data.seq }" value="${data.imagePrice }">
				<input type="text" id="imageQty${data.seq }" value="<fmt:formatNumber type="number" value="${data.imageQty }" pattern="#,###,###" />">
				<a href="#none" onclick=
					"javascript:var price=$('#imagePrice${data.seq }').val();
					var qty = $('#imageQty${data.seq }').val()*1+1;
					$('#imageQty${data.seq }').val(qty);
					$('#sum${data.seq }').text((qty*price).format())"
				><img class="option_box_up" src="http://img.echosting.cafe24.com/design/skin/default/product/btn_count_up.gif"></a>
				<a href="#none" onclick=
					"javascript:var price=$('#imagePrice${data.seq }').val();
					var qty = $('#imageQty${data.seq }').val()*1-1;
					$('#imageQty${data.seq }').val(qty);
					$('#sum${data.seq }').text((qty*price).format())"
				><img class="option_box_down" src="http://img.echosting.cafe24.com/design/skin/default/product/btn_count_down.gif"></a>
				</span>
			</td>
			<td class="field2" id="sum${data.seq }"><fmt:formatNumber type="number" value="${data.imagePrice * data.imageQty }" pattern="#,###,###" /></td>
		</tr>
	</c:forEach>
	</tbody>
</table>

<div align="center">
<table id="pageNav">
<tr>${imageboardPaging }</tr>
</table>
</div>
<script type="text/javascript" src="../js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="../js/format.js"></script>
<script type="text/javascript" src="../js/board.js"></script>