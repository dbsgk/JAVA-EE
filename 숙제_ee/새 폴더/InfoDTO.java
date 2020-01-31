package network;

import java.io.Serializable;

enum Info{
	JOIN, EXIT, SEND
}

public class InfoDTO implements Serializable{
	private String nickName;
	private String msg;
	private Info command;
	
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Info getCommand() {
		return command;
	}
	public void setCommand(Info command) {
		this.command = command;
	}
}
