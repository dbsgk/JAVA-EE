package guestbook.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import guestbook.bean.GuestbookDTO;
import guestbook.dao.GuestbookDAO;

@WebServlet("/GuestbookListServlet")
public class GuestbookListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//데이터(가져올거 없음)
		//DB
		GuestbookDAO guestbookDAO = GuestbookDAO.getInstance();
		List<GuestbookDTO> list = guestbookDAO.getList();
		
		//응답
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		if(list!=null) {
			for(GuestbookDTO guestbookDTO : list) {
				out.println("<table border='1'>");
				out.println("<tr>");
				out.println("<td>작성자</td>");
				out.println("<td>"+guestbookDTO.getName()+"</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td>작성일</td>");
				out.println("<td>"+guestbookDTO.getLogtime()+"</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td>이메일</td>");
				out.println("<td colspan='3'>"+guestbookDTO.getEmail()+"</td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td>홈페이지</td>");
				out.println("<td colspan='3'>"+guestbookDTO.getHomepage()+"</td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td>제목</td>");
				out.println("<td colspan='3'>"+guestbookDTO.getSubject()+"</td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td colspan='4'>"+guestbookDTO.getContent()+"</td>");
				out.println("</tr>");
				out.println("<hr align='left' color='red' width='500'>");
				out.println("</table>");

			}//for
		}
		out.println("</body>");
		out.println("</html>");
	}

}
