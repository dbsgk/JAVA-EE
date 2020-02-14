package member.bean;

import lombok.Data;

@Data
public class AddrDTO {
	
	private String zipcode;
	private String sido;
	private String sigungu;
	private String yubmyundong;
	private String ri;
	private String roadname;
	private String buildingname;

	public AddrDTO(String zipcode, String sido, String sigungu, String yubmyundong, String ri, String roadname,
			String buildingname) {
		super();
		this.zipcode = zipcode;
		this.sido = sido;
		this.sigungu = sigungu;
		this.yubmyundong = yubmyundong;
		this.ri = ri;
		this.roadname = roadname;
		this.buildingname = buildingname;
	}

}
