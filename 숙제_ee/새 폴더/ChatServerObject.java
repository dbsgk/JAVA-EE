package network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServerObject {
	private ServerSocket serverSocket;
	private List<ChatHandlerObject> list;
	
	public ChatServerObject() {
		try {
			serverSocket = new ServerSocket(9500);
			System.out.println("서버준비완료..");
			
			list = new ArrayList<ChatHandlerObject>();
			
			while(true) {
				Socket socket = serverSocket.accept();//낚아챈다
				
				ChatHandlerObject handler = new ChatHandlerObject(socket, list);//스레드 생성
				handler.start();
				
				list.add(handler);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args){
		new ChatServerObject();	
	}
}
