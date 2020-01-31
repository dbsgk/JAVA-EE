package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class ChatHandlerObject extends Thread {
	private Socket socket;
	private List<ChatHandlerObject> list;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	
	public ChatHandlerObject(Socket socket, List<ChatHandlerObject> list) throws IOException {
		this.socket = socket;
		this.list = list;
		
		oos = new ObjectOutputStream(socket.getOutputStream());
		ois = new ObjectInputStream(socket.getInputStream());
	}
	
	@Override
	public void run() {
		InfoDTO dto = null;
		String nickName = null;
		
		while(true) {
			//�޴� ��
			try {
				dto = (InfoDTO) ois.readObject();
				
				if(dto.getCommand()==Info.JOIN){
					nickName = dto.getNickName();
					
					//��� Ŭ���̾�Ʈ���� �޼��� ������
					InfoDTO sendDTO = new InfoDTO();
					sendDTO.setCommand(Info.SEND);
					sendDTO.setMsg(nickName+"���� �����Ͽ����ϴ�");
					broadcast(sendDTO);
					
				}else if(dto.getCommand()==Info.EXIT) {
					InfoDTO sendDTO = new InfoDTO();
					
					//Ŭ���̾�Ʈ�� ���� quit�� �޾�����
					sendDTO.setCommand(Info.EXIT);
					oos.writeObject(sendDTO);
					oos.flush();

					ois.close();
					oos.close();
					socket.close();
					
					//���� Ŭ���̾�Ʈ���� ���� �޼��� ������
					list.remove(this);
					
					sendDTO.setCommand(Info.SEND);
					sendDTO.setMsg(nickName+"���� �����Ͽ����ϴ�");
					broadcast(sendDTO);
					
					break;
					
				}else if(dto.getCommand()==Info.SEND) {
					InfoDTO sendDTO = new InfoDTO();
					sendDTO.setCommand(Info.SEND);
					sendDTO.setMsg("["+nickName+"] "+dto.getMsg());
					broadcast(sendDTO);
				}
			
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}//while
	}

	private void broadcast(InfoDTO sendDTO) {
		for(ChatHandlerObject handler : list){
			try {
				handler.oos.writeObject(sendDTO);
				handler.oos.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}//for		
	}
}

























