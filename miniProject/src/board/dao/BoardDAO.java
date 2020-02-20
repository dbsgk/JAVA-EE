package board.dao;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	public List<BoardDTO> getList(int startNum, int endNum) {
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("startNum", startNum);
		map.put("endNum", endNum);
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		List<BoardDTO> list = sqlSession.selectList("boardSQL.getList", map);
		
		sqlSession.close();
		return list;
	}
	
	public int getBoardTotalA() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int result = sqlSession.selectOne("boardSQL.getCount");
		sqlSession.close();
		return result;
	}
	
	public boolean insert(BoardDTO boardDTO) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int result = sqlSession.insert("boardSQL.insert", boardDTO);
		sqlSession.commit();
		sqlSession.close();
		
		return result > 0 ? true : false;
	}
	
	public boolean insert(Map<String, String> map) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int result = sqlSession.insert("boardSQL.insertMap", map);
		sqlSession.commit();
		sqlSession.close();
		
		return result > 0 ? true : false;
	}
	
	public boolean update(BoardDTO boardDTO) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int result = sqlSession.update("boardSQL.update", boardDTO);
		sqlSession.commit();
		sqlSession.close();
		
		return result > 0 ? true : false;
	}
	
	public boolean delete(int seq) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		sqlSession.update("boardSQL.updateReply", seq);
		int result = sqlSession.delete("boardSQL.delete", seq);
		sqlSession.update("boardSQL.updateReplyName", seq);
		sqlSession.commit();
		
		sqlSession.close();
		return result > 0 ? true : false;
	}
	
	public BoardDTO selectOne(int seq, boolean hit_on) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		if(hit_on) {
			sqlSession.update("boardSQL.updateHit", seq);
			sqlSession.commit();
		}
		BoardDTO boardDTO = sqlSession.selectOne("boardSQL.selectOne", seq);
		sqlSession.close();
		
		return boardDTO;
	}

	public boolean reply(Map<String, List<String>> map, int pseq) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		BoardDTO pDTO = sqlSession.selectOne("boardSQL.selectOne", pseq);
		map.get("fields").add("ref");
		map.get("fields").add("step");
		map.get("fields").add("lev");
		map.get("values").add(pDTO.getRef()+"");
		map.get("values").add((pDTO.getStep()+1) + "");
		map.get("values").add((pDTO.getLev()+1) + "");
		sqlSession.update("boardSQL.boardReply1", pDTO);
		sqlSession.update("boardSQL.boardReply2", pseq);
		int result = sqlSession.insert("boardSQL.insertMap", map);
		sqlSession.commit();
		
		sqlSession.close();
		
		return result > 0 ? true : false;
		
	}
	
}
