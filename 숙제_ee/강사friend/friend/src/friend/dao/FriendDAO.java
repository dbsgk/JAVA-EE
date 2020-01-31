package friend.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import friend.bean.FriendDTO;

public class FriendDAO {
	private static FriendDAO instance;
	
	private String driver = "oracle.jdbc.driver.OracleDriver"; 
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String username = "c##java";
	private String password = "bit";
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public static FriendDAO getInstance() {
		if(instance==null) {
			synchronized (FriendDAO.class) {
				instance = new FriendDAO();
			}
		}
		return instance;
	}
	
	public FriendDAO() {
		try {
			Class.forName(driver);//오라클 DB연동
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void getConnection() {		
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void friendInsert(FriendDTO friendDTO) {
		getConnection();
		String sql = "insert into friend values(?,?,?,?,?,?,?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, friendDTO.getSeq());
			pstmt.setString(2, friendDTO.getName());
			pstmt.setString(3, friendDTO.getTel1());
			pstmt.setString(4, friendDTO.getTel2());
			pstmt.setString(5, friendDTO.getTel3());
			pstmt.setInt(6, friendDTO.getGender());
			pstmt.setInt(7, friendDTO.getRead());
			pstmt.setInt(8, friendDTO.getMovie());
			pstmt.setInt(9, friendDTO.getMusic());
			pstmt.setInt(10, friendDTO.getGame());
			pstmt.setInt(11, friendDTO.getShopping());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	public int getSeq() {
		int seq=0;
		getConnection();
		String sql = "select seq_friend.nextval from dual";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			rs.next();
			seq = rs.getInt(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return seq;
	}

	public List<FriendDTO> getFriendList() {
		List<FriendDTO> list2 = new ArrayList<FriendDTO>();
		getConnection();
		String sql = "select * from friend";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				FriendDTO friendDTO = new FriendDTO();
				friendDTO.setSeq(rs.getInt("seq"));
				friendDTO.setName(rs.getString("name"));
				friendDTO.setTel1(rs.getString("tel1"));
				friendDTO.setTel2(rs.getString("tel2"));
				friendDTO.setTel3(rs.getString("tel3"));
				friendDTO.setGender(rs.getInt("gender"));
				friendDTO.setRead(rs.getInt("read"));
				friendDTO.setMovie(rs.getInt("movie"));
				friendDTO.setMusic(rs.getInt("music"));
				friendDTO.setGame(rs.getInt("game"));
				friendDTO.setShopping(rs.getInt("shopping"));				
				
				list2.add(friendDTO);
			}//while
			
		} catch (SQLException e) {
			e.printStackTrace();
			list2 = null;
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list2;
	}

	public void friendModify(FriendDTO friendDTO) {
		getConnection();
		String sql = "update friend set name=?"
								   + ", tel1=?"
								   + ", tel2=?"
								   + ", tel3=?"
								   + ", gender=?"
								   + ", read=?"
								   + ", movie=?"
								   + ", music=?"
								   + ", game=?"
								   + ", shopping=? where seq=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, friendDTO.getName());
			pstmt.setString(2, friendDTO.getTel1());
			pstmt.setString(3, friendDTO.getTel2());
			pstmt.setString(4, friendDTO.getTel3());
			pstmt.setInt(5, friendDTO.getGender());
			pstmt.setInt(6, friendDTO.getRead());
			pstmt.setInt(7, friendDTO.getMovie());
			pstmt.setInt(8, friendDTO.getMusic());
			pstmt.setInt(9, friendDTO.getGame());
			pstmt.setInt(10, friendDTO.getShopping());
			pstmt.setInt(11, friendDTO.getSeq());
			
			pstmt.executeUpdate();//실행
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void friendDelete(int seq) {
		getConnection();
		String sql = "delete friend where seq=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seq);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
	}

}




























