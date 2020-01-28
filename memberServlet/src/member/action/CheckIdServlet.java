package member.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.dao.MemberDAO;

@WebServlet("/CheckIdServlet")
public class CheckIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//데이터
		String id = request.getParameter("id");
		
		//DB
		MemberDAO memberDAO = MemberDAO.getInstance();
		boolean exist = memberDAO.isExistId(id);
		
		//응답
		response.setContentType("text/html;charset=UTF-8");//모든 컨텐츠타입을 html, utf-8로 알겠다.
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		System.out.println("id="+id);
		if(exist) 
			out.println(id+"는(은) 사용 불가능");
		else if(!exist) 
				out.println(id+"는(은) 사용 가능");
		out.println("</body>");
		out.println("</html>");
	
	}

}
