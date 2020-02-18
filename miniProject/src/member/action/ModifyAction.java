package member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.CommandProcess;

import member.bean.MemberDTO;
import member.dao.MemberDAO;

public class ModifyAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		MemberDTO memberDTO = request.getAttribute(name);
		MemberDAO memberDAO = MemberDAO.getInstance();
		boolean result = memberDAO.update(memberDTO);
		request.setAttribute("display", "/member/modify.jsp");
		return "/main/index.jsp";
	}

}
