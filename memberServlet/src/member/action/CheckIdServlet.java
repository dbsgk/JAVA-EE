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
		out.println("<form method='get' action='http://localhost:8080/memberServlet/CheckIdServlet'>");
		if(exist) {
			out.println(id+"는(은) 사용 불가능<br><br>");
			out.println("<br>");
			out.println("아이디");
			out.println("<input type='text' name='id'>");
			out.println("<input type='submit' value='중복체크'>");
		}else if(!exist) {
			out.println(id+"는(은) 사용 가능 <br>");
			out.println("<input type='button' value='사용하기' onClick='checkIdClose(\""+id+"\")'>");
		}
		
		out.println("</form>");
		out.println("<script type=\"text/javascript\" src='/memberServlet/js/member.js'>");
		out.println("</script>");
		out.println("</body>");
		out.println("</html>");
	
	}

}
