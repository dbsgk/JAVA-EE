package member.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import member.bean.MemberDTO;
import member.bean.ZipcodeDTO;

public class MemberDAO {
	private static MemberDAO instance;
	
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String username = "c##java";
	private String password = "bit";
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public static MemberDAO getInstance() {
		if(instance==null) {
			synchronized (MemberDAO.class) {
				instance = new MemberDAO();
			}
		}
		return instance;
	}
	
	public MemberDAO() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void getConnection() {
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public boolean isExistId(String id) {
		boolean exist=false;
		String sql = "select * from member where id=?";
		
		this.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next())
				exist = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return exist;
	}

	public int write(MemberDTO memberDTO) {
		int su=0;
		getConnection();
		String sql = "insert into member values(?,?,?,?,?,?,?,?,?,?,?,?,sysdate)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberDTO.getName());
			pstmt.setString(2, memberDTO.getId());
			pstmt.setString(3, memberDTO.getPwd());
			pstmt.setString(4, memberDTO.getGender());
			pstmt.setString(5, memberDTO.getEmail1());
			pstmt.setString(6, memberDTO.getEmail2());
			pstmt.setString(7, memberDTO.getTel1());
			pstmt.setString(8, memberDTO.getTel2());
			pstmt.setString(9, memberDTO.getTel3());
			pstmt.setString(10, memberDTO.getZipcode());
			pstmt.setString(11, memberDTO.getAddr1());
			pstmt.setString(12, memberDTO.getAddr2());
			
			su = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return su;
	}

	public MemberDTO login(String id, String pwd) {
		MemberDTO memberDTO = null;
		getConnection();
		String sql = "select * from member where id=? and pwd=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				memberDTO = new MemberDTO();
				memberDTO.setName(rs.getString("name"));
				memberDTO.setId(rs.getString("id"));
				memberDTO.setPwd(rs.getString("pwd"));
				memberDTO.setGender(rs.getString("gender"));
				memberDTO.setEmail1(rs.getString("email1"));
				memberDTO.setEmail2(rs.getString("email2"));
				memberDTO.setTel1(rs.getString("tel1"));
				memberDTO.setTel2(rs.getString("tel2"));
				memberDTO.setTel3(rs.getString("tel3"));
				memberDTO.setZipcode(rs.getString("zipcode"));
				memberDTO.setAddr1(rs.getString("addr1"));
				memberDTO.setAddr2(rs.getString("addr2"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return memberDTO;
	}
	
	public List<ZipcodeDTO> getZipcodeList(String sido, String sigungu, String roadname){
		List<ZipcodeDTO> list = new ArrayList<ZipcodeDTO>();
		String sql = "select * from newzipcode where sido like ? "
					+ "and nvl(sigungu,'0') like ? "
					+ "and roadname like ? ";
		
		getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+sido+"%");
			pstmt.setString(2, "%"+sigungu+"%");
			pstmt.setString(3, "%"+roadname+"%");
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ZipcodeDTO zipcodeDTO = new ZipcodeDTO();
				zipcodeDTO.setZipcode(rs.getString("zipcode"));
				zipcodeDTO.setSido(rs.getString("sido"));
				zipcodeDTO.setSigungu(rs.getString("sigungu")==null ? "" : rs.getString("sigungu"));
				zipcodeDTO.setYubmyundong(rs.getString("yubmyundong"));
				zipcodeDTO.setRi(rs.getString("ri")==null ? "" : rs.getString("ri"));                 
				zipcodeDTO.setRoadname(rs.getString("roadname"));
				zipcodeDTO.setBuildingname(rs.getString("buildingname")==null ? "" : rs.getString("buildingname"));
				
				list.add(zipcodeDTO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			list = null;
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt !=null) pstmt.close();
				if(conn != null) conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
				
		return list;
	}
	
	public MemberDTO getMember(String id){
		MemberDTO memberDTO = null;
		String sql = "SELECT * FROM member WHERE id=?";
		
		getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();//실행
			
			if(rs.next()) {
				memberDTO = new MemberDTO();
				
				memberDTO.setId(rs.getString("id"));
	            memberDTO.setName(rs.getString("name"));
	            memberDTO.setGender(rs.getString("gender"));
	            memberDTO.setEmail1(rs.getString("email1"));
	            memberDTO.setEmail2(rs.getString("email2"));
	            memberDTO.setTel1(rs.getString("tel1"));
	            memberDTO.setTel2(rs.getString("tel2"));
	            memberDTO.setTel3(rs.getString("tel3"));
	            memberDTO.setZipcode(rs.getString("zipcode"));
	            memberDTO.setAddr1(rs.getString("addr1"));
	            memberDTO.setAddr2(rs.getString("addr2"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
            try {
                if(rs != null)rs.close();
                if(pstmt != null) pstmt.close();
                if(conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
		}
		
		return memberDTO;
	}
	
	public void modify(MemberDTO memberDTO) {
		String sql = "UPDATE member "
                	+ "SET name = ?, "
                    + "pwd = ?, "
                    + "gender = ?, "
                    + "email1 = ?, "
                    + "email2 = ?, "
                    + "tel1 = ?, "
                    + "tel2 = ?, "
                    + "tel3 = ?, "
                    + "zipcode = ?, "
                    + "addr1 = ?, "
                    + "addr2 = ?, "
                    + "logtime = SYSDATE WHERE id=?";
		getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberDTO.getName());
            pstmt.setString(2, memberDTO.getPwd());
            pstmt.setString(3, memberDTO.getGender());
            pstmt.setString(4, memberDTO.getEmail1());
            pstmt.setString(5, memberDTO.getEmail2());
            pstmt.setString(6, memberDTO.getTel1());
            pstmt.setString(7, memberDTO.getTel2());
            pstmt.setString(8, memberDTO.getTel3());
            pstmt.setString(9, memberDTO.getZipcode());
            pstmt.setString(10, memberDTO.getAddr1());
            pstmt.setString(11, memberDTO.getAddr2());
            pstmt.setString(12, memberDTO.getId());
            
            pstmt.executeUpdate();
            
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
            try {
                if(pstmt!=null) pstmt.close();
                if(conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
	}
}













