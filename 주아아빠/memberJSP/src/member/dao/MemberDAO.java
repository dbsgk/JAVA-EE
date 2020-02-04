package member.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import member.bean.AddrDTO;
import member.bean.MemberDTO;

public class MemberDAO {
	
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe"; 
	private String username = "c##java";
	private String password = "bit";
	
	private static MemberDAO instance;
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public MemberDAO() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
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
	
	public boolean insert(MemberDTO memberDTO) {
		String sql = "INSERT INTO member VALUES(?,?,?,?,?,?,?,?,?,?,?,?,sysdate)";
		getConnection();
		int result = 0;
		try {
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
		getConnection();
		int result = 0;
		try {
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
		getConnection();
		try {
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
		getConnection();
		try {
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
		getConnection();
		String sql = "select sido, max(zipcode) as zipcode from newzipcode group by sido order by TO_NUMBER(zipcode, '99999')";
		List<String> list = new ArrayList<String>();
		try {
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
		getConnection();
		String sql = "SELECT to_char(zipcode, '00000') as zipcode, sido, sigungu, yubmyundong, ri, roadname, buildingname FROM newzipcode"
			+ " where sido=? and nvl(sigungu,'0') like ? and roadname like ?";
		List<AddrDTO> list = new ArrayList<AddrDTO>();
		try {
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
		getConnection();
		boolean result = false;
		try {
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
		getConnection();
		List<MemberDTO> list = new ArrayList<MemberDTO>();
		try {
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
