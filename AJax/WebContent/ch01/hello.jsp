<!-- 서버 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %><!-- 한글처리 -->
<fmt:requestEncoding value="UTF-8"/><!-- 이것까지가 한글처리 -->

<%-- <%
String name = request.getParameter("name");
%>

안녕하세요 <%=name%>님 --%> 
안녕하세요 ${param.name}님
