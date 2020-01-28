package com.hello;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/HelloServlet")//web.xml
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public void init(){ //톰캣이 servlet키면 이거 실행
		System.out.println("init()");
	}

	public void destroy() { //톰캣끄면 이거 실행
		System.out.println("destroy()");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException { //init 다음에 이거 실행
		System.out.println("doGet()");
		
//		response.setContentType("text/javascript");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();//
		out.println("<html>");
		out.println("<body>");
		out.println("Hello Servlet !!<br>");
		out.println("안녕하세요 서블릿!!<br>");
		out.println("</body>");
		out.println("</html>");
	}

}
