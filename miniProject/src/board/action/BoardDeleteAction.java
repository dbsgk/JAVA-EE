package board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.CommandProcess;

import board.dao.BoardDAO;

public class BoardDeleteAction implements CommandProcess {
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		BoardDAO boardDAO = BoardDAO.getInstance();
		int seq = Integer.parseInt(request.getParameter("seq"));
		int pg = Integer.parseInt(request.getParameter("pg"));
		
		if(boardDAO.delete(seq))
			request.setAttribute("display", "/board/boardOk.jsp?pg=" + pg);
		else
			request.setAttribute("display", "/board/fail.jsp");
		
		return "/main/index.jsp";
	}
}