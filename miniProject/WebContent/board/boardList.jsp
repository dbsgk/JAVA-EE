<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<table id="table-boardList">
	<tr class="search_">
		<td class="search" style="text-align:left;"><input type="button" value="글쓰기" onclick="write11()"></td>
		<td colspan="4" class="search">
			<input type="text" name="search_str" placeholder="검색어 입력" style="padding:3px;">
			<input type="button" value="검색" style="height:25px;">
		</td>
	</tr>
	<tr class="columnName">
		<td class="seq">글번호</td>
		<td class="subject">제목</td>
		<td class="name hcenter">작성자</td>
		<td class="logtime hcenter">작성일</td>
		<td class="hit hcenter">조회수</td>
	</tr>
	<c:if test="${memId == null }"><c:set var="logout" value="true"/></c:if>
	<c:if test="${memId != null }"><c:set var="logout" value="false"/></c:if>
	<c:forEach var = "dto" items = "${list }" varStatus="i">
		<c:if test = "${i.count % 2 == 1 }">
			<tr class="odd" onclick="javascript:isLogin(${logout }, ${dto.seq }, ${pg })">
		</c:if>
		<c:if test = "${i.count % 2 == 0 }">
			<tr class="" onclick="javascript:isLogin(${logout }, ${dto.seq }, ${pg })">
		</c:if>
			<td class="seq hcenter">
				<c:if test="${dto.step == 0 }">${dto.seq }</c:if>
			</td>
			<td class="subject">
				<c:forEach var="i" begin="1" end="${dto.lev }">&nbsp;</c:forEach>
				<c:if test="${dto.step > 0 }"><img src="../image/reply.gif"></c:if>
				<a href="javascript:void(0)">${dto.subject }<c:if test="${dto.reply != 0 }"> [${dto.reply}]</c:if>
				</a>
			</td>
				<td class="name hcenter">${dto.id }</td>
				<td class="logtime hcenter"><fmt:formatDate value="${dto.logtime }" pattern="YYYY.MM.dd" /></td>
				<td class="hit hcenter">${dto.hit }</td>
			</tr>
	</c:forEach>
</table>
<div align="center">
<table id="pageNav">
<tr>
	${boardPaging }
</tr>
</table>
</div>

<script type="text/javascript">
	function write11(){
		location.href = "boardWriteForm.do";
	}
	function isLogin(logout, seq, pg){
		if(logout) {
			alert('로그인이 필요합니다.');
		}else{
			location.href = "boardView.do?seq=" + seq + "&pg=" + pg;
		} 
	}
	
</script>

