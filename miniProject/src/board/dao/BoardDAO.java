package board.dao;

import java.io.IOException;
import java.io.Reader;
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

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import board.bean.BoardDTO;

public class BoardDAO {

	private static BoardDAO instance;
	private SqlSessionFactory sqlSessionFactory;

	public BoardDAO() {
		try {
			Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static BoardDAO getInstance() {
		synchronized (BoardDAO.class) {
			if(instance!=null) return instance;
			return new BoardDAO();
		}
	}
	/*
	 * public int boardWrite(BoardDTO boardDTO) { int result = 0; SqlSession
	 * sqlSession = sqlSessionFactory.openSession(); result =
	 * sqlSession.insert("boardSQL.boardWrite",boardDTO); sqlSession.commit();
	 * sqlSession.close();
	 * 
	 * return result; }
	 */

	public int boardWrite(Map<String, String> map) {
		int result = 0;
		SqlSession sqlSession = sqlSessionFactory.openSession();
		result = sqlSession.insert("boardSQL.boardWrite",map);
		sqlSession.commit();
		sqlSession.close();
		
		return result;
	}
	public List<BoardDTO> getList(Map<String, Integer> map) { 
		SqlSession sqlSession = sqlSessionFactory.openSession();
		List<BoardDTO> list = sqlSession.selectList("boardSQL.getList" , map);
		sqlSession.close();
		return list; 
	}
	public BoardDTO getBoard(int seq) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		BoardDTO boardDTO = sqlSession.selectOne("boardSQL.getBoard" , seq);
		sqlSession.close();
		return boardDTO; 
	}
	
	public int getBoardTotalA() {
		int result = 0;
		SqlSession sqlSession = sqlSessionFactory.openSession();
		result = sqlSession.selectOne("boardSQL.getBoardTotalA");
		sqlSession.close();
		return result;
	}
	 public void boardUpdate(Map<String, String> map) { 
		SqlSession sqlSession = sqlSessionFactory.openSession();
		sqlSession.update("boardSQL.boardUpdate", map);
		sqlSession.commit();
		sqlSession.close();
	 }
	 public void boardHit(int seq) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		sqlSession.update("boardSQL.boardHit", seq);
		sqlSession.commit();
		sqlSession.close();
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
