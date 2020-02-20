package board.action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.CommandProcess;

import board.bean.BoardDTO;
import board.dao.BoardDAO;

public class BoardViewAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//데이터
		int seq = Integer.parseInt(request.getParameter("seq"));
		int pg = Integer.parseInt(request.getParameter("pg"));
		
		//DB
		BoardDAO boardDAO = BoardDAO.getInstance();
		BoardDTO boardDTO = boardDAO.getBoard(seq);
		
		request.setAttribute("pg", pg);
		request.setAttribute("boardDTO", boardDTO);
		request.setAttribute("display", "/board/boardView.jsp");
		
		return "/main/index.jsp";
	}

}

/*
 * //조회수 - 새로고침 방지 Cookie[] ar = request.getCookies();
 * 
 * String id = (String) session.getAttribute("memId");
 * 
 * boolean hit_on = true; for(int i = 0; i < ar.length; i++){
 * if(ar[i].getName().equals("hit_on")){ hit_on =
 * ar[i].getValue().equals("true") ? true : false; } }
 * 
 * SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:SS");
 * 
 * Cookie cookie = new Cookie("hit_on", "false"); cookie.setMaxAge(60*30); // 초
 * 단위 response.addCookie(cookie); // 클라이언트에게 보내기
 */