<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="member.bean.AddrDTO"%>
<%@page import="java.util.List"%>
<%@page import="member.dao.MemberDAO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/member.css"></link>

</head>
<body>

<%
MemberDAO memberDAO = MemberDAO.getInstance();
List<String> list = memberDAO.getSido();

request.setCharacterEncoding("utf-8");

String sido = request.getParameter("sido")!=null ? request.getParameter("sido") : "";
String sigungu = request.getParameter("sigungu")!=null ? request.getParameter("sigungu") : "";
String roadname = request.getParameter("roadname")!=null ? request.getParameter("roadname") : "";
String buldingname = request.getParameter("buldingname")!=null ? request.getParameter("buldingname") : "";

List<AddrDTO> addrList = memberDAO.getAddr(sido, sigungu, roadname);
%>

<form name="form" method="post" action="checkPost.jsp">
<table>
	<tr >
		<td class="fieldName">시도</td>
		<td><select name="sido">
				<option value="">시도선택
			<%for(String s : list){ %>
				<option value="<%=s %>"<%if(sido.equals(s)){ %> selected<%} %>>
					<%=s %>
			<%} %>
			</select>
		</td>
		<td class="fieldName">시,군,구</td>
		<td><input type="text" name="sigungu" id="sigungu" value="<%=sigungu %>"></td>
	</tr>
	<tr>
		<td class="fieldName">도로명</td>
		<td colspan="3">
			<input type="text" name="roadname" id="roadname" style="width:82%;" value="<%=roadname %>">
			<input type="submit" value="검색">
		</td>
	</tr>
	<tr>
		<td class="fieldName">우편번호</td>
		<td class="fieldName" colspan="3">주소</td>
	</tr>
	<%int j = 0; %>
	<%for(AddrDTO addrDTO : addrList){ %>
		<%
		String zipcode = addrDTO.getZipcode().trim();
		
		String new_addr = addrDTO.getSido() + " ";
		new_addr += addrDTO.getSigungu()!=null ? addrDTO.getSigungu() + " " : "";
		new_addr += addrDTO.getRoadname()!=null ? addrDTO.getRoadname() + " " : "";
		new_addr += addrDTO.getBuildingname()!=null ? addrDTO.getBuildingname() : ""; 
		
		String old_addr = addrDTO.getSido() + " ";
		old_addr += addrDTO.getSigungu()!=null ? addrDTO.getSigungu() + " ":"";
		old_addr += addrDTO.getYubmyundong() + " ";
		old_addr += addrDTO.getRi()!=null ? addrDTO.getRi():"";
		%>
		
		<tr>
			<td class="<%if(++j%2==1){ %>
								fieldNames
							<%} else {%>
								fieldName
							<%} %>">
				<a href="#" onclick="selectAddr('<%=zipcode%>', '<%=new_addr %>')"><%=zipcode %></a>
			</td>
			<td colspan="3" <%if(j%2==1){ %>class="shadow"<%} %>>
				<div class="newAddr">
				<a href="#" onclick="selectAddr('<%=zipcode%>', '<%=new_addr %>')">
					<%=new_addr %> 
				</a>
				</div>
				<div class="oldAddr">
				<a href="#" onclick="selectAddr('<%=zipcode%>', '<%=old_addr %>')">
					(구)<%=old_addr %>
				</a>
				</div>
			</td>
		</tr>
	<%} %>
</table>
</form>

</body>

<script type="text/javascript" src="../js/member.js"></script>


</html>