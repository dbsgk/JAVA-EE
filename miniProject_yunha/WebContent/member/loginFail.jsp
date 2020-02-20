<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test = "${loginResult == 'fail' }">
	<center><strong>로그인 실패</strong></center>
</c:if>
<!-- <input type="button" value="돌아가기" onclick="window.history.back()">
 -->