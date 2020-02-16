package imageboard.bean;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ImageboardDTO {

	private int seq;
	private String imageId;
	private String imageName;
	private int imagePrice;
	private int imageQty;
	private String imageContent;
	private String image1;
	private Date logtime;
	
	public ImageboardDTO() {
		
	}

	public ImageboardDTO(String imageId, String imageName, int imagePrice, int imageQty, String imageContent,
			String image1) {
		super();
		this.imageId = imageId;
		this.imageName = imageName;
		this.imagePrice = imagePrice;
		this.imageQty = imageQty;
		this.imageContent = imageContent;
		this.image1 = image1;
	}
	
}


//seq NUMBER PRIMARY KEY,               
//imageId VARCHAR2(20) NOT NULL,     -- 상품코드  
//imageName VARCHAR2(40) NOT NULL, -- 상품명
//imagePrice  NUMBER NOT NULL,      -- 단가
//imageQty    NUMBER NOT NULL,      -- 개수
//imageContent VARCHAR2(4000) NOT NULL,      
//image1 varchar2(200),   
//logtime DATE DEFAULT SYSDATE
