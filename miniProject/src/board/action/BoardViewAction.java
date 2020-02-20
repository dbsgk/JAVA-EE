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
		
		Cookie[] ar = request.getCookies();
		boolean hit_on = true;
		if(ar!=null){
			for(int i = 0; i < ar.length; i++){
				if(ar[i].getName().equals("hit_on")){
					hit_on = ar[i].getValue().equals("true") ? true : false;
				}
			}
		}
		
		int seq = Integer.parseInt(request.getParameter("seq"));
		int pg = Integer.parseInt(request.getParameter("pg"));
		
		BoardDAO boardDAO = BoardDAO.getInstance();
		BoardDTO boardDTO = boardDAO.selectOne(seq, hit_on);

		Cookie cookie = new Cookie("hit_on", "false");
		cookie.setMaxAge(60*30); // 초 단위
		response.addCookie(cookie); // 클라이언트에게 보내기
		
		request.setAttribute("boardDTO", boardDTO);
		
		request.setAttribute("display", "/board/boardView.jsp?seq=" + seq + "&pg=" + pg);
		return "/main/index.jsp";
	}
}
