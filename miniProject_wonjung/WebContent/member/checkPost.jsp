<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주소 선택</title>
<link rel="stylesheet" href="../css/checkPost.css"></link>

</head>
<body>

<form name="form" method="post" action="/miniProject_wonjung/member/checkPost.do">
<table>
	<tr >
		<td class="fieldName">시도</td>
		<td><select name="sido">
				<option value="">시도선택
			<c:forEach var = "data" items = "${sidoList }">
				<c:choose>
					<c:when test="${param.sido eq data}">
						<option value="${data }" selected>
					</c:when>
					<c:otherwise>
						<option value="${data }">	
					</c:otherwise>
				</c:choose>
					${data }
			</c:forEach>
			</select>
		</td>
		<td class="fieldName">시,군,구</td>
		<td><input type="text" name="sigungu" id="sigungu" value="${param.sigungu }"></td>
	</tr>
	<tr>
		<td class="fieldName">도로명</td>
		<td colspan="3">
			<input type="text" name="roadname" id="roadname" style="width:82%;" value="${param.roadname }">
			<input type="submit" value="검색">
		</td>
	</tr>
	<tr>
		<td class="fieldName">우편번호</td>
		<td class="fieldName" colspan="3">주소</td>
	</tr>
	<c:forEach var="addrDTO" items = "${addrList }" varStatus="i">
		<c:set var = "zipcode">${addrDTO.getZipcode() }</c:set>
		<c:set var = "new_addr">${addrDTO.getSido() } ${addrDTO.sigungu } ${addrDTO.roadname } ${addrDTO.getBuildingname() }</c:set>
		<c:set var = "old_addr">${addrDTO.getSido() } ${addrDTO.sigungu } ${addrDTO.yubmyundong } ${addrDTO.ri }</c:set>
		<tr>
			<c:choose>
			<c:when test = "${i.count % 2 == 1 }">
				<td class="fieldNames">
					<a onclick="selectAddr('${zipcode }', '${new_addr }')" style="cursor:pointer;">${zipcode }</a>
				</td>
				<td colspan="3" class="shadow">
					<div class="newAddr">
					<a onclick="selectAddr('${zipcode }', '${new_addr }')" style="cursor:pointer;">
						${new_addr } 
					</a>
					</div>
					<div class="oldAddr">
					<a onclick="selectAddr('${zipcode }', '${new_addr }')" style="cursor:pointer;">
						(구)${old_addr }
					</a>
					</div>
				</td>
			</c:when>
			<c:otherwise>
				<td class="fieldName">
					<a onclick="selectAddr('${zipcode }', '${ new_addr }')" style="cursor:pointer;">${zipcode }</a>
				</td>
				<td colspan="3">
					<div class="newAddr">
					<a onclick="selectAddr('${zipcode }', '${new_addr }')" style="cursor:pointer;">
						${new_addr } 
					</a>
					</div>
					<div class="oldAddr">
					<a onclick="selectAddr('${zipcode }', '${new_addr }')" style="cursor:pointer;">
						(구)${old_addr }
					</a>
					</div>
				</td>
			</c:otherwise>
			</c:choose>
		</tr>
	</c:forEach>
</table>
</form>

</body>

<script type="text/javascript" src="../js/member.js"></script>

</html>