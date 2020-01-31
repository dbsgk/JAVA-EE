package network;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class ChatClientObject extends JFrame implements ActionListener, Runnable {
	private JTextArea output;
	private JTextField input;
	private JButton send;
	private Socket socket;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	
	public ChatClientObject() {
		output = new JTextArea();
		JScrollPane scroll = new JScrollPane(output);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		output.setEditable(false);
		
		input = new JTextField();
		send = new JButton("전송");
		
		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());
		p.add("Center", input);
		p.add("East", send);
		
		Container con = this.getContentPane();
		con.add("Center", scroll);
		con.add("South",p);
		
		setBounds(900,100,300,300);
		setVisible(true);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				//서버가 응답하기전까지는 종료해서는 안된다
				if(oos==null || ois==null)
					System.exit(0);
				
				InfoDTO dto = new InfoDTO();
				dto.setCommand(Info.EXIT);
				
				try {
					oos.writeObject(dto);//서버측으로 종료하겠다는 메세지를 전송
					oos.flush();
				} catch (IOException io) {
					io.printStackTrace();
				}
				
			}
		});
	}
	
	private void service() {
		//서버IP
		String serverIP = JOptionPane.showInputDialog(this,"서버IP를 입력하세요","192.168.0.");
		if(serverIP==null || serverIP.length()==0){
			System.out.println("서버IP가 입력되지 않았습니다");
			System.exit(0);
		}
		
		//닉네임
		String nickName = JOptionPane.showInputDialog(this,"닉네임을 입력하세요","닉네임",JOptionPane.INFORMATION_MESSAGE);
		if(nickName==null || nickName.length()==0){
			nickName="guest";
		}
		
		try {
			socket = new Socket(serverIP, 9500);
			
			oos = new ObjectOutputStream(socket.getOutputStream()); //출력스트림을 먼저 생성해줘야한다
			ois = new ObjectInputStream(socket.getInputStream());
			
			//닉네임을 서버로 보내기
			InfoDTO dto = new InfoDTO();
			dto.setNickName(nickName);
			dto.setCommand(Info.JOIN);
			oos.writeObject(dto);
			oos.flush();
			
		} catch (UnknownHostException e) {
			System.out.println("서버를 찾을 수 없습니다");
			e.printStackTrace();
			System.exit(0);
		} catch (IOException e) {
			System.out.println("서버와 연결이 안되었습니다");
			e.printStackTrace();
			System.exit(0);
		}
				
		//스레드 생성
		Thread t = new Thread(this);
		t.start();
		
		//이벤트
		send.addActionListener(this);
		input.addActionListener(this);
	}

	@Override
	public void run() {
		//서버로부터 받는 쪽
		InfoDTO dto = null;
		
		while(true) {
			try {
				dto = (InfoDTO) ois.readObject();
			
				if(dto.getCommand() == Info.EXIT) {
					ois.close();
					oos.close();
					socket.close();
					
					System.exit(0);
				}
				
				if(dto.getCommand() == Info.SEND) {
					output.append(dto.getMsg()+"\n");
					
					int pos = output.getText().length();
					output.setCaretPosition(pos);
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//서버로 보내는 쪽
		String msg = input.getText();
		
		InfoDTO dto = new InfoDTO();
		if(msg.toLowerCase().equals("exit")) {
			dto.setCommand(Info.EXIT);
			
		}else {
			dto.setCommand(Info.SEND);
			dto.setMsg(msg);
		}
		
		try {
			oos.writeObject(dto);
			oos.flush();
		} catch (IOException io) {
			io.printStackTrace();
		}
		
		input.setText("");
	}
	
	public static void main(String[] args) {
		new ChatClientObject().service();
	}
}

















