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
		//데이터
		String id = request.getParameter("loginId");
		String pwd = request.getParameter("loginPwd");
		
		//DB
		MemberDAO memberDAO = MemberDAO.getInstance();
		String name = memberDAO.getMemberName(id, pwd);
		MemberDTO memberDTO = memberDAO.getMember(id);
		
		String display = (String) request.getAttribute("display");
		System.out.println(display);
		
		//응답
		request.setAttribute("display", "/template/body.jsp");
		if(name==null) {
			return "/main/index.jsp";
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("memName", name);
		session.setAttribute("memId", id);
		session.setAttribute("memEmail", memberDTO.getEmail1() + "@" + memberDTO.getEmail2());
		
		//request.setAttribute("display", "/template/body.jsp");
		return "/main/index.jsp";
	}

}