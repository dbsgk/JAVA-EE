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
			<td class="seq hcenter">${dto.seq }</td>
			<td class="subject"><a href="javascript:void(0)">${dto.subject }</a></td>
			<td class="name hcenter">${dto.id }</td>
			<td class="logtime hcenter">${dto.logtime }</td>
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


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
	h2{
		text-align:center;
	}
	#boardList{
		margin:10px auto;
		border-collapse:collapse;
	}
	#boardList td:not(.search){
		border-top:1px solid #eee;
		border-bottom:1px solid #eee;
		padding:3px;
		font-size:15px;
	}
	.columnName{
		background-color:#eee;
	}
	.columnName>td{
		font-weight:bold;
		text-align:center;
	}
	.name{
		width:100px;
		text-align:center;
	}
	.subject{
		width:300px;
		padding-left:10px;
	}
	.subject>a{
		color:black;
		text-decoration:none;
	}
	.subject>a:hover{
		text-decoration:underline;
	}
	.logtime{
		width:100px;
		text-align:center;
	}
	.seq, .hit{
		width:70px;
		text-align:center;
	}
	.odd{
		background-color:#FAFAFA;
	}
	#boardList tr:not(.columnName):not(.search_):hover{
		background-color:#FBF5EF;
		cursor:pointer;
	}
	.search{
		text-align:right;
		border-top:0px solid #fff;
		padding-bottom:5px;
	}
	#pageNav{
		margin:3px auto;
	}
	#pageNav td{
		border:1px solid #eee;
		text-align:center;
		min-width:20px;
		padding:2px;
	}
	#paging{
		color: black;
		text-decoration: none;
		cursor: pointer;
	}
	#currentPaging{
		color: red;
		text-decoration: none;
		cursor: pointer;
	}
	
</style>

</head>
<body>
<h2>글 목록</h2>
<table id="boardList">
	<tr class="search_">
		<td colspan="5" class="search">
			<input type="text" name="search_str" placeholder="검색어 입력" style="padding:3px;">
			<input type="button" value="검색" style="height:25px;">
		</td>
	</tr>
	<tr class="columnName">
		<td class="seq">글번호</td>
		<td class="subject">제목</td>
		<td class="name">작성자</td>
		<td class="logtime">작성일</td>
		<td class="hit">조회수</td>
	</tr>
	
	<%for(int i = 0; i < list.size(); i++) { %>
	<tr class="<%if(i%2==1) {%>odd<%} %>" onclick="javascript:isLogin(<%=session.getAttribute("memId")==null%>, <%=list.get(i).getSeq() %>, <%=pg %>)">
		<td class="seq"><%=list.get(i).getSeq() %></td>
		<td class="subject"><a href="javascript:void(0)"><%=list.get(i).getSubject() %></a></td>
		<td class="name"><%=list.get(i).getId() %></td>
		<td class="logtime"><%=sdf.format(list.get(i).getLogtime()) %></td>
		<td class="hit"><%=list.get(i).getHit() %></td>
	</tr>
	<%} %>
</table>

	${boardPaging.pagingHTML }
</tr>
</table>

</body>

<script type="text/javascript">
	function isLogin(logout, seq, pg){
		if(logout) {
			alert('로그인이 필요합니다.');
		}else{
			location.href = "boardView.jsp?seq=" + seq + "&pg=" + pg;
		} 
	}
</script>

</html>