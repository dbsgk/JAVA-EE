package friend.action;

import java.awt.BorderLayout;
import java.awt.Button;
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

public class FriendManager extends JFrame implements ActionListener, ListSelectionListener {
	private JLabel nameL, phoneL, sexL, hobbyL,input, d1,d2;
	private JTextField nameT, phone1, phone2;
	private JComboBox phoneC;
	private JRadioButton man, woman;
	private JCheckBox read, movie, music, game, shopping;
	private JButton enroll, modify, delete, erase;
	private JLabel infoL, listL, areaL;
	private JTextArea area;
	private JList<FriendDTO> list;
	private DefaultListModel<FriendDTO> model;
	
	public FriendManager() {
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
		sexL = new JLabel("성         별:");
		man = new JRadioButton("남성", true);
		man.setBackground(new Color(230,190,230));
		woman = new JRadioButton("여성");
		woman.setBackground(new Color(230,190,230));
		ButtonGroup group = new ButtonGroup();
		group.add(man);
		group.add(woman);
		p3.add(sexL);
		p3.add(man);
		p3.add(woman);
		
		JPanel p4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		p4.setBackground(new Color(230,190,230));
		hobbyL = new JLabel("취         미:");
		hobbyL.setBackground(new Color(230,190,230));
		read = new JCheckBox("독서");
		read.setBackground(new Color(230,190,230));
		movie = new JCheckBox("영화");
		movie.setBackground(new Color(230,190,230));
		music = new JCheckBox("음악");
		music.setBackground(new Color(230,190,230));
		game = new JCheckBox("게임");
		game.setBackground(new Color(230,190,230));
		shopping = new JCheckBox("쇼핑");
		shopping.setBackground(new Color(230,190,230));
		p4.add(hobbyL);
		p4.add(read);
		p4.add(movie);
		p4.add(music);
		p4.add(game);
		p4.add(shopping);
		
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
		
		list = new JList(new DefaultListModel<FriendDTO>());
		model = (DefaultListModel<FriendDTO>) list.getModel();
		JScrollPane scroll = new JScrollPane(list);
		
		center.add("North",listL);
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
		
		setBounds(300, 300, 550, 450);
		setVisible(true);
		setBackground(new Color(230,190,230));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//DB에 있는 모든 레코드를 꺼내서 JList에 뿌리기
		FriendDAO friendDAO = FriendDAO.getInstance();
		List<FriendDTO> list2 = friendDAO.getFriendList();
		
		for(FriendDTO friendDTO : list2) {
			model.addElement(friendDTO);
		}
	}
	
	public void event() {
		enroll.addActionListener(this);//등록
		modify.addActionListener(this);//수정
		delete.addActionListener(this);//삭제
		erase.addActionListener(this);//지우기
		
		list.addListSelectionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==enroll) {
			//데이터
			String name = nameT.getText();
			
			String tel1 = (String) phoneC.getSelectedItem();
			String tel2 = phone1.getText();
			String tel3 = phone2.getText();
			
			int gender = 0;
			if(man.isSelected()) gender=0;
			else if(woman.isSelected())gender=1;
						
			int read  = this.read.isSelected() ? 1 : 0;
			int movie  = this.movie.isSelected() ? 1 : 0;
			int music = this.music.isSelected() ? 1 : 0;
			int game  = this.game.isSelected() ? 1 : 0;
			int shopping  = this.shopping.isSelected() ? 1 : 0;
			
			//FriendDTO - 1인분
			FriendDTO friendDTO = new FriendDTO();
			friendDTO.setName(name);
			friendDTO.setTel1(tel1);
			friendDTO.setTel2(tel2);
			friendDTO.setTel3(tel3);
			friendDTO.setGender(gender);
			friendDTO.setRead(read);
			friendDTO.setMovie(movie);
			friendDTO.setMusic(music);
			friendDTO.setGame(game);
			friendDTO.setShopping(shopping);
			
			//DB
			FriendDAO friendDAO = FriendDAO.getInstance();//싱글톤
			
			int seq = friendDAO.getSeq();
			friendDTO.setSeq(seq);
			
			friendDAO.friendInsert(friendDTO);
			
			//결과
			clear();
			area.setText("\n\t 데이터를 등록하였습니다");
			model.addElement(friendDTO);			
		
		}else if(e.getSource()==modify) {
			//데이터
			int seq = list.getSelectedValue().getSeq();
			String name = nameT.getText();
			
			String tel1 = (String) phoneC.getSelectedItem();
			String tel2 = phone1.getText();
			String tel3 = phone2.getText();
			
			int gender = 0;
			if(man.isSelected()) gender=0;
			else if(woman.isSelected())gender=1;
						
			int read  = this.read.isSelected() ? 1 : 0;
			int movie  = this.movie.isSelected() ? 1 : 0;
			int music = this.music.isSelected() ? 1 : 0;
			int game  = this.game.isSelected() ? 1 : 0;
			int shopping  = this.shopping.isSelected() ? 1 : 0;
			
			//FriendDTO - 1인분
			FriendDTO friendDTO = new FriendDTO();
			friendDTO.setSeq(seq);
			friendDTO.setName(name);
			friendDTO.setTel1(tel1);
			friendDTO.setTel2(tel2);
			friendDTO.setTel3(tel3);
			friendDTO.setGender(gender);
			friendDTO.setRead(read);
			friendDTO.setMovie(movie);
			friendDTO.setMusic(music);
			friendDTO.setGame(game);
			friendDTO.setShopping(shopping);
			
			//DB
			FriendDAO friendDAO = FriendDAO.getInstance();//싱글톤
			friendDAO.friendModify(friendDTO);
			
			//결과
			clear();
			area.setText("\n\t 데이터를 수정하였습니다");
			model.set(list.getSelectedIndex(), friendDTO);
			
		}else if(e.getSource()==delete) {
			int seq = list.getSelectedValue().getSeq();
			
			FriendDAO friendDAO = FriendDAO.getInstance();
			friendDAO.friendDelete(seq);
			
			clear();
			area.setText("\n\t 데이터를 삭제하였습니다");
			model.remove(list.getSelectedIndex());
			
		}else if(e.getSource()==erase) {
			clear();
		}
	}
	
	public void clear() {
		nameT.setText("");
		phoneC.setSelectedItem("010");
		phone1.setText("");
		phone2.setText("");
		man.setSelected(true);
		read.setSelected(false);
		movie.setSelected(false);
		music.setSelected(false);
		game.setSelected(false);
		shopping.setSelected(false);
		area.setText("");
		
		enroll.setEnabled(true);
		modify.setEnabled(false);
		delete.setEnabled(false);
		erase.setEnabled(false);
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		//list.setSelectedIndex(0);
		System.out.println(list.getSelectedIndex());
		if(list.getSelectedIndex()==-1) return;
		
		FriendDTO friendDTO = list.getSelectedValue();
		nameT.setText(friendDTO.getName());
		phoneC.setSelectedItem(friendDTO.getTel1());
		phone1.setText(friendDTO.getTel2());
		phone2.setText(friendDTO.getTel3());
		if(friendDTO.getGender()==0)
			man.setSelected(true);
		else if(friendDTO.getGender()==1)
			woman.setSelected(true);
		read.setSelected(friendDTO.getRead()==1);
		movie.setSelected(friendDTO.getMovie()==1);
		music.setSelected(friendDTO.getMusic()==1);
		game.setSelected(friendDTO.getGame()==1);
		shopping.setSelected(friendDTO.getShopping()==1);
		
		area.setText("");
		
		enroll.setEnabled(false);
		modify.setEnabled(true);
		delete.setEnabled(true);
		erase.setEnabled(true);
	}
	
	public static void main(String[] args) {
		new FriendManager().event();
	}
}















