package member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.CommandProcess;

import member.dao.MemberDAO;

public class CheckIdAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//데이터
		String id = request.getParameter("id");
		System.out.println(id);
		//DB
		MemberDAO memberDAO = MemberDAO.getInstance();
		request.setAttribute("id", id);
		boolean exist = memberDAO.checkId(id);
		if(!exist)return "/member/checkIdOk.jsp";
		else return "/member/checkIdFail.jsp";
	}

}
