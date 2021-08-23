package article.comment.service;


import java.util.Map;

public class CommentWriteRequest {
	private String id;
	private String content;
	private Integer articleNum;
	//private Date date;
	
	public CommentWriteRequest(String id, String content, Integer articleNum) {
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
	
	public Integer getArticleNum() {
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
