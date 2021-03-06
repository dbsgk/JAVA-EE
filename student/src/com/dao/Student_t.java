package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Student_t {
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String username = "c##java";
	private String password = "bit";
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public Student_t() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}	
	}
	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url,username,password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	public void menu() {
		System.out.println("****************");
		System.out.println("     관리");
		System.out.println("****************");
		System.out.println("   1. 입력");
		System.out.println("   2. 검색");
		System.out.println("   3. 삭제");
		System.out.println("   4. 종료");
		System.out.println("****************");
		System.out.print("번호선택: ");
	}
	private void insertArticle() {
		Connection conn = this.getConnection();
		PreparedStatement ps = null;
		String name = null;
		String value = null;
		
		System.out.println("****************");
		System.out.println("1. 학생");
		System.out.println("2. 교수");
		System.out.println("3. 관리자");
		System.out.println("4. 이전메뉴");
		System.out.println("****************");
		System.out.print("번호선택: ");
		
		int code = scan.nextInt();
		if(code ==1) {//학생
			System.out.print("이름입력: ");
			name = scan.next();
			System.out.print("학번입력: ");
			value = scan.next();
		}else if(code ==2) {//교수
			System.out.print("이름입력: ");
			name = scan.next();
			System.out.print("과목입력: ");
			value = scan.next();
		}else if(code ==3) {//관리자
			System.out.print("이름입력: ");
			name = scan.next();
			System.out.print("부서입력: ");
			value = scan.next();
		}else if(code ==4) {//이전메뉴
			return;
		}else return;
		
		try {
			String sql = "insert into student(name,value,code) values(?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, value);
			ps.setInt(3, code);
			
			int su = ps.executeUpdate();//실행
			System.out.println(su+"개가 만들어졌습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps!=null)ps.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}//insert
	private void selectArticle() {
		Connection conn = this.getConnection();
		PreparedStatement ps = null;
		String name = "";
		String sql = "select name,age,height,to_char(logtime,'YYYY.MM.DD') as logtime from dbtest";
		ResultSet rs = null;
		int code = 0;
		while(true) {
			System.out.println("****************");
			System.out.println("1. 이름 검색");// (1개 글자가 포함된 이름은 모두 검색)
			System.out.println("2. 전체 검색");
			System.out.println("3. 이전메뉴");
			System.out.println("****************");
			System.out.print("번호선택: ");
			code = scan.nextInt();
			if(code==1) {
				System.out.print("검색할 이름 입력 : ");
				name = scan.next();
				sql = "select * from student where name like '%"+name+"%'";
				break;
			}else if(code==2) {
				//전체출력
				sql = "select * from student";
				break;
			}else if(code==3) {
				return;
			}else {
				System.out.println("번호를 잘못 입력하셨습니다.");continue;
			}
		}
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();//실행
			while(rs.next()) {
				if(rs.getInt("code")==1) {
					System.out.println("이름 = "+rs.getString("name")+"\t"
							+"학번 = "+rs.getString("value"));
				}else if(rs.getInt("code")==2) {
					System.out.println("이름 = "+rs.getString("name")+"\t"
							+"과목 = "+rs.getString("value"));
				}else if(rs.getInt("code")==3) {
					System.out.println("이름 = "+rs.getString("name")+"\t"
							+"부서 = "+rs.getString("value"));
				}
				
			}//while
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps!=null)ps.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}//select
	private void deleteArticle() {
		Connection conn = this.getConnection();
		PreparedStatement ps = null;
		System.out.print("삭제를 원하는 이름 입력 : ");
		String name = scan.next();
		//삭제를 원하는 이름 입력 : (정확하게 3개의 글자가 모두 입력된 상태)
		try {
			String sql = "delete student where name = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			int su = ps.executeUpdate();//실행
			System.out.println(su+"개가 삭제되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps!=null)ps.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}//delete
	public static void main(String[] args) {
		Student_t st = new Student_t();
		st.menu();
	}
}
