package board.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.control.CommandProcess;

import board.dao.BoardDAO;

public class BoardReplyAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//데이터
		String pseq = (String)request.getAttribute("pseq");
		String pg = (String)request.getAttribute("pg");
		String subject = (String)request.getAttribute("subject");
		String content = (String)request.getAttribute("content");
		
		HttpSession session = request.getSession();

		String id = (String)session.getAttribute("memId");
		String name = (String)session.getAttribute("memName");
		String email = (String)session.getAttribute("email1"+"@"+"email2");
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("id",id);
		map.put("name",name);
		map.put("email",email);
		map.put("subject",subject);
		map.put("content",content);
		map.put("pseq",pseq);
		
		//DB
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.boardReply(map);
		
		request.setAttribute("display", "/board/boardReply.jsp");
		return "/main/index.jsp";
	}

}
