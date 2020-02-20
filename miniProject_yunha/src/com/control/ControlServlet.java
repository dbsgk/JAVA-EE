package com.control;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Map<String, Object> map = new HashMap<String, Object>();
	
	//처음에 딱 한 번 web.xml가서 읽어와라
	public void init(ServletConfig config) {
		String propertyConfig = config.getInitParameter("propertyConfig");
		System.out.println("propertyConfig = "+propertyConfig+"\n");
		
		FileInputStream fin = null;
		Properties properties = new Properties();
		
		try {
			fin = new FileInputStream(propertyConfig);
			properties.load(fin);
			System.out.println("properties = "+properties);
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				fin.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println();
		
		Iterator it = properties.keySet().iterator();
		while(it.hasNext()) {
			String key = (String)it.next();
			System.out.println("key = "+key);
			
			String className = properties.getProperty(key);
			System.out.println("className = "+className);
			
			try {
				//Class classType = Class.forName(className);
				//Object ob = classType.newInstance(); - @Deprecated

				//Class<?> classType = Class.forName(className);
				//Object ob = classType.getDeclaredConstructor().newInstance();

				Class<?> classType = Class.forName(className);
				Object ob = classType.newInstance();
				
				System.out.println("ob = "+ob);
				
				map.put(key, ob);
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} 
			//catch (InvocationTargetException e) {
			//	e.printStackTrace();
			//} catch (NoSuchMethodException e) {
			//	e.printStackTrace();
			//} catch (SecurityException e) {
			//	e.printStackTrace();
			//}
			
			System.out.println();
		}//while
		
	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//주소로 데이터, 한글 알아서함
		execute(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//내부적, 한글처리해줘야
		execute(request, response);
	}

	protected void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println();
		
		//get방식인지 ,post방식인지 물어보는 if문. POST인지 GET인지 대문자로만 나와서 대문자로 물어야 함.
		//한글처리
		if(request.getMethod().equals("POST")){
			request.setCharacterEncoding("UTF-8");
		}
		
		//http://localhost:8080/miniproject/main/index.do ��û
		String category = request.getServletPath();
		System.out.println("category = "+category);
		
		CommandProcess commandProcess = (CommandProcess)map.get(category);
		System.out.println("commandProcess = "+commandProcess);
		
		String view = null;
		try {
			view = commandProcess.requestPro(request, response);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		//forward	
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);//������
		dispatcher.forward(request, response);//����� �ѱ��
	}

}











