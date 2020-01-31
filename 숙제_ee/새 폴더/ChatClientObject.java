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
		send = new JButton("����");
		
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
				//������ �����ϱ��������� �����ؼ��� �ȵȴ�
				if(oos==null || ois==null)
					System.exit(0);
				
				InfoDTO dto = new InfoDTO();
				dto.setCommand(Info.EXIT);
				
				try {
					oos.writeObject(dto);//���������� �����ϰڴٴ� �޼����� ����
					oos.flush();
				} catch (IOException io) {
					io.printStackTrace();
				}
				
			}
		});
	}
	
	private void service() {
		//����IP
		String serverIP = JOptionPane.showInputDialog(this,"����IP�� �Է��ϼ���","192.168.0.");
		if(serverIP==null || serverIP.length()==0){
			System.out.println("����IP�� �Էµ��� �ʾҽ��ϴ�");
			System.exit(0);
		}
		
		//�г���
		String nickName = JOptionPane.showInputDialog(this,"�г����� �Է��ϼ���","�г���",JOptionPane.INFORMATION_MESSAGE);
		if(nickName==null || nickName.length()==0){
			nickName="guest";
		}
		
		try {
			socket = new Socket(serverIP, 9500);
			
			oos = new ObjectOutputStream(socket.getOutputStream()); //��½�Ʈ���� ���� ����������Ѵ�
			ois = new ObjectInputStream(socket.getInputStream());
			
			//�г����� ������ ������
			InfoDTO dto = new InfoDTO();
			dto.setNickName(nickName);
			dto.setCommand(Info.JOIN);
			oos.writeObject(dto);
			oos.flush();
			
		} catch (UnknownHostException e) {
			System.out.println("������ ã�� �� �����ϴ�");
			e.printStackTrace();
			System.exit(0);
		} catch (IOException e) {
			System.out.println("������ ������ �ȵǾ����ϴ�");
			e.printStackTrace();
			System.exit(0);
		}
				
		//������ ����
		Thread t = new Thread(this);
		t.start();
		
		//�̺�Ʈ
		send.addActionListener(this);
		input.addActionListener(this);
	}

	@Override
	public void run() {
		//�����κ��� �޴� ��
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
		//������ ������ ��
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

















