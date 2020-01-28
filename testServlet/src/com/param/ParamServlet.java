package com.param;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/ParamServlet")
public class ParamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//HttpServletRequest 얘가 요청 받아줌
	//HttpServletResponse 얘가 응답함
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet");
		
		//요청
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		
		//응답
		response.setContentType("text/html;charset=utf-8");//지금부터 쓰는 모든 문자열을 html로 알고, utf-8로 알거야.
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println(name+"님의 나이는 "+age+"살 이므로");
		if(age>=19) {
			out.println("<b>성인</b>입니다.");
		}else out.println("<b>청소년</b>입니다.");
		out.println("</body>");
		out.println("</html>");
	}

}
