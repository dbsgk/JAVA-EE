package member.bean;

import java.util.Date;

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
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail1() {
		return email1;
	}
	public void setEmail1(String email1) {
		this.email1 = email1;
	}
	public String getEmail2() {
		return email2;
	}
	public void setEmail2(String email2) {
		this.email2 = email2;
	}
	public String getTel1() {
		return tel1;
	}
	public void setTel1(String tel1) {
		this.tel1 = tel1;
	}
	public String getTel2() {
		return tel2;
	}
	public void setTel2(String tel2) {
		this.tel2 = tel2;
	}
	public String getTel3() {
		return tel3;
	}
	public void setTel3(String tel3) {
		this.tel3 = tel3;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public Date getLogtime() {
		return logtime;
	}
	public void setLogtime(Date logtime) {
		this.logtime = logtime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
