<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %>
<table id="table-imageList">
	<tr>
		<td class="imgFieldName">번호</td>
		<td class="imgFieldName">이미지</td>
		<td class="imgFieldName">상품명</td>
		<td class="imgFieldName">단가</td>
		<td class="imgFieldName">개수</td>
		<td class="imgFieldName">합계</td>
	</tr>
	<c:forEach var="data" items = "${list }" varStatus="i">
		<tr>
			<td class="field1">${data.seq }</td>
			<td class="field1"><img src="${data.image1 }" width="100px"></td>
			<td class="field1">${data.imageName }</td>
			<td class="field1">${data.imagePrice }</td>
			<td class="field1">${data.imageQty }</td>
			<td class="field1">${data.imagePrice * data.imageQty }</td>
		</tr>
	</c:forEach>


</table>