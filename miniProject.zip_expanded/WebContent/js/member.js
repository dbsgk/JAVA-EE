function loginCheck(){
	if(document.getElementById("id").value==""){
		alert("아이디를 입력하세요!");
		document.getElementById("id").focus();
	} else if(document.getElementById("pwd").value==""){
		alert("비밀번호를 입력하세요!");
		document.getElementById("pwd").focus();
	} else
		document.forms[0].submit();
}

$('#btn-login').click(function(){
	if(loginIdCheck() && loginPwdCheck()){
		$('form[name=loginForm]').submit();
	}
});

function loginIdCheck(){
	if($('#loginId').val()==''){
		$('#div-loginId').text('ID를 입력하세요.').attr('class', 'color-red');
		return false;
	}else if($('#loginId').val()!=''){
		$('#div-loginId').empty();
		return true;
	}
}
function loginPwdCheck(){
	if($('#loginPwd').val()==''){
		$('#div-loginPwd').text('비밀번호를 입력하세요.').attr('class', 'color-red');
		return false;
	}else if($('#loginPwd').val()!=''){
		$('#div-loginPwd').empty();
		return true;
	}
}
$('#loginId').focusout(function(){
	loginIdCheck();
});
$('#loginPwd').focusout(function(){
	loginPwdCheck();
});

$('#btn-writeForm').click(function(){
	$('#div-name').empty();
	$('#div-id').empty();
	$('#div-pwd').empty();
	$('#div-pwdcheck').empty();
	
	if($('#name').val() == ''){
		$('#div-name').text('이름을 입력하세요');
		$('#div-name').attr('class', 'color-red');
	}else if($('#id').val() == ''){
		$('#div-id').text('ID를 입력하세요');
		$('#div-id').attr('class', 'color-red');
	}else if($('#id').val()!=vId){
		$('#div-id').text('아이디 중복체크가 필요합니다.');
		$('#div-id').attr('class', 'color-red');
	}else if($('#pwd').val()==''){
		$('#div-pwd').text('비밀번호를 입력하세요');
		$('#div-pwd').attr('class', 'color-red');
		document.getElementById("pwd").focus();
	}else if($('#pwd').val() != $('pwdcheck').val()){
		$('#div-pwdcheck').text('비밀번호가 일치하지 않습니다!');
		$('#div-pwd').attr('class', 'color-red');
		document.getElementById("pwdcheck").focus();
	}else
		$('form[name=writeForm]').submit();
});

var vId;
function signUpCheck(){
	if(document.getElementById("name").value==""){
		alert("이름을 입력하세요!");
		document.getElementById("name").focus();
	} else if(document.getElementById("id").value==""){
		alert("아이디를 입력하세요!");
		document.getElementById("id").focus();
	} else if(document.getElementById("id").value!=vId){
		alert("아이디 중복체크가 필요합니다.");
	} else if(document.getElementById("pwd").value==""){
		alert("비밀번호를 입력하세요!");
		document.getElementById("pwd").focus();
	}else if(document.getElementById("pwd").value != document.getElementById("pwdcheck").value){
		alert("비밀번호가 일치하지 않습니다!");
		document.getElementById("pwdcheck").focus();
	} else
		document.writeForm.submit();
}

function updateCheck(){
	if(document.getElementById("pwd").value==""){
		alert("비밀번호를 입력하세요!");
		document.getElementById("pwd").focus();
	}else if(document.getElementById("pwd").value != document.getElementById("pwdcheck").value){
		alert("비밀번호가 일치하지 않습니다!");
		document.getElementById("pwdcheck").focus();
	}else 
		document.modifyForm.submit();
}

function idCheck(){
	let id = document.writeForm.id.value;
	if(id=="")
		alert("Id를 입력하세요!");
	else
		window.open("checkId.do?id="+id,"",
			"width=500 height=180 left=800 top=100");
}

function idUse(id){
	opener.document.forms[0].id.value=id;
	opener.document.forms[0].pwd.focus();
	opener.vId = id;
	self.close();
}

function checkPost() {
	window.open("checkPost.do", "", "width=550 height=400 scrollbars=yes")
}

function selectAddr(zipcode, addr){
	opener.document.forms[0].zipcode.value = zipcode;
	opener.document.forms[0].addr1.value = addr;
	opener.document.forms[0].addr2.focus();
	self.close();
}

function setDate(){
	alert("!");
}

function checkPost2() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
            } 

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('zipcode').value = data.zonecode;
            document.getElementById("addr1").value = addr + extraAddr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("addr2").focus();
        }
    }).open();
}