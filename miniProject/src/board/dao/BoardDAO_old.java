package board.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;

import board.bean.BoardDTO;

public class BoardDAO_old {

	private static BoardDAO instance;
	private DataSource ds;
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public BoardDAO_old() {
		Context ctx;
		try {
			ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/oracle"); // Tomcat의 경우 'java:comp/env/' 이것을 꼭 써줘야 한다.
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public static BoardDAO getInstance() {
		synchronized (BoardDAO.class) {
			if(instance!=null) return instance;
			return new BoardDAO();
		}
	}

	public void disconnect() {
		try {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int getBoardTotalA() {
		int result = 0;
		try {
			conn = ds.getConnection();
			String sql = "SELECT COUNT(*) FROM board";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			result = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return result;
	}
	
	public boolean insert(BoardDTO boardDTO) {
		boolean result = false;
		try {
			conn = ds.getConnection();
			String sql = "INSERT INTO board(id, name, email, subject, content, seq, ref) VALUES(?,?,?,?,?,seq_board.nextval,seq_board.currval)";
			pstmt = conn.prepareStatement(sql);
			int index = 1;
			pstmt.setString(index++, boardDTO.getId());
			pstmt.setString(index++, boardDTO.getName());
			pstmt.setString(index++, boardDTO.getEmail());
			pstmt.setString(index++, boardDTO.getSubject());
			pstmt.setString(index++, boardDTO.getContent());
			
			if(pstmt.executeUpdate() == 1) result = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
		return result;
	}
	
	public boolean update(BoardDTO boardDTO) {
		boolean result = false;
		
		try {
			conn = ds.getConnection();
			String sql = "UPDATE board "
					+ "SET subject=?, content=?, logtime=sysdate"
					+ " WHERE seq=?";
			pstmt = conn.prepareStatement(sql);
			int index = 1;
			pstmt.setString(index++, boardDTO.getSubject());
			pstmt.setString(index++, boardDTO.getContent());
			pstmt.setInt(index++, boardDTO.getSeq());
			if(pstmt.executeUpdate() > 0) result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return result;
	}
	
	public boolean delete(int seq) {
		boolean result = false;
		try {
			conn = ds.getConnection();
			String sql = "DELETE board WHERE seq=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seq);
			if(pstmt.executeUpdate() > 0) {
				System.out.println("1");
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return result;
	}
	
	public boolean boardWrite(Map<String, String> map) {
		boolean result = false;
		try {
			conn = ds.getConnection();
			Iterator<String> it = map.keySet().iterator();
			while(it.hasNext()) {
				;
			}
			String sql = "INSERT INTO board(id, name, email, subject, content, seq, ref) VALUES(?,?,?,?,?,seq_board.nextval,seq_board.currval)";
			pstmt = conn.prepareStatement(sql);
			int index = 1;
			it = map.keySet().iterator();
			while(it.hasNext()) {
				pstmt.setString(index++, map.get(it.next()));
			}
			if(pstmt.executeUpdate() == 1) result = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
		return result;
	}
	
	public List<BoardDTO> getList(int StartNum, int EndNum) {
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		try {
			conn = ds.getConnection();
			String sql = "SELECT * FROM (SELECT rownum rn, tt.* from (SELECT * FROM board ORDER BY ref DESC) tt)"
					+ " WHERE rn >= " + StartNum + " AND rn <= " + EndNum;
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int seq = rs.getInt("seq");
				String name = rs.getString("name");
				String id = rs.getString("id");
				String email = rs.getString("email");
				String subject = rs.getString("subject");
				String content = rs.getString("content");
				int ref = rs.getInt("ref");
				int lev = rs.getInt("lev");
				int step = rs.getInt("step");
				int pseq = rs.getInt("pseq");
				int reply = rs.getInt("reply");
				int hit = rs.getInt("hit");
				Date date = rs.getDate("logtime");
			
				BoardDTO boardDTO = new BoardDTO(seq, id, name, email, subject, content, ref, lev, step, pseq, reply, hit, date);
				list.add(boardDTO);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
		return list;
	}
	
	public BoardDTO selectOne(int seq, boolean hit_on) {
		BoardDTO boardDTO=null;
		try {
			conn = ds.getConnection();
			String sql = "SELECT * FROM board WHERE seq = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seq);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int seq_ = rs.getInt("seq");
				String name = rs.getString("name");
				String id = rs.getString("id");
				String email = rs.getString("email");
				String subject = rs.getString("subject");
				String content = rs.getString("content");
				int ref = rs.getInt("ref");
				int lev = rs.getInt("lev");
				int step = rs.getInt("step");
				int pseq = rs.getInt("pseq");
				int reply = rs.getInt("reply");
				int hit = rs.getInt("hit");
				Date date = rs.getDate("logtime");
			
				boardDTO = new BoardDTO(seq_, id, name, email, subject, content, ref, lev, step, pseq, reply, hit, date);
				
				if(hit_on) {
					boardDTO.setHit(boardDTO.getHit()+1);
					sql = "UPDATE board SET hit=hit+1 WHERE seq = ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, seq);
					pstmt.executeUpdate();
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return boardDTO;
	}
		
}

//CREATE TABLE board(
//seq NUMBER NOT NULL,               -- 글번호
//id VARCHAR2(20) NOT NULL,           -- 아이디
//name VARCHAR2(40) NOT NULL,       -- 이름
//email VARCHAR2(40),                  -- 이메일
//subject VARCHAR2(255) NOT NULL,    -- 제목
//content VARCHAR2(4000) NOT NULL,   -- 내용 
//
//ref NUMBER NOT NULL,                 -- 그룹번호
//lev NUMBER DEFAULT 0 NOT NULL,     -- 단계
//step NUMBER DEFAULT 0 NOT NULL,    -- 글순서
//pseq NUMBER DEFAULT 0 NOT NULL,    -- 원글번호
//reply NUMBER DEFAULT 0 NOT NULL,   -- 답변수
//
//hit NUMBER DEFAULT 0,              -- 조회수
//logtime DATE DEFAULT SYSDATE
//);
