package friend.action;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import friend.bean.FriendDTO;

public class FriendManager_mang extends JFrame {
	private JLabel inputL,nameL, phoneL,pDash1,pDash2, genL, hobL, listL, areaL;
	private JComboBox<String> phoneCB;
	private String[] items;
	private JTextField nameF, phoneF1,phoneF2;
	private JRadioButton gen1, gen2;
	private JCheckBox hob1,hob2,hob3,hob4,hob5;
	private JButton insertB, updateB, deleteB, clearB;
	private JList<FriendDTO> list;
	private JTextArea area;
	public FriendManager_mang() {
		inputL = new JLabel("                                                         개인정보입력");
		nameL = new JLabel("이        름:  ");
		phoneL = new JLabel("전화번호: ");
		pDash1 = new JLabel("-");
		pDash2 = new JLabel("-");
		genL = new JLabel("성        별:");
		hobL = new JLabel("취   미:");
		listL = new JLabel("                                                 전체목록");
		areaL = new JLabel("개인정보분석");
		
		nameF = new JTextField(15);
		items = new String[] {"010","02","031","032","033"};
		phoneCB = new JComboBox<String>(items);
		phoneF1 = new JTextField(5);
		phoneF2 = new JTextField(5);
		
		gen1 = new JRadioButton("남성");
		gen1.setSelected(true);
		gen1.setBackground(new Color(221,160,221));
		gen2 = new JRadioButton("여성");
		gen2.setBackground(new Color(221,160,221));
		
		hob1 = new JCheckBox("독서");
		hob2 = new JCheckBox("영화");
		hob3 = new JCheckBox("음악");
		hob4 = new JCheckBox("게임");
		hob5 = new JCheckBox("쇼핑");
		hob1.setBackground(new Color(221,160,221));
		hob2.setBackground(new Color(221,160,221));
		hob3.setBackground(new Color(221,160,221));
		hob4.setBackground(new Color(221,160,221));
		hob5.setBackground(new Color(221,160,221));
		
		insertB = new JButton("등록");
		updateB = new JButton("수정");
		updateB.setEnabled(false);
		deleteB = new JButton("삭제");
		deleteB.setEnabled(false);
		clearB = new JButton("지우기");
		clearB.setEnabled(false);
		
		list = new JList<FriendDTO>();
		list.setSize(50,50);
		area = new JTextArea(10,60);
		
		
		JPanel inputP = new JPanel(new FlowLayout(FlowLayout.LEFT));		
		inputP.add(inputL);
		
		JPanel p1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		p1.add(nameL);
		p1.add(nameF);
		
		JPanel p2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		p2.add(phoneL);
		p2.add(phoneCB);
		p2.add(pDash1);
		p2.add(phoneF1);
		p2.add(pDash2);
		p2.add(phoneF2);
		
		JPanel p3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		p3.add(genL);
		p3.add(gen1);
		p3.add(gen2);
		
		JPanel p4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		p4.add(hobL);
		p4.add(hob1);
		p4.add(hob2);
		p4.add(hob3);
		p4.add(hob4);
		p4.add(hob5);
		
		JPanel p5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		p5.add(insertB);
		p5.add(updateB);
		p5.add(deleteB);
		p5.add(clearB);
		
		JPanel p0 = new JPanel(new GridLayout(6,1));
		p0.setBackground(new Color(221,160,221));
		p0.add(inputP);
		p0.add(p1);
		p0.add(p2);
		p0.add(p3);
		p0.add(p4);
		p0.add(p5);
		
		JPanel p6 = new JPanel(new BorderLayout());
		inputP.setBackground(new Color(221,160,221));
		p1.setBackground(new Color(221,160,221));
		p2.setBackground(new Color(221,160,221));
		p3.setBackground(new Color(221,160,221));
		p4.setBackground(new Color(221,160,221));
		p5.setBackground(new Color(221,160,221));
		p6.setBackground(new Color(221,160,221));
		p6.add("North",listL);
		p6.add("Center",list);
		
		JPanel centerP = new JPanel(new GridLayout(1,2));
		centerP.setBackground(new Color(221,160,221));
		centerP.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		centerP.add(p0);
		centerP.add(p6);
		
		
		JPanel p7 = new JPanel();
		p7.setBackground(new Color(221,160,221));
		p7.add(areaL);
		p7.add(area);
		
		JPanel p8 = new JPanel();
		//p8.setBackground(new Color(221,160,221));
		
		JPanel southP = new JPanel(new BorderLayout());
		southP.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		southP.setBackground(new Color(221,160,221));
		southP.add(p7);
		//southP.add(p8);
		
		JPanel p9 = new JPanel();
		
		JPanel p10 = new JPanel();
		JPanel p11 = new JPanel();
		JPanel p12 = new JPanel();
		
//		JPanel p3 = new JPanel(new GridLayout(1,2));
//		p3.setBackground(new Color(221,160,221));
//		p3.add(p1);
//		p3.add(p2);
//		
//		
//		JPanel p4 = new JPanel(new BorderLayout());
//		
//		JPanel p5 = new JPanel(new BorderLayout());
//		p5.setBackground(new Color(221,160,221));
		
		
		add("Center",centerP);
		add("South",southP);
		//add("Center",p4);
		
		setBackground(new Color(221,160,221));
		setBounds(400,200,700,600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		new FriendManager_mang();
	}
}
