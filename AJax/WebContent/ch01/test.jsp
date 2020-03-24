<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.Calendar"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="jspnow" class="java.util.Date" />
<fmt:requestEncoding value="UTF-8"/>
<%
String name = request.getParameter("name");
String birth = request.getParameter("birth");
int currentYear = Calendar.getInstance().get(Calendar.YEAR);
int year = Integer.parseInt(birth.substring(0, 4));
int month = Integer.parseInt(birth.substring(4, 6));
int day = Integer.parseInt(birth.substring(6, 8));
int age = currentYear-year+1;
%>
<%=name %>의 생일은 <%= year%>년 <%=month %>월 <%=day %>일 입니다.
나이는 <%=age %>입니다.

${param.name }의 생일은 