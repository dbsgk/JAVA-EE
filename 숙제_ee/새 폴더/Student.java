package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Student {
	Connection conn=null;
	Scanner sc = new Scanner(System.in);
	
	public Student() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");//����Ŭ DB����
			System.out.println("����̹� �ε� ����");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public Connection getConnection() {		
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","c##java","bit");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	public void menu() {
		int num=0;
		while(true){
			System.out.println("******************");
			System.out.println("\t����");
			System.out.println("******************");
			System.out.println("1. �Է�");
			System.out.println("2. �˻�");
			System.out.println("3. ����");
			System.out.println("4. ����");
			System.out.println("******************");
			System.out.println("��ȣ : ");
			
			num = sc.nextInt();
			if(num==4) {
				System.out.println("����Ǿ����ϴ�.");
				break;
			}
			else if(num==1) insertArticle();
			else if(num==2) selectArticle();
			else if(num==3) deleteArticle();
			else {
				System.out.println("1~4�߿� �����ϼ���");
			}
		}
	}

	private void insertArticle() {
		int num=0;
		while(true) {
			System.out.println("******************");
			System.out.println("1. �л�");
			System.out.println("2. ����");
			System.out.println("3. ������");
			System.out.println("4. �����޴�");
			System.out.println("******************");
			System.out.println("��ȣ : ");
			num = sc.nextInt();
			
			String name = null;
			String value=null;
			int code=0;
			
			if(num==4) break;
			
			if(num==1) {
				System.out.println("�̸� �Է� : ");
				name = sc.next();
				System.out.println("�й� �Է� : ");
				value = sc.next();
				code = 1;
				}
			if(num==2) {
				System.out.println("�̸� �Է� : ");
				name = sc.next();
				System.out.println("���� �Է�: ");
				value = sc.next();
				code = 2;
			}
			if(num==3) {
				System.out.println("�̸� �Է� : ");
				name = sc.next();
				System.out.println("�μ� �Է� : ");
				value = sc.next();
				code = 3;
			}			
			String sql = "insert into student(name,value,code) values(?,?,?)";
			
			conn = this.getConnection();
			PreparedStatement pstmt=null;
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, name);
				pstmt.setString(2, value);
				pstmt.setInt(3, code);
				
				pstmt.executeUpdate();
				System.out.println("�Է� �Ǿ����ϴ�.");
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					if(pstmt!=null) pstmt.close();
					if(conn!=null) conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}
	private void selectArticle() {
		int num=0;
		while(true) {			
			System.out.println("******************");
			System.out.println("1. �̸� �˻� ");
			System.out.println("2. ��ü �˻�");
			System.out.println("3. ���� �޴�");
			System.out.println("******************");
			System.out.println("��ȣ : ");
			num = sc.nextInt();
			String name=null;
			String sql=null;
			if(num==3)break;
			
			if(num==1) {
				System.out.println("�˻��� �̸� �Է� : ");
				name = sc.next();
				sql = "select * from student where name like '%"+name+"%'";
			}
			if(num==2) {
				sql = "select * from student order by name asc";
			}
		
			conn = this.getConnection();
			PreparedStatement pstmt=null;
			ResultSet rs = null;

			try {
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					System.out.print("�̸�="+rs.getString("name")+"\t");
					String value1=null;
					if(rs.getInt("code")==1) value1 = "�й� = ";
					else if(rs.getInt("code")==2) value1 = "���� = ";
					else if(rs.getInt("code")==3) value1 = "�μ� = ";
					
					System.out.println(value1+rs.getString("value"));
				}//while
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					if(rs!=null) rs.close();
					if(pstmt!=null) pstmt.close();
					if(conn!=null) conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	private void deleteArticle() {
		int num=0;
		while(true) {
			System.out.println("******************");
			System.out.println("1. ���� ");
			System.out.println("2. ���� �޴�");
			System.out.println("******************");
			System.out.println("��ȣ : ");
			num = sc.nextInt();
			String name=null;
			String sql=null;
			if(num==2)break;
			if(num==1) {
				System.out.println("������ ���ϴ� �̸� �Է� : ");
				name = sc.next();
			}
			
			conn = this.getConnection();
			PreparedStatement pstmt=null;
			sql = "delete student where name like '"+name+"'";
			
			try {
				pstmt = conn.prepareStatement(sql);
				int su = pstmt.executeUpdate();
				
				System.out.println(su+"���� ���� �Ǿ����ϴ�.");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					if(pstmt!=null) pstmt.close();
					if(conn!=null) conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public static void main(String[] args) {
		Student st = new Student();
		st.menu();


	}

}
