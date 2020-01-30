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
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//데이터
		int pg = Integer.parseInt(request.getParameter("pg"));//1
		
		//DB
		GuestbookDAO guestbookDAO = GuestbookDAO.getInstance();

		//페이징 처리 - 1페이지당 3개씩
		int endNum = pg*3;//  !!!paging!!!
		int startNum = endNum-2;//!!!paging!!!
		int totalA = guestbookDAO.getTotalA();
		int totalP = (totalA+2)/3;//(총 글의 수 +2) /3
		
		List<GuestbookDTO> list = guestbookDAO.getList(startNum, endNum);
		
		//응답
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<style>");
		out.println("#currentPaging {color:red; text-decoration: underline;}");
		out.println("#paging {color:black; text-decoration: none;}");
		out.println("</style>");
		out.println("</head>");
		out.println("<body>");
		if(list!=null) {
			for(int i=1; i<=totalP;i++) {
				if(pg==i)
					out.println("[<a id='currentPaging' href='/guestbookServlet/GuestbookListServlet?pg="+i+"'>"+i+"</a>]");
				else
					out.println("[<a id='paging' href='/guestbookServlet/GuestbookListServlet?pg="+i+"'>"+i+"</a>]");
			}
			for(GuestbookDTO guestbookDTO : list) {
				out.println("<table border='1'>");
				out.println("<tr>");
				out.println("<td width='100'>작성자</td>");
				out.println("<td width='150'>"+guestbookDTO.getName()+"</td>");
				out.println("<td width='100'>작성일</td>");
				out.println("<td width='150'>"+guestbookDTO.getLogtime()+"</td>");
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
				out.println("<td colspan='4'><pre>"+guestbookDTO.getContent()+"</pre></td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("<hr color='red' width='500' align='left'>");
				//out.println("<hr color='red' width='500' align='left'>");
			}//for
		}
		out.println("</body>");
		out.println("</html>");
	}

}













