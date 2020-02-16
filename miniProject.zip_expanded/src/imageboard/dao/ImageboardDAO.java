package imageboard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import board.bean.BoardDTO;
import imageboard.bean.ImageboardDTO;

public class ImageboardDAO {

	private static ImageboardDAO instance;
	private DataSource ds;
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public ImageboardDAO() {
		Context ctx;
		try {
			ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/oracle"); // Tomcat의 경우 'java:comp/env/' 이것을 꼭 써줘야 한다.
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public static ImageboardDAO getInstance() {
		synchronized (ImageboardDAO.class) {
			if(instance!=null) return instance;
			return new ImageboardDAO();
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
			String sql = "SELECT COUNT(*) FROM imageboard";
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
	
	public boolean insert(ImageboardDTO imageboardDTO) {
		boolean result = false;
		try {
			conn = ds.getConnection();
			String sql = "INSERT INTO imageboard(seq, imageId, imageName, imagePrice, imageQty, imageContent, image1, logtime)"
					+ " VALUES(seq_imageboard.nextval,?,?,?,?,?,?,sysdate)";
			pstmt = conn.prepareStatement(sql);
			int index = 1;
			pstmt.setString(index++, imageboardDTO.getImageId());
			pstmt.setString(index++, imageboardDTO.getImageName());
			pstmt.setInt(index++, imageboardDTO.getImagePrice());
			pstmt.setInt(index++, imageboardDTO.getImageQty());
			pstmt.setString(index++, imageboardDTO.getImageContent());
			pstmt.setString(index++, imageboardDTO.getImage1());
			
			if(pstmt.executeUpdate() == 1) result = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
		return result;
	}
	
	public boolean update(ImageboardDTO imageboardDTO) {
		boolean result = false;
		
		try {
			conn = ds.getConnection();
			String sql = "UPDATE imageboard "
					+ "SET imageId=?, imageName=?, imagePrice=?, imageQty=?, imageContent=?, image1=?, logtime=sysdate"
					+ " WHERE seq=?";
			pstmt = conn.prepareStatement(sql);
			int index = 1;
			pstmt.setString(index++, imageboardDTO.getImageId());
			pstmt.setString(index++, imageboardDTO.getImageName());
			pstmt.setInt(index++, imageboardDTO.getImagePrice());
			pstmt.setInt(index++, imageboardDTO.getImageQty());
			pstmt.setString(index++, imageboardDTO.getImageContent());
			pstmt.setString(index++, imageboardDTO.getImage1());
			pstmt.setInt(index++, imageboardDTO.getSeq());
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
			String sql = "DELETE imageboard WHERE seq=?";
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
	
	public List<ImageboardDTO> getList(int StartNum, int EndNum) {
		List<ImageboardDTO> list = new ArrayList<ImageboardDTO>();
		try {
			conn = ds.getConnection();
			String sql = "SELECT * FROM (SELECT rownum rn, tt.* from (SELECT * FROM imageboard ORDER BY seq DESC) tt)"
					+ " WHERE rn >= " + StartNum + " AND rn <= " + EndNum;
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int seq = rs.getInt("seq");
				String imageName = rs.getString("imageName");
				String imageId = rs.getString("imageId");
				int imagePrice = rs.getInt("imagePrice");
				int imageQty = rs.getInt("imageQty");
				String imageContent = rs.getString("imageContent");
				String image1 = rs.getString("image1").replaceAll(" ","%20");
				Date date = rs.getDate("logtime");
			
				ImageboardDTO imageboardDTO = new ImageboardDTO(seq, imageId, imageName, imagePrice, imageQty, imageContent, image1, date);
				list.add(imageboardDTO);
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
