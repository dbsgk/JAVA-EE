<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
	h2{text-align:center}
	table{margin: 10px auto;}
</style>
<body>
<h2>구구단</h2>
<% 
String result="";
for(int i=1;i<=9;i++){
	result +="<tr>";
	for(int dan=2;dan<=9;dan++){
		result+= "<td>"+dan +"*"+ i+"="+ dan*i +"<td>";
	}
	result +="</tr>";
}
%>
<table>
<%= result %>
</table>
<table border="1">
<% for(int i=1;i<=9;i++){%>
	<tr>
	<% for(int dan=2;dan<=9;dan++){ %>
		<td><%= dan %> * <%= i %> = <%=dan*i %></td>
	<%}//dan %>
	</tr>
<%}//i %>
</table>
</body>
</html>