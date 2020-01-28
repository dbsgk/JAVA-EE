package friend.dao;
//JList�� �̸��� Ŭ���ϸ� �����Ͱ� ���ʿ� �� ���δ�.
// ��� ��Ȱ��
// ����, ���� , ����� Ȱ��ȭ

//����
//JList�� �̸��� �ٽ� Ŭ���ϸ� ������ �������� �����ش�.

//����
//JList���� ������ �̸��� �������.

//�����
//�� �ʱ�ȭ , ��Ϲ�ư�� Ȱ��ȭ
//����, ����, ����� ��Ȱ��ȭ

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import friend.bean.FriendDTO;

public class FriendDAO {
	private FriendDTO dto;
	PreparedStatement pstmt = null;
	Connection conn;
	ResultSet rs;
	public FriendDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("driver loading success");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		getConnection();
	}
	public FriendDAO(FriendDTO dto) {
		this();
		this.dto = dto;
	}

	public Connection getConnection() {//�����ϴ� �޼ҵ�
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","c##java","bit");
//												URL: ����̹���:@ȣ��Ʈ(192.168.0.27):��Ʈ��ȣ:�����ͺ��̽���,ID, PASSWORD
			System.out.println("connection success");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	public void insert(FriendDTO dto) {
		Connection conn = this.getConnection();
		
		int index=1;
		String sql = "insert into friend(seq,name,tel1,tel2,tel3,gender,read,movie,music,game,shop) values(?,?,?,?,?,?,?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql); //����
			pstmt.setInt(index++, dto.getSeq());
			pstmt.setString(index++, dto.getName());
			pstmt.setString(index++, dto.getTel1());
			pstmt.setString(index++, dto.getTel2());
			pstmt.setString(index++, dto.getTel3());
			pstmt.setInt(index++, dto.getGender());
			pstmt.setInt(index++, dto.getRead());
			pstmt.setInt(index++, dto.getMovie());
			pstmt.setInt(index++, dto.getMusic());
			pstmt.setInt(index++, dto.getGame());
			pstmt.setInt(index++, dto.getShop());
			
			int su = pstmt.executeUpdate();//���� - ������ ����
			String msg = su+"���� ��ϵǾ����ϴ�.";
			System.out.println(msg);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {//���� ���� ���� ��������
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
//		insert into dbtest(name,age,height,logtime) values('ȫ�浿',25,185.567,sysdate);
	}
	private static FriendDAO instance=null;
	public static FriendDAO getInstance() {
		
		if(instance==null) {
			synchronized (FriendDAO.class) {
				instance = new FriendDAO();	
			}
			
		}
		return instance;
	}
	public int getSeq() {
		int seq=0;
		getConnection();
		try {
			String sql = "select seq_friend.nextval from dual";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			
			seq = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return seq;
	}
	public List<FriendDTO> getList(){
		List<FriendDTO> list2 = new ArrayList<FriendDTO>();
//		getConnection();
		String sql = "select * from friend";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				System.out.println("getList whlie");
				FriendDTO dto = new FriendDTO();
				dto.setSeq(rs.getInt("seq"));
				dto.setName(rs.getString("name"));
				dto.setTel1(rs.getString("tel1"));
				dto.setTel2(rs.getString("tel2"));
				dto.setTel3(rs.getString("tel3"));
				dto.setGender(rs.getInt("gender"));
				dto.setRead(rs.getInt("read"));
				dto.setMovie(rs.getInt("movie"));
				dto.setMusic(rs.getInt("music"));
				dto.setGame(rs.getInt("game"));
				dto.setShop(rs.getInt("shop"));

				list2.add(dto);
			}//while
		} catch (SQLException e) {
			e.printStackTrace();
			//list2 = null;
		}finally {//���� ���� ���� ��������
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list2;
		
	}
	public void modify(FriendDTO friendDTO) {
		Connection conn = this.getConnection();
		
		int index=1;
		String sql = "update friend set name=?"
									+ ",tel1=?"
									+ ",tel2=?"
									+ ",tel3=?"
									+ ",gender=?"
									+ ",read=?"
									+ ",movie=?"
									+ ",music=?"
									+ ",game=?"
									+ ",shop=? where seq=?";
		try {
			pstmt = conn.prepareStatement(sql); //����
			pstmt.setString(index++, friendDTO.getName());
			pstmt.setString(index++, friendDTO.getTel1());
			pstmt.setString(index++, friendDTO.getTel2());
			pstmt.setString(index++, friendDTO.getTel3());
			pstmt.setInt(index++, friendDTO.getGender());
			pstmt.setInt(index++, friendDTO.getRead());
			pstmt.setInt(index++, friendDTO.getMovie());
			pstmt.setInt(index++, friendDTO.getMusic());
			pstmt.setInt(index++, friendDTO.getGame());
			pstmt.setInt(index++, friendDTO.getShop());
			pstmt.setInt(index++, friendDTO.getSeq());
			
			int su = pstmt.executeUpdate();//���� - ������ ����
			String msg = "���� �Ǿ����ϴ�.";
			System.out.println(msg);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {//���� ���� ���� ��������
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	public void delete(FriendDTO friendDTO) {
		Connection conn = this.getConnection();
		
		int index=1;
		String sql = "delete friend where seq=?";
		try {
			pstmt = conn.prepareStatement(sql); //����
			pstmt.setInt(index++, friendDTO.getSeq());
			
			int su = pstmt.executeUpdate();//���� - ������ ����
			String msg = "���� �Ǿ����ϴ�.";
			System.out.println(msg);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {//���� ���� ���� ��������
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}//delete
}
