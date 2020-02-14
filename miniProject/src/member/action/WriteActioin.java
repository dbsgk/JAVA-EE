package member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.CommandProcess;

import member.bean.MemberDTO;
import member.dao.MemberDAO;

public class WriteActioin implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		MemberDTO memberDTO = new MemberDTO();
		boolean result = MemberDAO.getInstance().insert(memberDTO);
		request.setAttribute("result", result);
		request.setAttribute("display", "/member/write.jsp");
		return "/main/index.jsp";
	}

}
