package board.action;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.CommandProcess;

import board.bean.BoardDTO;
import board.bean.BoardPaging;
import board.dao.BoardDAO;

public class BoardListAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		int pg = Integer.parseInt(request.getParameter("pg"));
		int articlesPerPage = 5;
		int endNum = pg * articlesPerPage;
		int startNum = endNum - articlesPerPage + 1;
		
		BoardDAO boardDAO = BoardDAO.getInstance();
		List<BoardDTO> list = boardDAO.getList(startNum, endNum);
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");

		// 쿠키 설정 - 목록으로 돌아왔으므로 hit_on = true 
		Cookie cookie = new Cookie("hit_on", "true");
		cookie.setMaxAge(60*30); // 초 단위
		response.addCookie(cookie); // 클라이언트에게 보내기

		BoardPaging boardPaging = new BoardPaging();
		int totalA = boardDAO.getBoardTotalA();

		boardPaging.setCurrentPage(pg);
		boardPaging.setPageBlock(3);
		boardPaging.setPageSize(articlesPerPage);
		boardPaging.setTotalA(totalA);
		boardPaging.makePagingHTML();
		
		request.setAttribute("diplay", "/board/boardList.jsp");
		return "/main/index.jsp";
	}

}
