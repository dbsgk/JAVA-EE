package board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.control.CommandProcess;

import board.bean.BoardDTO;
import board.dao.BoardDAO;

public class BoardWriteAction implements CommandProcess {
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		BoardDAO boardDAO = BoardDAO.getInstance();
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("memId");
		String name = (String) session.getAttribute("memName");
		String email = (String) session.getAttribute("memEmail");

		String subject = request.getParameter("subject");
		subject = subject.replaceAll("<","&lt;");
		subject = subject.replaceAll(">","&gt;");
		subject = subject.replaceAll("\"","&quot;");
		subject = subject.replaceAll("&","&amp;");
		System.out.println("subject 변환 후 : " + subject);

		String content = request.getParameter("content");
		content = content.replaceAll("<","&lt;");
		content = content.replaceAll(">","&gt;");
		content = content.replaceAll("\"","&quot;");
		content = content.replaceAll("&","&amp;");
		System.out.println("content 변환 후 : " + content);
		
		BoardDTO boardDTO = new BoardDTO(id, name, email, subject, content);
		
		if(boardDAO.insert(boardDTO))
			request.setAttribute("display", "/board/boardList.jsp?pg=1");
		else
			request.setAttribute("display", "/board/boardWriteFail.jsp");
		
		return "/main/index.jsp";
	}
}
