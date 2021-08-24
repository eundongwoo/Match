package comment.service;


import java.util.Map;

public class CommentReadRequest {
	
	
	private int articleNum;
	
	
	public CommentReadRequest(int articleNum) {
		super();
		this.articleNum = articleNum;
	}
	
	public int getArticleNum() {
		return articleNum;
	}

	public void setArticleNum(int articleNum) {
		this.articleNum = articleNum;
	}

	
	
}
