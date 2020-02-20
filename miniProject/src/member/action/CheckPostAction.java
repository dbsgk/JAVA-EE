package member.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.CommandProcess;

import member.bean.AddrDTO;
import member.dao.MemberDAO;

public class CheckPostAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//데이터
		MemberDAO memberDAO = MemberDAO.getInstance();
		List<String> list = memberDAO.getSido();

		//request.setCharacterEncoding("utf-8"); 서버에서 이미 한글 인코딩 처리를 한 뒤 넘어오므로 여기서는 안 해도 됨

		String sido = request.getParameter("sido")!=null ? request.getParameter("sido") : "";
		String sigungu = request.getParameter("sigungu")!=null ? request.getParameter("sigungu") : "";
		String roadname = request.getParameter("roadname")!=null ? request.getParameter("roadname") : "";

		//DB
		List<AddrDTO> addrList;
		if((sido+sigungu+roadname).length()==0) {
			addrList = new ArrayList<AddrDTO>();
		}else {
			addrList = memberDAO.getAddr(sido, sigungu, roadname);
		}
		
		//응답
		request.setAttribute("sidoList", list);
		request.setAttribute("addrList", addrList);
		request.setAttribute("sido", sido);
		
		return "/member/checkPost.jsp";
	}

}