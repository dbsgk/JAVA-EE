package member.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberDAO {
	private static MemberDAO instance=null;
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private String username = "c##java";
	private String password = "bit";
	private Connection conn;
	private PreparedStatement pstmt = null;
	private ResultSet rs;
	
	public MemberDAO() {
		try {
			Class.forName(driver);
			System.out.println("driver loading success");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		getConnection();
	}
	public Connection getConnection() {
		try {
			conn = DriverManager.getConnection(url,username,password);
			System.out.println("connection success");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	public static MemberDAO getInstance() {
		
		if(instance==null) {
			synchronized (MemberDAO.class) {//MemberDAO.class 이런 파일은 딱 한 개만 통과시켜라.
				instance = new MemberDAO();	
			}
		}
		return instance;
	}
	public boolean isExistId(String id) {
		boolean exist = false;
		String sql = "select * from member where id=?";
		this.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			if(rs.next())
				exist = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return exist;
	}
	public boolean isLoginCheck(String id, String pwd) {
		boolean loginCheck = false;
		String sql = "select id from member where id=? and pwd=?";
		this.getConnection();
		try {
			System.out.println(id);
			System.out.println(pwd);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				loginCheck = true;
				System.out.println("로그인 성공");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		System.out.println(loginCheck);
		return loginCheck;
	}
	public String getName(String id) {
		String sql = "select name from member where id=?";
		String name = null;
		this.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			if(rs.next())
				name = rs.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return name;
	}
}
