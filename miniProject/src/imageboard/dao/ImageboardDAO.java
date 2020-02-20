package imageboard.dao;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import imageboard.bean.ImageboardDTO;

public class ImageboardDAO {

	private static ImageboardDAO instance;
	private SqlSessionFactory sqlSessionFactory;

	public ImageboardDAO() {
		Reader reader;
		try {
			reader = Resources.getResourceAsReader("mybatis-config.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static ImageboardDAO getInstance() {
		synchronized (ImageboardDAO.class) {
			if(instance!=null) return instance;
			return new ImageboardDAO();
		}
	}

	public int getBoardTotalA() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int result = sqlSession.selectOne("imageboardSQL.getCount");
		sqlSession.close();
		return result;
	}
	
	public boolean insert(ImageboardDTO imageboardDTO) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int result = sqlSession.insert("imageboardSQL.insert", imageboardDTO);
		sqlSession.commit();
		sqlSession.close();
		
		return result > 0 ? true: false;
	}
	
	public boolean update(ImageboardDTO imageboardDTO) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int result = sqlSession.update("imageboardSQL.update", imageboardDTO);
		sqlSession.commit();
		sqlSession.close();
		
		return result > 0 ? true: false;
	}
	
	public boolean delete(int seq) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int result = sqlSession.delete("imageboardSQL.delete", seq);
		sqlSession.commit();
		sqlSession.close();
		
		return result > 0 ? true: false;
	}
	
	public boolean deleteMap(Map<String, List<String>> map) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int result = sqlSession.delete("imageboardSQL.deleteMap", map);
		sqlSession.commit();
		sqlSession.close();
		
		return result > 0 ? true: false;
	}
	
	public List<ImageboardDTO> getList(int StartNum, int EndNum) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("StartNum", StartNum);
		map.put("EndNum", EndNum);
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		List<ImageboardDTO> list = sqlSession.selectList("imageboardSQL.getList", map);
		sqlSession.commit();
		sqlSession.close();
		
		return list;
	}
	
	public ImageboardDTO selectOne(int seq, boolean hit_on) {
		ImageboardDTO imageboardDTO = null;
		SqlSession sqlSession = sqlSessionFactory.openSession();
		imageboardDTO = sqlSession.selectOne("imageboardSQL.selectOne", seq);
		sqlSession.close();
		
		return imageboardDTO;
	}
//	public boolean imageboardDelete2(String[] seq) {
//		SqlSession sqlSession = sqlSessionFactory.openSession();
//		
//		Map<String, String[]> map = new HashMap<String, String[]>();
//		map.put("array", seq);
//		sqlSession.delete("imageboardSQL.imageboardDelete2", map);
//		sqlSession.commit();
//		sqlSession.close();
//		
//		return result > 0 ? true: false;
//	}
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
