function loginCheck(){
	if(document.getElementById("id").value==""){
		alert("아이디를 입력하세요!");
		document.getElementById("id").focus();
	} else if(document.getElementById("pwd").value==""){
		alert("비밀번호를 입력하세요!");
		document.getElementById("pwd").focus();
	} else
		document.loginForm.submit();
}

var vId;
/*function signUpCheck(){
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
*/

$('#loginFormBtn').click( function(){
	$('#div_loginId').empty();
	$('#div_loginPwd').empty();
	if($('#loginId').val()==''){
		$('#div_loginId').text('아이디를 입력하세요');
		$('#div_loginId').css('color','red');
		$('#div_loginId').css('font-size','8pt');
		$('#div_loginId').css('font-weight','bold');
	}else if($('#loginPwd').val()==''){//id
		$('#div_loginPwd').text('비밀번호를 입력하세요');
		$('#div_loginPwd').css('color','red');
		$('#div_loginPwd').css('font-size','8pt');
		$('#div_loginPwd').css('font-weight','bold');
		
	}
} );
$('#writeFormBtn').click(function(){
	$('#div_name').empty();
	$('#div_id').empty();
	$('#div_pwd').empty();
	$('#div_repwd').empty();
	//if($('input[id=name
	if($('#name').val()==''){
		$('#div_name').text('이름을 입력하세요');
		$('#div_name').css('color','red');
		$('#div_name').css('font-size','8pt');
		$('#div_name').css('font-weight','bold');
	}else if($('#id').val()==''){//id
		$('#div_id').text('아이디를 입력하세요');
		$('#div_id').css('color','red');
		$('#div_id').css('font-size','8pt');
		$('#div_id').css('font-weight','bold');
	}else if($('#pwd').val()==''){//pwd
		$('#div_pwd').text('비밀번호를 입력하세요');
		$('#div_pwd').css('color','red');
		$('#div_pwd').css('font-size','8pt');
		$('#div_pwd').css('font-weight','bold');
	}else if($('#pwd').val() != $('#pwdcheck').val()){//repwd
		$('#div_repwd').text('비밀번호가 다릅니다.');
		$('#div_pwd').css('color','red');
		$('#div_pwd').css('font-size','8pt');
		$('#div_pwd').css('font-weight','bold');
	}else if($('#id').val() != $('#check').val()){
		$('#div_idcheck').text('중복체크 하세요');
		$('#div_idcheck').css('color','red');
		$('#div_idcheck').css('font-size','8pt');
		$('#div_idcheck').css('font-weight','bold');
	}else 
		$('form[name=writeForm]').submit;
	
});

$('#loginId').focusout(function(){
	$('#div_loginId').empty();
	$('#div_loginPwd').empty();
	if($('#loginId').val()==''){
		$('#div_loginId').text('아이디를 입력하세요')
						 .css('color','red')
		 				 .css('font-size','8pt')
		 				 .css('font-weight','bold');
	}else if($('#loginPwd').val()==''){//id
		$('#div_loginPwd').text('비밀번호를 입력하세요')
							.css('color','red')
							.css('font-size','8pt')
							.css('font-weight','bold');
	}	
});

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
	let sId = document.writeForm.id.value;
	if(sId=="") 
		alert("먼저 아이디를 입력하세요");
	else
		window.open("/miniProject/member/checkId.do?id="+sId, "", "width=500 height=180 left=800 top=100");
}

function idUse(id){
	opener.writeForm.id.value=id;
	opener.writeForm.pwd.focus();
	opener.vId = id;
	self.close();
}

function checkPost() {
	window.open("/miniProject/member/checkPost.do", "", "width=550 height=400 scrollbars=yes")
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