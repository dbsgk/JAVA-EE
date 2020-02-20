<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	window.onload=function(){
			alert("글 수정 완료");
			location.href="boardView.do?pg="+${pg}+"&seq="+${seq};
		}
</script>
