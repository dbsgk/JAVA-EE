package friend.action.t;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
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

public class FriendManager extends JFrame implements ActionListener{
	private JLabel nameL, phoneL, sexL, hobbyL,input, d1,d2;
	private JTextField nameT, phone1, phone2;
	private JComboBox phoneC;
	private JRadioButton male, female;
	private JCheckBox book, movie, music, game, shop;
	private JButton enroll, mod, del, erase;
	private JLabel infoL, listL, areaL;
	private JTextArea area;
	private JList listJ;
	private DefaultListModel<?> model;
	
	public FriendManager() {
		
		input = new JLabel("���������Է�");
		input.setHorizontalAlignment(JLabel.CENTER);
		input.setBackground(new Color(230,190,230));
		JPanel p1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		p1.setBackground(new Color(230,190,230));
		nameL = new JLabel("��        ��:");
		nameT = new JTextField(7);
		
		p1.add(nameL);
		p1.add(nameT);
		
		JPanel p2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		p2.setBackground(new Color(230,190,230));
		phoneL = new JLabel("��ȭ��ȣ:");
		String num[] = {"010", "011", "016", "017", "018", "019"};
		phoneC = new JComboBox<String>(num);
		d1 = new JLabel("-");
		phone1 = new JTextField(4);
		d2 = new JLabel("-");
		phone2 = new JTextField(4);
		p2.add(phoneL);
		p2.add(phoneC);
		p2.add(d1);
		p2.add(phone1);
		p2.add(d2);
		p2.add(phone2);
		
		JPanel p3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		p3.setBackground(new Color(230,190,230));
		sexL = new JLabel("��         ��:");
		male = new JRadioButton("����");
		male.setBackground(new Color(230,190,230));
		female = new JRadioButton("����");
		female.setBackground(new Color(230,190,230));
		ButtonGroup group = new ButtonGroup();
		group.add(male);
		group.add(female);
		p3.add(sexL);
		p3.add(male);
		p3.add(female);
		
		JPanel p4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		p4.setBackground(new Color(230,190,230));
		hobbyL = new JLabel("��         ��:");
		hobbyL.setBackground(new Color(230,190,230));
		book = new JCheckBox("����");
		book.setBackground(new Color(230,190,230));
		movie = new JCheckBox("��ȭ");
		movie.setBackground(new Color(230,190,230));
		music = new JCheckBox("����");
		music.setBackground(new Color(230,190,230));
		game = new JCheckBox("����");
		game.setBackground(new Color(230,190,230));
		shop = new JCheckBox("����");
		shop.setBackground(new Color(230,190,230));
		p4.add(hobbyL);
		p4.add(book);
		p4.add(movie);
		p4.add(music);
		p4.add(game);
		p4.add(shop);
		
		JPanel p5 = new JPanel();
		p5.setBackground(new Color(230,190,230));
		enroll = new JButton("���");
		mod = new JButton("����");
		mod.setEnabled(false);
		del = new JButton("����");
		del.setEnabled(false);
		erase = new JButton("�����");
		erase.setEnabled(false);
		p5.add(enroll);
		p5.add(mod);
		p5.add(del);
		p5.add(erase);
		
		JPanel p = new JPanel(new GridLayout(6,1,4,4));
		p.setBackground(new Color(230,190,230));
		p.add(input);
		p.add(p1);
		p.add(p2);
		p.add(p3);
		p.add(p4);
		p.add(p5);
		
		JPanel center = new JPanel(new BorderLayout());
		center.setBackground(new Color(230,190,230));
		center.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		listL = new JLabel("��ü���");
		listL.setHorizontalAlignment(JLabel.CENTER);
//		JScrollPane scroll = new JScrollPane(listL);
		listJ = new JList();
		center.add("North",listL);
		center.add("Center",listJ);
//		center.add("Center",scroll);
		
		JPanel down = new JPanel(new BorderLayout());
		down.setBackground(new Color(230,190,230));
		down.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		infoL = new JLabel("���������м�");
		infoL.setHorizontalAlignment(JLabel.CENTER);
		area = new JTextArea(4,80);
		
		down.add("North",infoL);
		down.add("Center",area);
		
		Container con = this.getContentPane();
		con.setBackground(new Color(230, 230, 190));
		con.setLayout(new BorderLayout());
		
		con.add("West",p);
		con.add("Center",center);
		con.add("South",down);
		
		setBounds(300, 300, 550, 380);
		setVisible(true);
		setBackground(new Color(230,190,230));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private void event() {
		//���,����,����,�����
		enroll.addActionListener(this);
		
	}//event
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==enroll) {
			//�Էµ� ������ ��������
			//FriendDTO - 1�κ� ��� 
			//DB - FriendDAO
			//DB�� ��������
		}
	}//actionPerformed
	
	public static void main(String[] args) {
		new FriendManager().event();
	}//main



}
