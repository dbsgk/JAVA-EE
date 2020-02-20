<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri ="http://java.sun.com/jsp/jstl/fmt" %>
<table id="table-imageList">
	<tr>
		<th class="imgFieldName"><input type="checkbox">번호</th>
		<th class="imgFieldName">이미지</th>
		<th class="imgFieldName">상품명</th>
		<th class="imgFieldName">단가</th>
		<th class="imgFieldName">개수</th>
		<th class="imgFieldName">합계</th>
	</tr>
	<c:forEach var="data" items = "${list }" varStatus="i">
		<tr>
			<td class="field1"><input type="checkbox">${data.seq }</td>
			<td class="field1">
				<a href="#" onclick="location.href='imageboardView.do?seq=${data.seq}&pg=${data.pg}'">
					<img src="${data.image1 }" width="100px">
				</a>
			</td>
			<td class="field1">${data.imageName }</td>
			<td class="field1">
				<fmt:formatNumber pattern="#,###원">
			${data.imagePrice }
				</fmt:formatNumber></td>
			<td class="field1">${data.imageQty }</td>
			<td class="field1">${data.imagePrice * data.imageQty }</td>
		</tr>
	</c:forEach>
	<tr>
		<td><input type="button" value="선택삭제"></td>
	</tr>

</table>