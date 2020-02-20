package member.dao;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

import member.bean.AddrDTO;
import member.bean.MemberDTO;

public class MemberDAO {
	
	private static MemberDAO instance;
	private SqlSessionFactory sqlSessionFactory;
	
	public MemberDAO() {
		Reader reader;
		try {
			reader = Resources.getResourceAsReader("mybatis-config.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static MemberDAO getInstance() {
		if(instance!=null) return instance;
		synchronized(MemberDAO.class) {
			instance = new MemberDAO();
		}
		return instance;
	}
	
	public boolean insert(MemberDTO memberDTO) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int result = sqlSession.insert("memberSQL.insert", memberDTO);
		sqlSession.commit();
		sqlSession.close();
		return result > 0 ? true : false;
	}
	
	public boolean update(MemberDTO memberDTO) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int result = sqlSession.update("memberSQL.update", memberDTO);
		sqlSession.commit();
		sqlSession.close();
		return result == 1 ? true : false;
	}
	
	public String getMemberName(String id, String pwd) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("pwd", pwd);
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		MemberDTO memberDTO = sqlSession.selectOne("memberSQL.login", map);
		sqlSession.close();
		
		if(memberDTO != null) {
			return memberDTO.getName();
		}
		else return null;
	}
	
	public MemberDTO getMember(String id) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		MemberDTO memberDTO = sqlSession.selectOne("memberSQL.select", id);
		sqlSession.close();
		
		return memberDTO;
	}
	
	public List<String> getSido(){
		SqlSession sqlSession = sqlSessionFactory.openSession();
		List<String> list = sqlSession.selectList("memberSQL.getSido");
		sqlSession.close();
		
		return list;
	}
	
	public List<AddrDTO> getAddr(String sido, String sigungu, String roadname){
		String sigungu_n = "%" + sigungu + "%";
		String roadname_n = "%" + roadname.split(" ")[0] + "%";
		
		String buildingname;
		if(roadname.split(" ").length > 1) {
			buildingname = "%" + roadname.split(" ")[1] + "%";
		}else {
			buildingname = "%%";
		}
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("sido", sido);
		map.put("sigungu", sigungu_n);
		map.put("roadname", roadname_n);
		map.put("buildingname", buildingname);
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		List<AddrDTO> list = sqlSession.selectList("memberSQL.getAddr", map);
		sqlSession.close();
		
		return list;
	}
	
	public boolean checkId(String id) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int result = sqlSession.selectOne("memberSQL.checkId", id);
		sqlSession.close();
		
		return result > 0 ? true:false;
	}
	
	
	public List<MemberDTO> selectAll(){
		List<MemberDTO> list = new ArrayList<MemberDTO>();
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		list = sqlSession.selectList("memberSQL.selectAll");
		sqlSession.close();
		return list;
	}
	
	
}

