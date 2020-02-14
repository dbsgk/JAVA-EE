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
<div align="center">
<div style="align:center; width:50px; margin:5px auto; display:inline-block;">
	<jsp:include page="../main/logo.jsp" />
</div>
<div style="display:inline-block; align:center;">
<table id="pageNav">
<tr>
	<%=boardPaging.getPagingHTML() %>
</tr>
</table>
</div>
</div>
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