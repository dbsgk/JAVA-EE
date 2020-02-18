package user.dao;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

import user.bean.UserDTO;

public class UserDAO {
	private static UserDAO instance;
	private SqlSessionFactory sqlSessionFactory;

	public UserDAO() {
		/* mybatis-config.xml 파일을 읽어서 SqlSessionFactory를 생성한다.*/
		/* 파일을 읽으려 할 때 IOStream 필요*/
		/* 1.byte - InputStream
		 * 2.문자 - Reader 			
		 * */
		try {
			Reader reader = Resources.getResourceAsReader("mybatis-config.xml");/*   /src에 있는 파일을 읽어온다. */
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static UserDAO getInstance() {
		if(instance==null) {
			synchronized (UserDAO.class) {
				instance = new UserDAO();
			}
		}
		return instance;
	}

	public void userWrite(UserDTO userDTO) {
		SqlSession sqlSession = sqlSessionFactory.openSession();//sqlSessionFactory는 나에게 sqlSession을 넘겨준다.
		sqlSession.insert("userSQL.userWrite", userDTO);/* mapper파일을 불러서 그 파일안에 mapper id가 맞는 걸 불러. + userDTO가져가 */
		sqlSession.commit();
		sqlSession.close();
		
	}

	public List<UserDTO> getUserList() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		List<UserDTO> list = sqlSession.selectList("userSQL.getUserList");//가져갈 데이터 없어서 "(sql mapper id)"만.
		
		sqlSession.close();//읽어오기만 하는거라서 commit 안하고 close바로 한다.
		return list;
	}

	public UserDTO selectOne(String id) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserDTO userDTO = sqlSession.selectOne("userSQL.selectOne", id);
		sqlSession.close();
		return userDTO;
	}

	public void userUpdate(UserDTO userDTO) {
		SqlSession sqlSession = sqlSessionFactory.openSession();//sqlSessionFactory는 나에게 sqlSession을 넘겨준다.
		sqlSession.update("userSQL.userUpdate",userDTO);
		sqlSession.commit();
		sqlSession.close();
	}

	public void userDelete(String id) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		sqlSession.delete("userSQL.userDelete", id);
		sqlSession.commit();
		sqlSession.close();
	}

	public List<UserDTO> userSearch(Map<String, String> map) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		List<UserDTO> list = sqlSession.selectList("userSQL.userSearch", map);
		sqlSession.close();
		return list;
	}
}
