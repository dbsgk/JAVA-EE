<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>   
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>

<fmt:requestEncoding value="UTF-8"/>
<sql:update dataSource="jdbc/oracle">
	update usertable set name='${param.name}', pwd='${param.pwd}' where id='${param.id}'
</sql:update>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- <body onload="location.href='jstlList.jsp'"> -->

</body>
<script type="text/javascript">
window.onload=function(){
	alert("회원정보수정 완료");
	location.href='jstlList.jsp';
}
</script>
</html>