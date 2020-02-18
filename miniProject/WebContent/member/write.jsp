<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>

<script>
window.onload=function(){
	if(${su}==1){
		alert("회원가입 성공");
		location.href="/miniProject/main/index.do";
	}else{
		alert("회원가입 실패");
		location.href="/miniProject/member/writeForm.do";
	}
};
</script>