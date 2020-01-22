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
		inputL = new JLabel("                                                         ���������Է�");
		nameL = new JLabel("��        ��:  ");
		phoneL = new JLabel("��ȭ��ȣ: ");
		pDash1 = new JLabel("-");
		pDash2 = new JLabel("-");
		genL = new JLabel("��        ��:");
		hobL = new JLabel("��   ��:");
		listL = new JLabel("                                                 ��ü���");
		areaL = new JLabel("���������м�");
		
		nameF = new JTextField(15);
		items = new String[] {"010","02","031","032","033"};
		phoneCB = new JComboBox<String>(items);
		phoneF1 = new JTextField(5);
		phoneF2 = new JTextField(5);
		
		gen1 = new JRadioButton("����");
		gen1.setSelected(true);
		gen1.setBackground(new Color(221,160,221));
		gen2 = new JRadioButton("����");
		gen2.setBackground(new Color(221,160,221));
		
		hob1 = new JCheckBox("����");
		hob2 = new JCheckBox("��ȭ");
		hob3 = new JCheckBox("����");
		hob4 = new JCheckBox("����");
		hob5 = new JCheckBox("����");
		hob1.setBackground(new Color(221,160,221));
		hob2.setBackground(new Color(221,160,221));
		hob3.setBackground(new Color(221,160,221));
		hob4.setBackground(new Color(221,160,221));
		hob5.setBackground(new Color(221,160,221));
		
		insertB = new JButton("���");
		updateB = new JButton("����");
		updateB.setEnabled(false);
		deleteB = new JButton("����");
		deleteB.setEnabled(false);
		clearB = new JButton("�����");
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