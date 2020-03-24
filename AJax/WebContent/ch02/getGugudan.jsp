<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
int dan = Integer.parseInt(request.getParameter("dan"));
String output = "";
for(int i=1; i<=9; i++){
	output += (dan + " * " + i + " = " + (dan*i));
}

%>
<%=output%>