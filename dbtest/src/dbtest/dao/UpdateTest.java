package dbtest.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

//������ �̸� �Է�: ȫ
//	ȫ�� �� �̸��� ���̸� 1�� ����
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
		System.out.print("������ �̸� �Է�: ");
		String name = scan.next();
		//String sql = "update dbtest set age=age+1 where name like '%"+name+"%'";
		String sql = "update dbtest set age=age+1 where name like ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%"+name+"%"); //�̰� ' '(����ǥ)�� ���� ��.
			
			int su = ps.executeUpdate();
			System.out.println(su +"���� ���� �Ǿ����ϴ�.");
			
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