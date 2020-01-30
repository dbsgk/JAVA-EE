package guestbook.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import guestbook.bean.GuestbookDTO;
//driver loading
//connection
//prepareStatement
public class GuestbookDAO {
	static GuestbookDAO instance;
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private String username = "hr";
	private String password = "hr";
	private Connection conn;
	private PreparedStatement pstmt = null;
	private ResultSet rs;
	private List<GuestbookDTO> list = new ArrayList<GuestbookDTO>();
	
	public GuestbookDAO() {
		try {
			Class.forName(driver);
			System.out.println("driver loading success");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		getConnection();
	}//const
	
	public Connection getConnection() {
		try {
			conn = DriverManager.getConnection(url,username,password);
			System.out.println("connection success");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}//getConnection
	
	public static GuestbookDAO getInstance() {
		if(instance==null) {
			synchronized (GuestbookDAO.class) {
				instance = new GuestbookDAO();
			}
		}
		return instance;
	}//getInstance
	
	public boolean isWrite(GuestbookDTO guestbookDTO) {
		boolean checkWrite = false;
		String sql = "insert into guestbook values(seq_guestbook.nextval,?,?,?,?,?,sysdate)";
		int index=1;
		this.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(index++, guestbookDTO.getName());
			pstmt.setString(index++, guestbookDTO.getEmail());
			pstmt.setString(index++, guestbookDTO.getHomepage());
			pstmt.setString(index++, guestbookDTO.getSubject());
			pstmt.setString(index++, guestbookDTO.getContent());
			
			rs = pstmt.executeQuery();
			if(rs.next())
				checkWrite = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return checkWrite;
	}//isWrite
	public List<GuestbookDTO> getList(){
		List<GuestbookDTO> list2 = new ArrayList<GuestbookDTO>();
		String sql = "select * from guestbook order by seq desc";
		getConnection();//실행할 때마다 종료되니까 꼭 넣어라.
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				GuestbookDTO dto = new GuestbookDTO();
				dto.setSeq(rs.getInt("seq"));
				dto.setName(rs.getString("name"));
				dto.setEmail(rs.getString("email"));
				dto.setHomepage(rs.getString("homepage"));
				dto.setSubject(rs.getString("subject"));
				dto.setContent(rs.getString("content"));
				dto.setLogtime(rs.getDate("logtime"));

				list2.add(dto);
			}//while
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
		
		return list2;
		
	}

	public int getTotalA() {
		int totalA = 0;
		getConnection();
		String sql = "SELECT count(*) FROM guestbook";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			rs.next();
			totalA = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(rs!=null)rs.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return totalA;
	}
}//class
