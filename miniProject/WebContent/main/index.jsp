<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/board.css">
<link rel="stylesheet" type="text/css" href="../css/imageboard.css">

</head>
<body>
<table id="table-main">
	<tbody>
		<tr>
			<td colspan="2">
				<jsp:include page="/template/top.jsp"/>
			</td>
		</tr>
		<tr>
			<td id="td-left">
				<jsp:include page="/template/left.jsp"/>
			</td>
			<td id="td-main">
				<jsp:include page="${display }" />
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<jsp:include page="/template/bottom.jsp"/>
			</td>
		</tr>
	</tbody>
</table>
</body>
</html>