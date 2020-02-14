package member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.CommandProcess;

//얘는 jsp 파일이 어디있다만 알려줘서 거기로 보내기만 하면 됨 
public class WriteFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		return "/member/writeForm.jsp";
	}

}
