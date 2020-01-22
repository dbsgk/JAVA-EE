package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Student_t {
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String username = "c##java";
	private String password = "bit";
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public Student_t() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}	
	}
	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url,username,password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	public void menu() {
		System.out.println("****************");
		System.out.println("     ����");
		System.out.println("****************");
		System.out.println("   1. �Է�");
		System.out.println("   2. �˻�");
		System.out.println("   3. ����");
		System.out.println("   4. ����");
		System.out.println("****************");
		System.out.print("��ȣ����: ");
	}
	private void insertArticle() {
		Connection conn = this.getConnection();
		PreparedStatement ps = null;
		String name = null;
		String value = null;
		
		System.out.println("****************");
		System.out.println("1. �л�");
		System.out.println("2. ����");
		System.out.println("3. ������");
		System.out.println("4. �����޴�");
		System.out.println("****************");
		System.out.print("��ȣ����: ");
		
		int code = scan.nextInt();
		if(code ==1) {//�л�
			System.out.print("�̸��Է�: ");
			name = scan.next();
			System.out.print("�й��Է�: ");
			value = scan.next();
		}else if(code ==2) {//����
			System.out.print("�̸��Է�: ");
			name = scan.next();
			System.out.print("�����Է�: ");
			value = scan.next();
		}else if(code ==3) {//������
			System.out.print("�̸��Է�: ");
			name = scan.next();
			System.out.print("�μ��Է�: ");
			value = scan.next();
		}else if(code ==4) {//�����޴�
			return;
		}else return;
		
		try {
			String sql = "insert into student(name,value,code) values(?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, value);
			ps.setInt(3, code);
			
			int su = ps.executeUpdate();//����
			System.out.println(su+"���� ����������ϴ�.");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps!=null)ps.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}//insert
	private void selectArticle() {
		Connection conn = this.getConnection();
		PreparedStatement ps = null;
		String name = "";
		String sql = "select name,age,height,to_char(logtime,'YYYY.MM.DD') as logtime from dbtest";
		ResultSet rs = null;
		int code = 0;
		while(true) {
			System.out.println("****************");
			System.out.println("1. �̸� �˻�");// (1�� ���ڰ� ���Ե� �̸��� ��� �˻�)
			System.out.println("2. ��ü �˻�");
			System.out.println("3. �����޴�");
			System.out.println("****************");
			System.out.print("��ȣ����: ");
			code = scan.nextInt();
			if(code==1) {
				System.out.print("�˻��� �̸� �Է� : ");
				name = scan.next();
				sql = "select * from student where name like '%"+name+"%'";
				break;
			}else if(code==2) {
				//��ü���
				sql = "select * from student";
				break;
			}else if(code==3) {
				return;
			}else {
				System.out.println("��ȣ�� �߸� �Է��ϼ̽��ϴ�.");continue;
			}
		}
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();//����
			while(rs.next()) {
				if(rs.getInt("code")==1) {
					System.out.println("�̸� = "+rs.getString("name")+"\t"
							+"�й� = "+rs.getString("value"));
				}else if(rs.getInt("code")==2) {
					System.out.println("�̸� = "+rs.getString("name")+"\t"
							+"���� = "+rs.getString("value"));
				}else if(rs.getInt("code")==3) {
					System.out.println("�̸� = "+rs.getString("name")+"\t"
							+"�μ� = "+rs.getString("value"));
				}
				
			}//while
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps!=null)ps.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}//select
	private void deleteArticle() {
		Connection conn = this.getConnection();
		PreparedStatement ps = null;
		System.out.print("������ ���ϴ� �̸� �Է� : ");
		String name = scan.next();
		//������ ���ϴ� �̸� �Է� : (��Ȯ�ϰ� 3���� ���ڰ� ��� �Էµ� ����)
		try {
			String sql = "delete student where name = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			int su = ps.executeUpdate();//����
			System.out.println(su+"���� �����Ǿ����ϴ�.");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps!=null)ps.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}//delete
	public static void main(String[] args) {
		Student_t st = new Student_t();
		st.menu();
	}
}