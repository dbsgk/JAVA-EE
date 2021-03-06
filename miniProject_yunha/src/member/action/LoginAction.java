
package member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.control.CommandProcess;

import member.bean.MemberDTO;
import member.dao.MemberDAO;

public class LoginAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		System.out.println("loginAction");
		//데이터
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		//DB
		MemberDAO memberDAO = MemberDAO.getInstance();
		MemberDTO memberDTO = memberDAO.getMemberDTO(id, pwd);
		
		//응답
		String loginResult = null;
		if(memberDTO!=null) {
			//세션
			HttpSession session = request.getSession();
			session.setAttribute("memName", memberDTO.getName());
			session.setAttribute("memId", id);
			session.setAttribute("memEmail", memberDTO.getEmail1()+"@"+memberDTO.getEmail2());
			session.setAttribute("memberDTO", memberDTO);
			loginResult = "success";
		}else {
			loginResult = "fail";
		}
		
		request.setAttribute("display",	"/template/body.jsp");
		request.setAttribute("loginResult", loginResult);
		return "/main/index.jsp";
	}

}
