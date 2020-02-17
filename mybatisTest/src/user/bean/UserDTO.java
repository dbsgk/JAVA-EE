package user.bean;

import lombok.Data;

@Data
public class UserDTO {
	public UserDTO(String name, String id, String pwd) {
		this.name = name;
		this.id = id;
		this.pwd = pwd;
	}
	private String name;
	private String id;
	private String pwd;
}
