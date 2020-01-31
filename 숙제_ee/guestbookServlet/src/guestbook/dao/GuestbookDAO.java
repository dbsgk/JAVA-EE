package guestbook.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import guestbook.bean.GuestbookDTO;

public class GuestbookDAO {
	private static GuestbookDAO instance;

    private String driver = "oracle.jdbc.driver.OracleDriver";
    private String url = "jdbc:oracle:thin:@localhost:1521:xe";
    private String userName = "c##java";
    private String password = "bit";
    
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;
    
    public static GuestbookDAO getInstance() {
        if(instance==null) {
            synchronized (GuestbookDAO.class) {
                instance = new GuestbookDAO();
            }
        }
        return instance;
    }
    
    public GuestbookDAO() {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    private void getConnection() {
        try {
            conn = DriverManager.getConnection(url, userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	public void write(GuestbookDTO guestbookDTO) {
		// TODO Auto-generated method stub
		
	}

	public List<GuestbookDTO> getList() {
		List<GuestbookDTO> list = new ArrayList<GuestbookDTO>();
		String sql = "SELECT seq, name, email, homepage, subject, content"
				+ ",TO_CHAR(logtime, 'YYYY.MM.DD') AS logtime FROM guestbook order by seq desc";
		getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				GuestbookDTO guestbookDTO = new GuestbookDTO();
                guestbookDTO.setSeq(rs.getInt("seq"));
                guestbookDTO.setName(rs.getString("name"));
                guestbookDTO.setEmail(rs.getString("email"));
                guestbookDTO.setHomepage(rs.getString("homepage"));
                guestbookDTO.setSubject(rs.getString("subject"));
                guestbookDTO.setContent(rs.getString("content"));
                guestbookDTO.setLogtime(rs.getString("logtime"));
				list.add(guestbookDTO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			list = null;//문제 생기면 그냥 list를 null로 만듦
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
}