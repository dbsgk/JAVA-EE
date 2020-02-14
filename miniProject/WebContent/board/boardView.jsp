<%@page import="java.text.SimpleDateFormat"%>
<%@page import="board.bean.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="boardDAO" class="board.dao.BoardDAO" />    
<%
request.setCharacterEncoding("UTF-8");
int seq = Integer.parseInt(request.getParameter("seq"));
int pg = Integer.parseInt(request.getParameter("pg"));
String id = (String) session.getAttribute("memId");

Cookie[] ar = request.getCookies();
boolean hit_on = true;
for(int i = 0; i < ar.length; i++){
	if(ar[i].getName().equals("hit_on")){
		hit_on = ar[i].getValue().equals("true") ? true : false;
	}
}

BoardDTO boardDTO = boardDAO.selectOne(seq, hit_on);
SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:SS");

Cookie cookie = new Cookie("hit_on", "false");
cookie.setMaxAge(60*30); // 초 단위
response.addCookie(cookie); // 클라이언트에게 보내기
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
<style type="text/css">
	h2{
		text-align:center;
	}
	table{
		width:40%;
		margin:10px auto;
		border-collapse:collapse;
		border:1px solid #ddd;
	}
	td{
		padding:3px;
		border:1px solid #ddd;
	}
	.fieldName + td:not(.content):not(.td_subject){
		min-width:90px;
		text-align:center;
	}
	input[type="text"]{
		padding:3px;
	}
	textarea, .content{
		padding:4px;
		padding-left:10px;
		line-height:120%;
		max-width:700px;
	}
	pre{
		word-break:break-all;
		white-space: pre-wrap;
		overflow: auto;
	}
	#save, #cancel, textarea, #subject{
		display:none;
	}
	.fieldName{
		padding:3px; 
		text-align:center;
		min-width:90px;
		background-color:#FAFAFA;
	}
	.field{
		width:97%;
	}
	.td_subject{
		padding-left:10px; 
	}
</style>

</head>
<body>

<h2>글쓰기</h2>
<form name="updateForm" action="boardUpdate.jsp" method="post">
<input type="hidden" name="id" value="<%=session.getAttribute("memId") %>">
<input type="hidden" name="seq" value="<%=seq %>">
<input type="hidden" name="pg" value="<%=pg %>">
<table>
	<tr>
		<td class="fieldName">글 번호</td>
		<td style="width:50px;text-align:center;">
			<%=boardDTO.getSeq() %> 
		</td> 
		<td class="fieldName">작성일</td>
		<td style="width:170px;text-align:center;">
			<%=boardDTO.getLogtime().toString() %> 
		</td>
		<td class="fieldName">작성자</td>
		<td>
			<%=boardDTO.getName() %> 
		</td>
		<td class="fieldName">조회수</td>
		<td>
			<%=boardDTO.getHit() %> 
		</td>
	</tr>
	<tr>
		<td class="fieldName">제 목</td>
		<td colspan="7" class="td_subject">
			<span id="span_subject"><%=boardDTO.getSubject() %></span>
			<input type="text" name="subject" id="subject" class="field" placeholder="제목 입력" value="<%=boardDTO.getSubject() %>"> 
		</td>
	</tr>
	<tr>
		<td class="fieldName">내 용</td>
		<td colspan="7" width="500px" class="content">
			<textarea id="ta" class="field" name="content" rows="20" placeholder="내용 입력" ><%=boardDTO.getContent() %></textarea>
			<pre id="pp"><%=boardDTO.getContent() %></pre>
		</td>
	</tr>
	<tr>
		<td colspan="8" align="center">
			<%if(id.equals(boardDTO.getId())){ %>
			<input type="button" id="modify" value="수정" onclick="checkBoardUpdate(<%=pg %>, <%=seq %>)">
			<input type="button" id="save" value="저장" onclick="boardUpdate()">
			<input type="button" id="cancel" value="취소" onclick="cancelUpdate()">
			<input type="button" id="delete" value="삭제" onclick="checkBoardDelete(<%=pg %>, <%=seq %>)">
			<%} %>
			<input type="button" value="돌아가기" onclick="location.href='boardList.jsp?pg=<%=pg %>'">
		</td>
	</tr>

</table>
</form>

</body>
<script type="text/javascript">
function checkBoardUpdate(pg, seq){
	document.getElementById("ta").style.display="block";
	document.getElementById("pp").style.display="none";
	document.getElementById("modify").style.display="none";
	document.getElementById("span_subject").style.display="none";
	
	document.getElementById("subject").style.display="inline";
	document.getElementById("save").style.display="inline";
	document.getElementById("cancel").style.display="inline";
	//location.href="boardUpdate.jsp?pg=" + pg + "seq=" + seq;
}

function boardUpdate(){
	document.updateForm.submit();
}

function cancelUpdate(){
	location.reload()
}

function checkBoardDelete(pg, seq){
	alert('삭제는 나중에~ 선생님과 함께');
	//location.href="boardUpdate.jsp?pg=" + pg + "seq=" + seq;
}
</script>

</html>