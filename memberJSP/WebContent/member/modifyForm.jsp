<%@page import="member.bean.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="memberDAO" class="member.dao.MemberDAO" scope="page"/>
<jsp:setProperty property="*" name="memberDTO"/>

<%
//데이터
String id = (String)session.getAttribute("memId");

//DB
MemberDTO memberDTO = memberDAO.getMember(id);

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보수정</title>
</head>
<body>
<form name="modifyForm" method="post" action="modify.jsp">
<h2>회원정보수정</h2>
<table border="1" cellpadding="3" cellspacing="0">
<tr>
	<td width="90" align="center">이름</td>
	<td><input type="text" name="name" placeholder="이름 입력" value="<%=memberDTO.getName()%>"></td>
</tr>
<tr>
	<td align="center">아이디</td>
	<td>
<%-- 	<jsp:getProperty property="id" name="memberDTO"/>+
<jsp:getProperty property="y" name="dataDTO"/>= 
<%=dataDTO.getX()+dataDTO.getY() %><br> --%>
		<input type="text" name="id" id="id" size="25" readonly value="<%=memberDTO.getId()%>">
		<%-- <jsp:getProperty property="id" name="memberDTO"/> --%>
		
		<input type="hidden" name="check" value="">
	</td>
</tr>
<tr>
	<td align="center">비밀번호</td>
	<td><input type="password" name="pwd" id="pwd" size="30" placeholder=""></td>
</tr>
<tr>
	<td align="center">재확인</td>
	<td><input type="password" name="repwd" size="30"></td>
</tr>
<tr>
	<td align="center">성별</td>
	<td><input type="radio" name="gender" value="0" checked>남
		<input type="radio" name="gender" value="1">여
	</td>
</tr>
<tr>
	<td align="center">이메일</td>
	<td><input type="text" name="email1" size="15" value="<%=memberDTO.getEmail1()%>"> 
	@ 
	<input type="text" name="email2" list="email2" placeholder="직접입력">
	<datalist id="email2">
	 <option value="gmail.com">
	 <option value="hanmail.net">
	 <option value="naver.com">
	</datalist>
	</td>
</tr>
<tr>
	<td align="center">핸드폰</td>
	<td>
	<select name="tel1" style="width: 60px;">
	<option value="010">010
	<option value="011">011
	<option value="019">019
	</select>
	 - 
	 <input type="text" name="tel2" size="5" value="<%=memberDTO.getTel2()%>"> 
	 - 
	 <input type="text" name="tel3" size="5" value="<%=memberDTO.getTel3()%>"></td>
</tr>
<tr>
	<td align="center">주소</td>
	<td><input type="text" name="zipcode" id="zipcode" size="6" readonly value="<%=memberDTO.getZipcode()%>"> 
	<input type="button" value="우편번호검색" onclick="checkPost()">
	<br>
	<input type="text" name="addr1" id="addr1" size="50" placeholder="주소" readonly value="<%=memberDTO.getAddr1()%>">
	<br>
	<input type="text" name="addr2" id="addr2" size="50" placeholder="상세 주소" value="<%=memberDTO.getAddr2()%>">
</tr>

<tr>
	<td colspan="2" align="center">
	<input type="button" value="회원정보수정" onclick="javascript:checkModifyForm()">
	                                   
	<input type="reset" value="다시작성">
</tr>
</table>
</form>
</body>
<script type="text/javascript" src="http://localhost:8080/memberJSP/js/member.js"></script>
<script type="text/javascript">
window.onload = function() {
	document.modifyForm.gender['<%=memberDTO.getGender()%>'].checked = true;
	document.modifyForm.email2.value = '<%=memberDTO.getEmail2()%>';
	
	 document.modifyForm.tel1.value = '<%=memberDTO.getTel1()%>';
	 <%-- document.getElementById('tel1').value = '<%=memberDTO.getTel1()%>'; --%>
}
</script>
</html>















