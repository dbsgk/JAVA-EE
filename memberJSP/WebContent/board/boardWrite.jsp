<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//데이터
request.setCharacterEncoding("utf-8");
%>

<jsp:useBean id="memberDTO" class="member.bean.MemberDTO"/><!-- 데이터 가져오기  -->
<jsp:setProperty property="*" name="memberDTO"/>
<!DOCTYPE html>
<%
//데이터
/* id,name,email 세션을 통해서 얻어온다.

email1, eamil2은  member테이블에 있는데 이 2개를 합쳐서  email에 넣으면 됨 */
//DB

//응답
%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>