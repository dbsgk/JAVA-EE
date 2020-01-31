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
			//받는 쪽
			try {
				dto = (InfoDTO) ois.readObject();
				
				if(dto.getCommand()==Info.JOIN){
					nickName = dto.getNickName();
					
					//모든 클라이언트에게 메세지 보내기
					InfoDTO sendDTO = new InfoDTO();
					sendDTO.setCommand(Info.SEND);
					sendDTO.setMsg(nickName+"님이 입장하였습니다");
					broadcast(sendDTO);
					
				}else if(dto.getCommand()==Info.EXIT) {
					InfoDTO sendDTO = new InfoDTO();
					
					//클라이언트로 부터 quit를 받았을때
					sendDTO.setCommand(Info.EXIT);
					oos.writeObject(sendDTO);
					oos.flush();

					ois.close();
					oos.close();
					socket.close();
					
					//남은 클라이언트에게 퇴장 메세지 보내기
					list.remove(this);
					
					sendDTO.setCommand(Info.SEND);
					sendDTO.setMsg(nickName+"님이 퇴장하였습니다");
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

























