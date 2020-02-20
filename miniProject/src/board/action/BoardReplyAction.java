package board.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.control.CommandProcess;

import board.bean.BoardDTO;
import board.dao.BoardDAO;

public class BoardReplyAction implements CommandProcess {
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("memEmail");
		
		List<String> fields = new ArrayList<String>();
		String[] str_fields = {"id", "name", "subject", "content", "pseq"};
		Arrays.asList(str_fields).stream().forEach(fields::add);
		
		List<String> values = new ArrayList<String>();
		String[] str_values = {
				(String) session.getAttribute("memId"), 
				(String) session.getAttribute("memName"), 
				(String) request.getParameter("subject"), 
				(String) request.getParameter("content"), 
				(String) request.getParameter("pseq")
				};
		Arrays.asList(str_values).stream().forEach(values::add);
		
		
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		map.put("fields", fields);
		map.put("values", values);
		
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.reply(map, Integer.parseInt(request.getParameter("pseq")));

		request.setAttribute("pg", request.getParameter("pg"));
		request.setAttribute("display", "/board/boardOk.jsp");
		return "/main/index.jsp";
	}
}
