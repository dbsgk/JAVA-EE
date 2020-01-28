package com.person;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/PersonServlet")
public class PersonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//요청
		String name = request.getParameter("name");
		int gender = Integer.parseInt(request.getParameter("gender"));
		String color = request.getParameter("color");
		String[] hobby = request.getParameterValues("hobby");
		String[] subject = request.getParameterValues("subject");
		//<font color="red">This is some text!</font>
		
		//응답
		response.setContentType("text/html;charset=utf-8");//지금부터 쓰는 모든 문자열을 html로 알고, utf-8로 알거야.
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<style>");
		out.println("#aaa {color: "+color+"; font-weight:bold;}");//id가 aaa라는 애(ul의 id가 aaa)를 찾아서 스타일적용시켜라.
		//out.println("ul {color: "+color+"; font-weight:bold;}");
		out.println(".backA:link {color:red; text-decoration: none;} "); //		a태그가 처음 걸렸을 때(처음 한 번만 보임)
		out.println(".backA:visited {color:blue;text-decoration: none;}"); //	a태그를 클릭했을 때(눌렀다가 놓으면 그 때 보임)
		out.println(".backA:hover {color:green;text-decoration: underline;}");//a태그에 마우스를 올렸을 때
		out.println(".backA:active {color:magenta;text-decoration: none;}");//	a태그를 누르고 있는 동안
		out.println("</style>");
		out.println("</head>");
		out.println("<body><ul id= 'aaa'>"); /* <font color='"+color+"'> */
		out.println("<li> 이름 : "+name+"</li>");
		out.println("<li> 성별 : "+ (gender==1?"여자":"남자")+"</li>");
		out.println("<li> 색깔 : "+color+"</li>");
		out.println("<li> 취미 : ");
		for(int i=0;i<hobby.length;i++) {
			out.print(hobby[i]+" ");
		}
		out.println("</li>");
		out.println("<li> 과목 : ");
		for(String val: subject) {
			out.print(val+" ");
		}
		out.println("</li>");
		out.println("</font>");
		out.println("</ul>");
		//out.println("<a href= 'http://www.naver.com'>뒤로</a>"); //a태그 --> 링크
		out.println("<a class='backA' href= 'javascript:history.go(-1)'>뒤로</a>");
		out.println("</body>");
		out.println("</html>");
	}
}
