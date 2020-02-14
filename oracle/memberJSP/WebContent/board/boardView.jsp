<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="board.bean.BoardDTO" %>

<jsp:useBean id="boardDAO" class="board.dao.BoardDAO" />

<%
//데이터
String id = (String)session.getAttribute("memId");
int seq = Integer.parseInt(request.getParameter("seq"));
int pg = Integer.parseInt(request.getParameter("pg"));

//조회수 - 새로고침 방지
Cookie[] ar = request.getCookies();
if(ar!=null){
	for(int i=0;i<ar.length; i++){
		if(ar[i].getName().equals("memHit")){
			boardDAO.boardHit(seq);//조회수 증가
			ar[i].setMaxAge(0);
			response.addCookie(ar[i]);
		}
	}
}


//DB
BoardDTO boardDTO = boardDAO.getBoard(seq);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.contents{
	white-space: pre-wrap;
	word-break: break-all;
	width: 600px;
}
</style>
</head>
<body>
<h2>작성한 글 확인</h2>
<form name="boardViewForm" action="boardModifyForm.jsp"><!-- method="post"  -->
<table border="1" cellpadding="5" frame="hsides" rules="rows"  style="table-layout: fixed" >
<input type="hidden" name="seq" value="<%=seq%>">
<input type="hidden" name="pg" value="<%=pg%>">
<input type="hidden" name="subject" value="<%=boardDTO.getSubject()%>">
<input type="hidden" name="content" value="<%=boardDTO.getContent()%>">
	<tr>
		<td colspan="3">
			<%-- <input type="text"  name="subject"  value="<%=boardDTO.getSubject() %>"size="50" readonly> --%><h3><%=boardDTO.getSubject() %></h3>
		</td>
	</tr>
	<tr>
		<td width="150">글번호: <%=boardDTO.getSeq() %></td>
		<td width="150">작성자: <%=boardDTO.getId()%></td>
		<td width="150">조회수: <%=boardDTO.getHit() %></td>
	</tr>
	<tr>
		<td colspan="3 " height="200" valign="top" style="word-break:break-all" > 
			<pre class="contents" style="word-break:break-all; white-space:pre-wrap;"><%=boardDTO.getContent() %></pre>
		</td>
	</tr>
	<tr>
		<td colspan="2" align="center">
		<input type="button" value="목록" onclick="location.href='boardList.jsp?pg=<%=pg%>'">
		<%if(id.equals(boardDTO.getId())){ %>
			<input type="button" value="수정" onclick="mode(1)"> 
			<input type="button" value="삭제" onclick="mode(2)">
		<%} %>
		</td>
	</tr>
</table>
</form>
</body>
<script type="text/javascript">
function mode(num){
	if(num==1){
		document.boardViewForm.method = 'post';
		document.boardViewForm.action = 'boardModifyForm.jsp';
		document.boardViewForm.submit();
	}else if(num==2){
		document.boardViewForm.method = 'post';
		document.boardViewForm.action = 'boardDelete.jsp';
		document.boardViewForm.submit();
	}
}
</script>
</html>