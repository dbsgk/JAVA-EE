package member.action;

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
		//request.setCharacterEncoding("utf-8"); 이미 Servlet에서 한글처리 하고 와서 여기서 안해도 됨.
		String sido = request.getParameter("sido");
		String sigungu = request.getParameter("sigungu");
		String roadname = request.getParameter("roadname");

		System.out.println(sido+", "+sigungu+", "+roadname);

		//DB
		MemberDAO memberDAO = MemberDAO.getInstance();
		List<AddrDTO> addrList = null;
		if(sido!=null && roadname!=null){//값이 안들어올 때 
			if(sido!="" && roadname!=""){//데이터 입력안하고 누를 때
				addrList = memberDAO.getAddr(sido,sigungu,roadname);
			}
		}


		/*
		 * MemberDAO memberDAO = MemberDAO.getInstance(); List<String> list =
		 * memberDAO.getSido();
		 * 
		 * request.setCharacterEncoding("utf-8");
		 * 
		 * String sido = request.getParameter("sido")!=null ?
		 * request.getParameter("sido") : ""; String sigungu =
		 * request.getParameter("sigungu")!=null ? request.getParameter("sigungu") : "";
		 * String roadname = request.getParameter("roadname")!=null ?
		 * request.getParameter("roadname") : ""; String buldingname =
		 * request.getParameter("buldingname")!=null ?
		 * request.getParameter("buldingname") : "";
		 * 
		 * List<AddrDTO> addrList = memberDAO.getAddr(sido, sigungu, roadname);
		 */
		request.setAttribute("addrList", addrList);
		return "/member/checkPost.jsp";
	}

}
