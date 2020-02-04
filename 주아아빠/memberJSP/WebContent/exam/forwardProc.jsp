<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setAttribute("apple", "사과");

// 페이지 이동
// forward와 같은 의미로 쓰이는 것
RequestDispatcher dispatcher = request.getRequestDispatcher("forwardResult.jsp"); // 반드시 상대주소로 작성
dispatcher.forward(request, response); // 제어권 넘기기
%>

<%-- <jsp:forward page="forwardResult.jsp" /> --%>

