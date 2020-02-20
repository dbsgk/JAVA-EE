<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<h2>상품 등록</h2>
<table id="table-imageboard">
<tr>
	<td rowspan="4"><img src="${dto.image1 }" alt="${dto.imageName }">
	<td class="fieldName">상품명</td>
	<td class="field1">${dto.imageName }</td>
</tr>
<tr>
	<td class="fieldName">단가</td>
	<td class="field1"><fmt:formatNumber value="${dto.imagePrice }" pattern="#,###,###"/>원 </td>
</tr>
<tr>
	<td class="fieldName">개수</td>
	<td class="field1">${dto.imageQty }</td>
</tr>
<tr>
	<td class="fieldName">합계</td>
	<td class="field1"><fmt:formatNumber value="${dto.imageQty * dto.imagePrice }" pattern="#,###,###"/>원</td>
</tr>
<tr>
	<td colspan="3" class="field1">${dto.imageContent }</td>
</tr>
<tr>
<td colspan="3" class="td-btn"><input type="button" id="btn-imageList" value="목록으로" onclick="history.back(-1)"></td>
</tr>
</table>
<script type="text/javascript" src="../js/jquery-3.4.1.min.js"></script>
