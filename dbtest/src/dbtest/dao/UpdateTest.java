package dbtest.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

//수정할 이름 입력: 홍
//	홍이 들어간 이름의 나이를 1살 증가
public class UpdateTest {
	public UpdateTest() {
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
	public void updateArticle() {
		Connection conn = this.getConnection();
		PreparedStatement ps = null;
		Scanner scan = new Scanner(System.in);
		System.out.print("수정할 이름 입력: ");
		String name = scan.next();
		//String sql = "update dbtest set age=age+1 where name like '%"+name+"%'";
		String sql = "update dbtest set age=age+1 where name like ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%"+name+"%"); //이건 ' '(따옴표)를 물고 들어감.
			
			int su = ps.executeUpdate();
			System.out.println(su +"개가 수정 되었습니다.");
			
			if(ps!=null)ps.close();
			if(conn!=null)conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	public static void main(String[] args) {
		new UpdateTest().updateArticle();
	}
}
