<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="member.dao.MemberDAO, member.bean.PostDTO"%>
<!DOCTYPE html>
<%
request.setCharacterEncoding("utf-8");
String sido = request.getParameter("sido");
String sigungu = request.getParameter("sigungu");
String roadname = request.getParameter("roadname");
List<PostDTO> list = null;
//DB
MemberDAO memberDAO = MemberDAO.getInstance();
if(sido!=null && roadname!=null){ 
	list = memberDAO.getPost(sido,sigungu,roadname);
} 

response.setContentType("text/html; charset=UTF-8");
%>
<html>
<head>
<meta charset="UTF-8">
<title>우편번호찾기</title>
<style type="text/css">
td {font-size: 10pt;}
</style>
</head>
<body>
<form action="checkPost.jsp" method="post" name="postForm">
<table border="1" cellspacint="0" cellpadding="3">
	<tr>
		<td align="center">시도</td>
		<td>
		<select id="sido" name="sido">
			<option value="" selected>시도선택</option>
			<option value="서울">서울</option>
			<option value="인천">인천</option>
			<option value="대전">대전</option>
			<option value="대구">대구</option>
			<option value="울산">울산</option>
			<option value="세종">세종</option>
			<option value="광주">광주</option>
			<option value="경기">경기</option>
			<option value="강원">강원</option>
			<option value="전남">전남</option>
			<option value="전북">전북</option>
			<option value="경남">경남</option>
			<option value="경북">경북</option>
			<option value="충남">충남</option>
			<option value="충북">충북</option>
			<option value="부산">부산</option>
			<option value="제주">제주</option>
		</select>
		</td>
		<td align="center">시.군.구</td>
		<td>
		<input type="text" name="sigungu" id="sigungu">
		</td>
	</tr>
	<tr>
		<td align="center">도로명</td>
		<td colspan="3">
		<input type="text" name="roadname" id="roadname"><input type="submit" value="검색">
		</td>
	</tr>
	<tr>
		<td>우편번호</td>
		<td colspan="3" align="center">주소</td>
	</tr>
</form>
<%
if(list!=null){
for(int i=0;i<list.size();i++){
	String zipcode= (list.get(i).getZipcode().length()!=5?("0"+list.get(i).getZipcode()):list.get(i).getZipcode());
	String addr1 = list.get(i).getSido()+" "+list.get(i).getSigungu()+" "+list.get(i).getYubmyundong()+" "+list.get(i).getRi()+" "+list.get(i).getRoadname()+" "+list.get(i).getBuildingname();
%>
<form method="get" >
<tr>
<td>
<%-- <a href="javascript:selectPost(<%= list.get(i)%>)"><%=zipcode%></a> --%>
<a href="javascript:selectPost(<%= zipcode%>,<%=addr1%>)"><%=zipcode%></a>
</td>

<td colspan="3">
<a href="#" onclick="selectPost('<%= zipcode%>','<%=addr1%>')"><%=addr1%></a>
</td>


</tr>
<%

}//for
}//if
%>


</form>
</table>

</body>
<script type="text/javascript" src="http://localhost:8080/memberJSP/js/member.js"></script>
</html>