package board.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import board.bean.BoardDTO;

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
	
	public static BoardDAO getInstance() {
		synchronized (BoardDAO.class) {
			if(instance!=null) return instance;
			return new BoardDAO();
		}
	}

	public void getConnection() {
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
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
	
	public boolean insert(BoardDTO boardDTO) {
		boolean result = false;
		getConnection();
		try {
			String sql = "SELECT seq_board.nextval from dual";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			int seq = rs.getInt(1);
			
			sql = "INSERT INTO board(id, name, email, subject, content, seq, ref) VALUES(?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			int index = 1;
			pstmt.setString(index++, boardDTO.getId());
			pstmt.setString(index++, boardDTO.getName());
			pstmt.setString(index++, boardDTO.getEmail());
			pstmt.setString(index++, boardDTO.getSubject());
			pstmt.setString(index++, boardDTO.getContent());
			pstmt.setInt(index++, seq);
			pstmt.setInt(index++, seq);
			
			int r = pstmt.executeUpdate();
			if(r == 1) result = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
		return result;
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
