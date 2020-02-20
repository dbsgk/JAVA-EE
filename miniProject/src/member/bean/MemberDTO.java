package member.bean;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MemberDTO {

	private String name;
	private String id;
	private String pwd;
	private String gender;
	private String email1;
	private String email2;
	private String tel1;
	private String tel2;
	private String tel3;
	private String zipcode;
	private String addr1;
	private String addr2;
	private Date logtime;
	
	public MemberDTO(String name, String id, String pwd, String gender, String email1, String email2,
			String tel1, String tel2, String tel3, String zipcode, String addr1, String addr2, Date logtime) {
		super();
		this.name = name;
		this.id = id;
		this.pwd = pwd;
		this.gender = gender;
		this.email1 = email1;
		this.email2 = email2;
		this.tel1 = tel1;
		this.tel2 = tel2;
		this.tel3 = tel3;
		this.zipcode = zipcode;
		this.addr1 = addr1;
		this.addr2 = addr2;
		this.logtime = logtime;
	}
	public MemberDTO(String name, String id, String pwd, String gender, String email1, String email2, String tel1,
			String tel2, String tel3, String zipcode, String addr1, String addr2) {
		super();
		this.name = name;
		this.id = id;
		this.pwd = pwd;
		this.gender = gender;
		this.email1 = email1;
		this.email2 = email2;
		this.tel1 = tel1;
		this.tel2 = tel2;
		this.tel3 = tel3;
		this.zipcode = zipcode;
		this.addr1 = addr1;
		this.addr2 = addr2;
	}
	
	public MemberDTO() {
		// TODO Auto-generated constructor stub
	}
	
}
