<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h3>
<a href="../main/index.do"><img src="../image/img1.jpg" width="50px"></a> MVC를 이용한 미니 프로젝트
</h3>
<style>
#topdiv a:link{color:black; }
ul {
    list-style:none;
    margin:50;
    padding:0;
}

li {
	display:inline-block;/* li는 block특성을 가지고 있어서  이렇게 해주고 float를 center로 해야함.*/
    margin: 0px;
    padding-left:10px;
    padding-right:10px;
    border : 20;
    float: center;
}
</style>
<div id="topDiv" style="border:1px red solid" align="center">
<nav>
<ul>
	<c:if test = "${memId != null }">
	<li><a href="../member/modifyForm.do">회원정보수정</a></li>
	<li><a href="../member/logout.do">로그아웃</a></li>
	<li><a href="../board/boardWriteForm.do">글쓰기</a></li>
	</c:if>
	
	<li><a href="../board/boardList.do?pg=1">목록</a></li>
	
	<c:if test = "${memId != null}">
	<%-- <c:if test = "${memId != null && memId == 'admin'}"> --%>
	<li><a href="../imageboard/imageboardWriteForm.do">이미지등록</a></li>
	</c:if>
	<li><a href="../imageboard/imageboardList.do">이미지목록</a></li>
</ul>
</nav>
</div>