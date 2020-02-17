<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="login">
	<c:if test="${memId == null }">
		<jsp:include page="/member/loginForm.jsp"/>
		<jsp:include page="/member/loginFail.jsp"/>
	</c:if>
	<c:if test = "${memId != null }">
		${memName }님<br>환영합니다!
	</c:if>
</div>
