package notice.model;

import java.util.Date;

import article.model.Writer;

public class Notice {
	
	private Integer number;
	private Writer writer;
	private String title;
	private Date regDate;
	private Date modifiedDate;
	private String content;
	private int readCount;
	
	public Integer getNumber() {
		return number;
	}
	public Writer getWriter() {
		return writer;
	}
	public String getTitle() {
		return title;
	}
	public Date getRegDate() {
		return regDate;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public int getReadCount() {
		return readCount;
	}
	public String getContent() {
		return content;
	}
	
	public Notice(Integer number, Writer writer, String title, Date regDate, Date modifiedDate, String content, int readCount) {
		super();
		this.number = number;
		this.writer = writer;
		this.title = title;
		this.regDate = regDate;
		this.modifiedDate = modifiedDate;
		this.readCount = readCount;
		this.content = content;
	}
	
	
}
