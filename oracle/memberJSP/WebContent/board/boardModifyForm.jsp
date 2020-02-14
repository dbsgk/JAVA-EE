<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="boardDAO" class="board.dao.BoardDAO"/>
<%
request.setCharacterEncoding("UTF-8");
String seq = request.getParameter("seq");
String pg = request.getParameter("pg");
String subject = request.getParameter("subject");
String content = request.getParameter("content");


%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardModifyForm.jsp</title>
</head>
<body>
 <h3>글 수정</h3>
 <form name="boardModifyForm" action="boardModify.jsp" method="post">
 <table border=1 cellpadding="3" cellspacing="0">
        <tr>
        <td class='key'>제목</td>
        <td class='value'>
          <input type="hidden" name="seq" value=<%=seq%>>
          <input type="text" name="subject"size="50" value=<%=subject%> placeholder="제목을 입력해주세요">
        </td>
      </tr>
        <tr>
        <td class='key'>내용</td>
        <td class='value'>
          <textarea name="content" cols="60" rows="10" placeholder="내용을 입력해주세요"><%=content%></textarea>
        </td>
      </tr>
        <tr>
          <td colspan="2" align="center">
            <input type="button" value="글수정" onclick="checkBoardModifyForm()">
            <input type="reset" value="다시작성">
          </td>
        </tr>
      </table>
  </form>
</body>
<script type="text/javascript">
function checkBoardModifyForm(){
	if(document.boardModifyForm.subject.value==""){
		alert("제목을 입력해주세요.");
		document.boardModifyForm.subject.focus();
	}else if(document.boardModifyForm.content.value==""){
		alert("내용을 입력해주세요.");
		document.boardModifyForm.content.focus();
	}else{
		document.boardModifyForm.submit();		
	}
}
</script>
</html>