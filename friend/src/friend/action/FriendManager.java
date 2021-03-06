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
		dao = FriendDAO.getInstance();//싱글톤
		input = new JLabel("개인정보입력");
		input.setHorizontalAlignment(JLabel.CENTER);
		input.setBackground(new Color(230,190,230));
		JPanel p1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		p1.setBackground(new Color(230,190,230));
		nameL = new JLabel("이        름:");
		nameT = new JTextField(7);
		
		p1.add(nameL);
		p1.add(nameT);
		
		JPanel p2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		p2.setBackground(new Color(230,190,230));
		phoneL = new JLabel("전화번호:");
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
		genderL = new JLabel("성         별:");
		male = new JRadioButton("남성");
		male.setBackground(new Color(230,190,230));
		female = new JRadioButton("여성");
		female.setBackground(new Color(230,190,230));
		ButtonGroup group = new ButtonGroup();
		group.add(male);
		group.add(female);
		p3.add(genderL);
		p3.add(male);
		p3.add(female);
		
		JPanel p4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		p4.setBackground(new Color(230,190,230));
		hobbyL = new JLabel("취         미:");
		hobbyL.setBackground(new Color(230,190,230));
		book = new JCheckBox("독서");
		book.setBackground(new Color(230,190,230));
		movie = new JCheckBox("영화");
		movie.setBackground(new Color(230,190,230));
		music = new JCheckBox("음악");
		music.setBackground(new Color(230,190,230));
		game = new JCheckBox("게임");
		game.setBackground(new Color(230,190,230));
		shop = new JCheckBox("쇼핑");
		shop.setBackground(new Color(230,190,230));
		p4.add(hobbyL);
		p4.add(book);
		p4.add(movie);
		p4.add(music);
		p4.add(game);
		p4.add(shop);
		
		JPanel p5 = new JPanel();
		p5.setBackground(new Color(230,190,230));
		enroll = new JButton("등록");
		modify = new JButton("수정");
		modify.setEnabled(false);
		delete = new JButton("삭제");
		delete.setEnabled(false);
		erase = new JButton("지우기");
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
		listL = new JLabel("전체목록");
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
		infoL = new JLabel("개인정보분석");
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
		
		//DB에 있는 모든 레코드를 꺼내서 JList에 뿌리기
		list2 = dao.getList();
		System.out.println(list2);
		for(FriendDTO data: list2) {
			model.addElement(data);
		}
		System.out.println("for뒤에");
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
            		male.setSelected(list2.get(selectedIndex).getGender()==0);// = list2.get(selectedIndex).getGender()==0?true:false
            		female.setSelected(list2.get(selectedIndex).getGender()==1);//어차피 1이면 true가 들어갈거고 아니면 false들어갈거라서 뒤에 삭제.
            		book.setSelected(list2.get(selectedIndex).getRead()==1);
            		movie.setSelected(list2.get(selectedIndex).getMovie()==1);
            		music.setSelected(list2.get(selectedIndex).getMusic()==1);
            		game.setSelected(list2.get(selectedIndex).getGame()==1);
            		shop.setSelected(list2.get(selectedIndex).getShop()==1);
                }
                area.setText("");
                
                enroll.setEnabled(false);
                modify.setEnabled(true);
                delete.setEnabled(true);
                erase.setEnabled(true);
            }
        });
	}
	
	private void event() {
		//등록,수정,삭제,지우기
		enroll.addActionListener(this);
		modify.addActionListener(this);
		delete.addActionListener(this);
		erase.addActionListener(this);
		
	}//event
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==enroll) {
			//입력된 데이터 가져오기
			//FriendDTO - 1인분 담기 
			FriendDTO dto = new FriendDTO();
			if(nameT.getText()==null || nameT.getText().equals(""))return;
			dto.setName(nameT.getText());
			dto.setTel1((String)phoneC.getSelectedItem());
			dto.setTel2((phone1.getText()));
			dto.setTel3((phone2.getText()));
			dto.setGender(male.isSelected()?0:1);//남자면 0, 여자면 1
			dto.setRead(book.isSelected()?1:0);//선택 1, 선택안되면 0
			dto.setMovie(movie.isSelected()?1:0);
			dto.setMusic(music.isSelected()?1:0);
			dto.setGame(game.isSelected()?1:0);
			dto.setShop(shop.isSelected()?1:0);
			//DB - FriendDAO
			
			int seq = dao.getSeq();
			dto.setSeq(seq);
			
			//DB로 가져가기
			dao.insert(dto);
			clear();
			//결과
			area.setText("\n\t 데이터를 등록하였습니다.");
			list2.add(dto);
			model.addElement(dto);
			
		}//enroll
		else if(e.getSource()==modify) {
			if(nameT.getText()==null || nameT.getText().equals(""))return;
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
			//DB로 보내기
			dao.modify(list2.get(selectedIndex));
			//결과
			model.removeAllElements();
			for(FriendDTO data: list2) {
				model.addElement(data);
			}
			area.setText("\n\t 데이터를 수정하였습니다.");
			
		}else if(e.getSource()==delete) {
			dao.delete(list2.get(selectedIndex));
			list2.remove(selectedIndex);
			area.setText("\n\t 데이터를 삭제하였습니다.");
			
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
