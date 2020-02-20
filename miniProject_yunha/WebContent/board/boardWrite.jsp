<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:if test="${ result == 1 }">
	글 작성이 완료되었습니다.<br>
	<input type="button" value="글 목록" onclick="location.href='boardList.do?pg=1'">
</c:if>

<script>
 
</script>
</body>
</html>