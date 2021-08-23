package comment.service;


import java.util.Map;

public class CommentWriteRequest {
	private String id;
	private String content;
	private int articleNum;
	//private Date date;
	
	public CommentWriteRequest(String id, String content, int articleNum) {
		super();
		this.id = id;
		this.content = content;
		this.articleNum = articleNum;
	}

	public String getId() {
		return id;
	}

	public String getContent() {
		return content;
	}
	
	public int getArticleNum() {
		return articleNum;
	}
	
//	public Date getDate() {
//		return date;
//	}
	
	public void validate(Map<String, Boolean> errors) {
		if(content==null || content.trim().isEmpty()	) {
			errors.put("content", Boolean.TRUE);
		}
	}
}
