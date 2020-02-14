<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.io.File"%>
<%@page import="java.io.BufferedInputStream"%>
<%@page import="java.io.BufferedOutputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.net.URLEncoder"%>
<%
//데이터
String fileName = request.getParameter("fileName");
String realFolder = request.getServletContext().getRealPath("/storage");

//다운로드 받을 파일 생성
File file = new File(realFolder, fileName);
fileName = URLEncoder.encode(fileName, "UTF-8");

response.setHeader("Content-Disposition", "attachment;fileName="+fileName);
response.setHeader("Content-Length", file.length()+"");

/* getOutputStream() has alread been called for this response
JSP에서는 SERVLET으로 변환될 때 내부적으로 out객체가 자동으로 생성하기 때문에 따라서
out객체를 만들면 충돌이 일어나서 저런 메시지가 뜨는 것이다.
그래서 먼저 out을 초기화하고 생성하면 된다. 
*/

out.clear();
out = pageContext.pushBody();


BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());

int size = (int)file.length(); //파일의 크기 알아내라.
byte[] b = new byte[size];//파일크기만큼 배열잡고
bis.read(b,0,size);//배열에 전부 담고, 그 배열 전부 읽어서 b에 저장해라.
bos.write(b);//b를 내보내라.

bis.close();
bos.close();
%>