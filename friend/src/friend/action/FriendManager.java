package friend.action;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import friend.bean.FriendDTO;
import friend.dao.FriendDAO;

public class FriendManager extends JFrame implements ActionListener{
	private JLabel nameL, phoneL, genderL, hobbyL,input, d1,d2;
	private JTextField nameT, phone1, phone2;
	private JComboBox phoneC;
	private JRadioButton male, female;
	private JCheckBox book, movie, music, game, shop;
	private JButton enroll, modify, delete, erase;
	private JLabel infoL, listL, areaL;
	private JTextArea area;
	private JList list;
	private DefaultListModel<FriendDTO> model;
	private FriendDAO dao;
	private List<FriendDTO> list2;
	private int selectedIndex;
	public FriendManager() {
		dao = FriendDAO.getInstance();//�̱���
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
		genderL = new JLabel("��         ��:");
		male = new JRadioButton("����");
		male.setBackground(new Color(230,190,230));
		female = new JRadioButton("����");
		female.setBackground(new Color(230,190,230));
		ButtonGroup group = new ButtonGroup();
		group.add(male);
		group.add(female);
		p3.add(genderL);
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
		modify = new JButton("����");
		modify.setEnabled(false);
		delete = new JButton("����");
		delete.setEnabled(false);
		erase = new JButton("�����");
		erase.setEnabled(false);
		p5.add(enroll);
		p5.add(modify);
		p5.add(delete);
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
		model = new DefaultListModel<FriendDTO>();
		list = new JList(model);
		center.add("North",listL);
//		center.add("Center",listJ);
		JScrollPane scroll = new JScrollPane(list);
		center.add("Center",scroll);
		
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
		
		//DB�� �ִ� ��� ���ڵ带 ������ JList�� �Ѹ���
		list2 = dao.getList();
		System.out.println(list2);
		for(FriendDTO data: list2) {
			model.addElement(data);
		}
		System.out.println("for�ڿ�");
		list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                if (!arg0.getValueIsAdjusting()) {
                	selectedIndex = list.getSelectedIndex();
                	if(selectedIndex==-1)return;
                	nameT.setText(list2.get(selectedIndex).getName());
                	phoneC.setSelectedItem(list2.get(selectedIndex).getTel1());
            		phone1.setText(list2.get(selectedIndex).getTel2());
            		phone2.setText(list2.get(selectedIndex).getTel3());
            		male.setSelected(list2.get(selectedIndex).getGender()==0?true:false);
            		female.setSelected(list2.get(selectedIndex).getGender()==1?true:false);
            		book.setSelected(list2.get(selectedIndex).getRead()==1?true:false);
            		movie.setSelected(list2.get(selectedIndex).getMovie()==1?true:false);
            		music.setSelected(list2.get(selectedIndex).getMusic()==1?true:false);
            		game.setSelected(list2.get(selectedIndex).getGame()==1?true:false);
            		shop.setSelected(list2.get(selectedIndex).getShop()==1?true:false);
                }
                enroll.setEnabled(false);
                modify.setEnabled(true);
                delete.setEnabled(true);
                erase.setEnabled(true);
            }
        });
	}
	
	private void event() {
		//���,����,����,�����
		enroll.addActionListener(this);
		modify.addActionListener(this);
		delete.addActionListener(this);
		erase.addActionListener(this);
		
	}//event
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==enroll) {
			//�Էµ� ������ ��������
			//FriendDTO - 1�κ� ��� 
			FriendDTO dto = new FriendDTO();
			if(nameT.getText()==null || nameT.getText().equals(""))return;
			dto.setName(nameT.getText());
			dto.setTel1((String)phoneC.getSelectedItem());
			dto.setTel2((phone1.getText()));
			dto.setTel3((phone2.getText()));
			dto.setGender(male.isSelected()?0:1);//���ڸ� 0, ���ڸ� 1
			dto.setRead(book.isSelected()?1:0);//���� 1, ���þȵǸ� 0
			dto.setMovie(movie.isSelected()?1:0);
			dto.setMusic(music.isSelected()?1:0);
			dto.setGame(game.isSelected()?1:0);
			dto.setShop(shop.isSelected()?1:0);
			//DB - FriendDAO
			
			int seq = dao.getSeq();
			dto.setSeq(seq);
			
			//DB�� ��������
			dao.insert(dto);
			clear();
			//���
			area.setText("\n\t �����͸� ����Ͽ����ϴ�.");
			model.addElement(dto);
			
		}//enroll
		else if(e.getSource()==modify) {
			list2.get(selectedIndex).setName(nameT.getText());
			list2.get(selectedIndex).setTel1((String)phoneC.getSelectedItem());
			list2.get(selectedIndex).setTel2((String)phone1.getText());
			list2.get(selectedIndex).setTel3((String)phone2.getText());
			list2.get(selectedIndex).setGender(male.isSelected()?0:1);
			list2.get(selectedIndex).setRead(book.isSelected()?1:0);
			list2.get(selectedIndex).setMovie(movie.isSelected()?1:0);
			list2.get(selectedIndex).setMusic(music.isSelected()?1:0);
			list2.get(selectedIndex).setGame(game.isSelected()?1:0);
			list2.get(selectedIndex).setShop(shop.isSelected()?1:0);
			//DB�� ������
			dao.modify(list2.get(selectedIndex));
			//���
			area.setText("\n\t �����͸� �����Ͽ����ϴ�.");
			
		}else if(e.getSource()==delete) {
			dao.delete(list2.get(selectedIndex));
			area.setText("\n\t �����͸� �����Ͽ����ϴ�.");
			model.removeAllElements();
			for(FriendDTO data: list2) {
				model.addElement(data);
			}
		}else if(e.getSource()==erase) {
			clear();
			enroll.setEnabled(true);
			modify.setEnabled(false);
            delete.setEnabled(false);
            erase.setEnabled(false);
		}
	}//actionPerformed
	
	private void clear() {
		nameT.setText("");
		phoneC.setSelectedItem("010");
		phone1.setText("");
		phone2.setText("");
		male.setSelected(true);
		female.setSelected(false);
		
		book.setSelected(false);
		movie.setSelected(false);
		music.setSelected(false);
		game.setSelected(false);
		shop.setSelected(false);
		
	}

	public static void main(String[] args) {
		new FriendManager().event();
	}//main

}