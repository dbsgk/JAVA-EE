function checkWriteForm(){
	//유효성 체크
	if(document.writeForm.name.value=="")
		alert("이름을 입력하세요");
	else if(document.writeForm.id.value=="")
		alert("아이디를 입력하세요");
	//else if(document.writeForm.pwd.value=="")
	else if(document.getElementById("pwd").value=="")	
		alert("비밀번호를 입력하세요");
	else if(document.writeForm.pwd.value != document.writeForm.repwd.value)
		alert("비밀번호가 맞지 않습니다");
	else if(document.writeForm.id.value != document.writeForm.check.value)
		alert("중복체크 하세요");
	else
		document.writeForm.submit();
}

function checkModifyForm(){
	//유효성 체크
	if(document.modifyForm.name.value=="")
		alert("이름을 입력하세요");
	else if(document.modifyForm.pwd.value=="")
	//else if(document.getElementById("pwd").value=="")	
		alert("비밀번호를 입력하세요");
	else if(document.modifyForm.pwd.value != document.modifyForm.repwd.value)
		alert("비밀번호가 맞지 않습니다");
	else
		document.modifyForm.submit();
}
function checkId(){
	let sId = document.writeForm.id.value;
	if(sId=="") 
		alert("먼저 아이디를 입력하세요");
	else
		window.open("checkId.jsp?id="+sId, "", "width=300 height=100 left=500 top=100");
}

function checkIdClose(id){
	opener.writeForm.id.value = id;
	opener.writeForm.check.value = id;
	window.close();
	opener.writeForm.pwd.focus();	
}
function checkPostClose(zipcode,address){
	opener.writeForm.zipcode.value = zipcode;
	opener.writeForm.addr1.value = address;
	window.close();
	opener.writeForm.addr2.focus();	
}

function checkLoginForm(){
	if(document.loginForm.id.value=="")
		alert("아이디를 입력하세요");
	else if(document.loginForm.pwd.value=="")	
		alert("비밀번호를 입력하세요");
	else
		document.loginForm.submit();
}

function checkPost(){
	window.open("checkPost.jsp","","width=400 height=400 scrollbars=yes");
}
function test(){
	alert("윈도우 온로드 테스트");
}











