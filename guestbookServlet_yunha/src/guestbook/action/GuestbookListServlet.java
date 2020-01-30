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
		//데이터
		
		//DB
		GuestbookDAO guestbookDAO = GuestbookDAO.getInstance();
		List<GuestbookDTO> list = guestbookDAO.getList(); //DTO받아옴
		//응답
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>글목록</title></head>");
		out.println("<body>");
		for(GuestbookDTO d : list) {
		out.println("<form action='/guestbookServlet_yunha/GuestbookListServlet>");
		out.println("	<table width=\"800\" border=\"3\" cellspacing=\"0\" cellpadding=\"5\">");
		out.println("		<tr>");
		out.println("			<td width=\"80\" align=\"center\">작성자</td>");
		out.println("			<td>"+d.getName()+"</td>");
		out.println("			<td width=\"80\" align=\"center\">작성일</td>");
		out.println("			<td>"+d.getLogtime()+"</td>");
		out.println("		</tr>");
		out.println("		<tr>");
		out.println("			<td align=\"center\">이메일</td>");
		out.println("			<td colspan=\"3\">"+d.getEmail()+"</td>");
		out.println("		</tr>");
		out.println("		<tr>");
		out.println("			<td align=\"center\">홈페이지</td>");
		out.println("			<td colspan=\"3\">"+d.getHomepage()+"</td>");
		out.println("		</tr>");
		out.println("		<tr>");
		out.println("			<td align=\"center\">제목</td>");
		out.println("			<td colspan=\"3\">"+d.getSubject()+"</td>");
		out.println("		</tr>");
		out.println("		<tr>");
		out.println("			<td colspan=\"4\">");
		out.println("			<textarea name=\"content\" cols=\"120\" rows=\"10\">"+d.getContent()+"</textarea>");
		out.println("			</td>");
		out.println("		</tr>");
		out.println("	</table>");
		out.println("	</form>");
		}
		out.println("</body>");
		out.println("<html>");
		
	}

}
