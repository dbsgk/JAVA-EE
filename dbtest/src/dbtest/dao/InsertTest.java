package dbtest.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertTest {
	public InsertTest() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("driver loading success");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public Connection getConnection() {//접속하는 메소드
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","c##java","bit");
//												URL: 드라이버명:@호스트(192.168.0.27):포트번호:데이터베이스명,ID, PASSWORD
			System.out.println("connection success");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	public void insertArticle() {
		Scanner scan = new Scanner(System.in);
		System.out.print("이름 입력: ");
		String name = scan.next();
		System.out.print("나이 입력: ");
		int age = scan.nextInt();
		System.out.print("키 입력: ");
		double height = scan.nextDouble();
		
		Connection conn = this.getConnection();
		PreparedStatement pstmt = null;
		String sql = "insert into dbtest(name,age,height,logtime) values(?,?,?,sysdate)";
		try {
			pstmt = conn.prepareStatement(sql); //생성
			pstmt.setString(1, name);
			pstmt.setInt(2, age);
			pstmt.setDouble(3, height);
			
			int su = pstmt.executeUpdate();//실행 - 개수가 리턴
			System.out.println(su+"개가 만들어졌습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {//연결 끊을 때는 역순으로
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
//		insert into dbtest(name,age,height,logtime) values('홍길동',25,185.567,sysdate);
	}
	public static void main(String[] args) {
		InsertTest it = new InsertTest();
		it.insertArticle();
	}
}
