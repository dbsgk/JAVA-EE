package board.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import board.bean.BoardDTO;
import member.bean.MemberDTO;

public class BoardDAO {
	private static BoardDAO instance;
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String username = "c##java";
	private String password = "bit";

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public BoardDAO() {
		try {
			Class.forName(driver);
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
	public void boardWrite(Map<String,String> map) {
		String sql ="INSERT INTO board(seq,id,name,email,subject,content,ref)"
					+" values(seq_board.nextval, ?, ?, ?, ?, ?, seq_board.currval)";
		
		getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, map.get("id"));
			pstmt.setString(2, map.get("name"));
			pstmt.setString(3, map.get("email"));
			pstmt.setString(4, map.get("subject"));
			pstmt.setString(5, map.get("content"));
			
			pstmt.executeUpdate();//실행
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs!=null) rs.close();
				if (pstmt!=null) pstmt.close();
				if (conn!=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static BoardDAO getInstance() {
        if(instance==null) {
            synchronized (BoardDAO.class) {
                instance = new BoardDAO();
            }
        }
        return instance;
    }
	public int getTotalA() {
		int totalA = 0;
		String sql = "select count(*) cn from board";
		getConnection();
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				//totalA = rs.getInt("cn");
				totalA = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
                if(rs!=null) rs.close();
                if(pstmt!=null) pstmt.close();
                if(conn!=null) conn.close();
            } catch(SQLException e) {
                e.printStackTrace();
            }
		}
		
		return totalA;
	}
	public List<BoardDTO> getList(int startNum, int endNum) {
		List<BoardDTO> list = new ArrayList<BoardDTO>();
//		String sql = "select * from" + 
//				"(select rownum rn, tt.* from" + 
//				"(select seq, name, email, homepage, subject, content"
//				+ ", to_char(logtime, 'YYYY.MM.DD')as logtime "
//				+ "from guestbook order by seq desc" + 
//				")tt )where rn>=? and rn<=?";
		String sql = "select * from (select rownum rn, tt.* from(select * from board order by ref desc)tt) where rn>=? and rn<=?";
		getConnection();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startNum);
			pstmt.setInt(2, endNum);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardDTO boardDTO = new BoardDTO();
				boardDTO.setSeq(rs.getInt("seq"));
				boardDTO.setId(rs.getString("id"));
				boardDTO.setName(rs.getString("name"));
				boardDTO.setEmail(rs.getString("email"));
				boardDTO.setSubject(rs.getString("subject"));
				boardDTO.setContent(rs.getString("content"));
				boardDTO.setRef(rs.getInt("ref"));
				boardDTO.setLev(rs.getInt("lev"));
				boardDTO.setStep(rs.getInt("step"));
				boardDTO.setPseq(rs.getInt("pseq"));
				boardDTO.setReply(rs.getInt("reply"));
				boardDTO.setHit(rs.getInt("hit"));
				boardDTO.setLogtime(rs.getString("logtime"));
	            
				list.add(boardDTO);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			list=null;
		}finally {
			try {
	            if(rs!=null) rs.close();
	            if(pstmt!=null) pstmt.close();
	            if(conn!=null) conn.close();
	        } catch(SQLException e) {
	            e.printStackTrace();
	        }
		}
		
		return list;
	}
	public BoardDTO getBoard(int seq){
		BoardDTO boardDTO = null;
		String sql = "SELECT * FROM board WHERE seq=?";
		
		getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seq);
			
			rs = pstmt.executeQuery();//실행
			
			if(rs.next()) {
				boardDTO = new BoardDTO();
				boardDTO.setSeq(rs.getInt("seq"));
				boardDTO.setId(rs.getString("id"));
				boardDTO.setName(rs.getString("name"));
				boardDTO.setEmail(rs.getString("email"));
				boardDTO.setSubject(rs.getString("subject"));
				boardDTO.setContent(rs.getString("content"));
				boardDTO.setRef(rs.getInt("ref"));
				boardDTO.setLev(rs.getInt("lev"));
				boardDTO.setStep(rs.getInt("step"));
				boardDTO.setPseq(rs.getInt("pseq"));
				boardDTO.setReply(rs.getInt("reply"));
				boardDTO.setHit(rs.getInt("hit"));
				boardDTO.setLogtime(rs.getString("logtime"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
            try {
                if(rs != null)rs.close();
                if(pstmt != null) pstmt.close();
                if(conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
		}
		
		return boardDTO;
	}
}