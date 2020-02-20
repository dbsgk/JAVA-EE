package board.bean;

import java.util.Date;

public class BoardDTO {

	private int seq;
	private String id;
	private String name;
	private String email;
	private String subject;
	private String content;
	
	private int ref;
	private int lev = 0;
	private int step = 0;
	private int pseq = 0;
	private int reply = 0;
	
	private int hit = 0;
	private Date logtime;
	
	// constructor
	public BoardDTO() {
	}
	
	public BoardDTO(int seq, String id, String name, String email, String subject, String content, int ref, int lev,
			int step, int pseq, int reply, int hit, Date logtime) {
		super();
		this.seq = seq;
		this.id = id;
		this.name = name;
		this.email = email;
		this.subject = subject;
		this.content = content;
		this.ref = ref;
		this.lev = lev;
		this.step = step;
		this.pseq = pseq;
		this.reply = reply;
		this.hit = hit;
		this.logtime = logtime;
	}
	
	public BoardDTO(String id, String name, String email, String subject, String content, int ref) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.subject = subject;
		this.content = content;
		this.ref = ref;
	}

	// getter & setter
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public int getLev() {
		return lev;
	}
	public void setLev(int lev) {
		this.lev = lev;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public int getPseq() {
		return pseq;
	}
	public void setPseq(int pseq) {
		this.pseq = pseq;
	}
	public int getReply() {
		return reply;
	}
	public void setReply(int reply) {
		this.reply = reply;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public Date getLogtime() {
		return logtime;
	}
	public void setLogtime(Date logtime) {
		this.logtime = logtime;
	}
	
	
	
}


//CREATE TABLE board(
//	     seq NUMBER NOT NULL,               -- 글번호
//	     id VARCHAR2(20) NOT NULL,           -- 아이디
//	     name VARCHAR2(40) NOT NULL,       -- 이름
//	     email VARCHAR2(40),                  -- 이메일
//	     subject VARCHAR2(255) NOT NULL,    -- 제목
//	     content VARCHAR2(4000) NOT NULL,   -- 내용 
//
//	     ref NUMBER NOT NULL,                 -- 그룹번호
//	     lev NUMBER DEFAULT 0 NOT NULL,     -- 단계
//	     step NUMBER DEFAULT 0 NOT NULL,    -- 글순서
//	     pseq NUMBER DEFAULT 0 NOT NULL,    -- 원글번호
//	     reply NUMBER DEFAULT 0 NOT NULL,   -- 답변수
//
//	     hit NUMBER DEFAULT 0,              -- 조회수
//	     logtime DATE DEFAULT SYSDATE
//	 );