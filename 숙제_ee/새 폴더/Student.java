package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Student {
	Connection conn=null;
	Scanner sc = new Scanner(System.in);
	
	public Student() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");//오라클 DB연동
			System.out.println("드라이버 로딩 성공");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public Connection getConnection() {		
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","c##java","bit");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	public void menu() {
		int num=0;
		while(true){
			System.out.println("******************");
			System.out.println("\t관리");
			System.out.println("******************");
			System.out.println("1. 입력");
			System.out.println("2. 검색");
			System.out.println("3. 삭제");
			System.out.println("4. 종료");
			System.out.println("******************");
			System.out.println("번호 : ");
			
			num = sc.nextInt();
			if(num==4) {
				System.out.println("종료되었습니다.");
				break;
			}
			else if(num==1) insertArticle();
			else if(num==2) selectArticle();
			else if(num==3) deleteArticle();
			else {
				System.out.println("1~4중에 선택하세요");
			}
		}
	}

	private void insertArticle() {
		int num=0;
		while(true) {
			System.out.println("******************");
			System.out.println("1. 학생");
			System.out.println("2. 교수");
			System.out.println("3. 관리자");
			System.out.println("4. 이전메뉴");
			System.out.println("******************");
			System.out.println("번호 : ");
			num = sc.nextInt();
			
			String name = null;
			String value=null;
			int code=0;
			
			if(num==4) break;
			
			if(num==1) {
				System.out.println("이름 입력 : ");
				name = sc.next();
				System.out.println("학번 입력 : ");
				value = sc.next();
				code = 1;
				}
			if(num==2) {
				System.out.println("이름 입력 : ");
				name = sc.next();
				System.out.println("과목 입력: ");
				value = sc.next();
				code = 2;
			}
			if(num==3) {
				System.out.println("이름 입력 : ");
				name = sc.next();
				System.out.println("부서 입력 : ");
				value = sc.next();
				code = 3;
			}			
			String sql = "insert into student(name,value,code) values(?,?,?)";
			
			conn = this.getConnection();
			PreparedStatement pstmt=null;
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, name);
				pstmt.setString(2, value);
				pstmt.setInt(3, code);
				
				pstmt.executeUpdate();
				System.out.println("입력 되었습니다.");
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					if(pstmt!=null) pstmt.close();
					if(conn!=null) conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}
	private void selectArticle() {
		int num=0;
		while(true) {			
			System.out.println("******************");
			System.out.println("1. 이름 검색 ");
			System.out.println("2. 전체 검색");
			System.out.println("3. 이전 메뉴");
			System.out.println("******************");
			System.out.println("번호 : ");
			num = sc.nextInt();
			String name=null;
			String sql=null;
			if(num==3)break;
			
			if(num==1) {
				System.out.println("검색할 이름 입력 : ");
				name = sc.next();
				sql = "select * from student where name like '%"+name+"%'";
			}
			if(num==2) {
				sql = "select * from student order by name asc";
			}
		
			conn = this.getConnection();
			PreparedStatement pstmt=null;
			ResultSet rs = null;

			try {
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					System.out.print("이름="+rs.getString("name")+"\t");
					String value1=null;
					if(rs.getInt("code")==1) value1 = "학번 = ";
					else if(rs.getInt("code")==2) value1 = "과목 = ";
					else if(rs.getInt("code")==3) value1 = "부서 = ";
					
					System.out.println(value1+rs.getString("value"));
				}//while
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					if(rs!=null) rs.close();
					if(pstmt!=null) pstmt.close();
					if(conn!=null) conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	private void deleteArticle() {
		int num=0;
		while(true) {
			System.out.println("******************");
			System.out.println("1. 삭제 ");
			System.out.println("2. 이전 메뉴");
			System.out.println("******************");
			System.out.println("번호 : ");
			num = sc.nextInt();
			String name=null;
			String sql=null;
			if(num==2)break;
			if(num==1) {
				System.out.println("삭제를 원하는 이름 입력 : ");
				name = sc.next();
			}
			
			conn = this.getConnection();
			PreparedStatement pstmt=null;
			sql = "delete student where name like '"+name+"'";
			
			try {
				pstmt = conn.prepareStatement(sql);
				int su = pstmt.executeUpdate();
				
				System.out.println(su+"개가 삭제 되었습니다.");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					if(pstmt!=null) pstmt.close();
					if(conn!=null) conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public static void main(String[] args) {
		Student st = new Student();
		st.menu();


	}

}
