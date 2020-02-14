<%@page import="com.jstl.PersonDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%
ArrayList<String> list = new ArrayList<String>();
list.add("호랑이");
list.add("사자");
list.add("기린");
list.add("코끼리");
list.add("타조");
list.add("코알라");
list.add("여우");

request.setAttribute("list",list);

//페이지 이동
//response.sendRedirect("jstlTest.jsp");


ArrayList<PersonDTO> list2 = new ArrayList<PersonDTO>();
list2.add(new PersonDTO("홍길동",20));
list2.add(new PersonDTO("코난",20));
list2.add(new PersonDTO("또치",20));

request.setAttribute("list2",list2);



RequestDispatcher dispatcher = request.getRequestDispatcher("jstlTest.jsp");//상대번지
dispatcher.forward(request, response);//제어권 넘기기
%>

<%-- <jsp:forward page="jstlTest.jsp"/> --%>