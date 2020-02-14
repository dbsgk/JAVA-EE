package member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.control.CommandProcess;

public class LogoutAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//세션
		HttpSession session = request.getSession();
		//session.removeAttribute("memName");
		//session.removeAttribute("memId");
		session.invalidate();//무효화
		return "/member/logout.jsp";
	}

}
