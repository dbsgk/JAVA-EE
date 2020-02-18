<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<body onload = "updateCheck('<%=result %>', <%=pg %>, <%=seq %>)">

</body>

<script type="text/javascript">
	function updateCheck(str, pg, seq){
		alert(str);
		location.href="boardView.jsp?pg="+pg+"&seq="+seq;
	}

</script>
