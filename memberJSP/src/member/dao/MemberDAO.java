package member.dao;

import java.sql.Connection;
import java.sql.DriverManager;
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

import member.bean.AddrDTO;
import member.bean.MemberDTO;

public class MemberDAO {
	
	private static MemberDAO instance;
	
	private DataSource ds;
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public MemberDAO() {
		Context ctx;
		try {
			ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/oracle");
		} catch (NamingException e) {
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
	
	public void disconnect() {
		try {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean insert(MemberDTO memberDTO) {
		String sql = "INSERT INTO member VALUES(?,?,?,?,?,?,?,?,?,?,?,?,sysdate)";
		int result = 0;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			int index = 1;
			pstmt.setString(index++, memberDTO.getName());
			pstmt.setString(index++, memberDTO.getId());
			pstmt.setString(index++, memberDTO.getPwd());
			pstmt.setString(index++, memberDTO.getGender());
			pstmt.setString(index++, memberDTO.getEmail1());
			pstmt.setString(index++, memberDTO.getEmail2());
			pstmt.setString(index++, memberDTO.getTel1());
			pstmt.setString(index++, memberDTO.getTel2());
			pstmt.setString(index++, memberDTO.getTel3());
			pstmt.setString(index++, memberDTO.getZipcode());
			pstmt.setString(index++, memberDTO.getAddr1());
			pstmt.setString(index++, memberDTO.getAddr2());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return result == 1 ? true : false;
	}
	
	public boolean update(MemberDTO memberDTO) {
		String sql = "Update member SET name=?, pwd=?, gender=?, email1=?, email2=?, tel1=?, tel2=?, tel3=?, zipcode=?, addr1=?, addr2=?, logtime=sysdate"
				+ " WHERE id = ?";
		int result = 0;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			int index = 1;
			pstmt.setString(index++, memberDTO.getName());
			pstmt.setString(index++, memberDTO.getPwd());
			pstmt.setString(index++, memberDTO.getGender());
			pstmt.setString(index++, memberDTO.getEmail1());
			pstmt.setString(index++, memberDTO.getEmail2());
			pstmt.setString(index++, memberDTO.getTel1());
			pstmt.setString(index++, memberDTO.getTel2());
			pstmt.setString(index++, memberDTO.getTel3());
			pstmt.setString(index++, memberDTO.getZipcode());
			pstmt.setString(index++, memberDTO.getAddr1());
			pstmt.setString(index++, memberDTO.getAddr2());
			pstmt.setString(index++, memberDTO.getId());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return result == 1 ? true : false;
	}
	
	public String getMemberName(String id, String pwd) {
		String memberName=null;
		String sql = "SELECT name FROM member WHERE id=? AND pwd=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				memberName = rs.getString(1);
				System.out.println(memberName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return memberName;
	}
	
	public MemberDTO getMember(String id) {
		MemberDTO memberDTO = null;
		String sql = "SELECT * FROM member WHERE id=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				memberDTO = new MemberDTO(
						rs.getString("name"),
						rs.getString("id"),
						rs.getString("pwd"),
						rs.getString("gender"),
						rs.getString("email1"),
						rs.getString("email2"),
						rs.getString("tel1"),
						rs.getString("tel2"),
						rs.getString("tel3"),
						rs.getString("zipcode"),
						rs.getString("addr1"),
						rs.getString("addr2")
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return memberDTO;
	}
	
	public List<String> getSido(){
		String sql = "select sido, max(zipcode) as zipcode from newzipcode group by sido order by TO_NUMBER(zipcode, '99999')";
		List<String> list = new ArrayList<String>();
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}
	
	public List<AddrDTO> getAddr(String sido, String sigungu, String roadname){
		String sql = "SELECT to_char(zipcode, '00000') as zipcode, sido, sigungu, yubmyundong, ri, roadname, buildingname FROM newzipcode"
			+ " where sido=? and nvl(sigungu,'0') like ? and roadname like ?";
		List<AddrDTO> list = new ArrayList<AddrDTO>();
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sido);
			pstmt.setString(2, "%"+sigungu+"%");
			pstmt.setString(3, "%"+roadname+"%");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				AddrDTO addrDTO = new AddrDTO(rs.getString("zipcode"), 
						rs.getString("sido"),
						rs.getString("sigungu"),
						rs.getString("yubmyundong"),
						rs.getString("ri"),
						rs.getString("roadname"),
						rs.getString("buildingname"));
				list.add(addrDTO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}
	
	public boolean checkId(String id) {
		String sql = "SELECT id FROM member WHERE id=?";
		boolean result = false;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return result;
	}
	
	
	public List<MemberDTO> selectAll(){
		String sql = "SELECT * FROM memeber";
		List<MemberDTO> list = new ArrayList<MemberDTO>();
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				
				MemberDTO memberDTO = new MemberDTO(
						rs.getString("name"), 
						rs.getString("id"), 
						rs.getString("pwd"), 
						rs.getString("gender"), 
						rs.getString("email1"), 
						rs.getString("email2"), 
						rs.getString("tel1"), 
						rs.getString("tel2"), 
						rs.getString("tel3"), 
						rs.getString("zipcode"), 
						rs.getString("addr1"), 
						rs.getString("addr2"), 
						rs.getDate("name"));
				list.add(memberDTO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}
	
	
}
