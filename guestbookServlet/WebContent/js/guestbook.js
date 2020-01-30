function checkGuestbookWriteForm(){
	if(document.guestbookWriteForm.subject.value=="")
		alert("제목을 입력해주세요.");
	else if(document.guestbookWriteForm.content.value=="")
		alert("내용을 입력해주세요.");
	else
		document.guestbookWriteForm.submit();
}

function showList(){
  window.open(""
				  ,"viewer"
				  ,"width=480 height=380 left=500 top=100");
  document.guestbookWriteForm.action = "http://localhost:8080/guestbookServlet/GuestbookListServlet";
  document.guestbookWriteForm.target = "viewer";
  document.guestbookWriteForm.submit();
}
