package dbtest.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SelectTest {
	public SelectTest() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("driver loading  success");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","c##java","bit");
			System.out.println("connection success");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	public void selectArticle() {
		Connection conn = this.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select name,age,height,to_char(logtime,'YYYY.MM.DD') as logtime from dbtest";// =="select * from dbtest";
		try {
			pstmt = conn.prepareStatement(sql); //생성
			
			rs = pstmt.executeQuery();//실행
			while(rs.next()) {
				System.out.println(rs.getString("name")+"\t"
						+rs.getInt("age")+"\t"
						+rs.getDouble("height")+"\t"
						+rs.getString("logtime"));
				
			}//while
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
	}
	public static void main(String[] args) {
		new SelectTest().selectArticle();
	}
}
