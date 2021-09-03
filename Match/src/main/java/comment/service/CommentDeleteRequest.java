package comment.service;

public class CommentDeleteRequest {

	private int articleNum;
	private int commentNum;
	public CommentDeleteRequest(int articleNum, int commentNum) {
		
		this.articleNum = articleNum;
		this.commentNum = commentNum;
	}
	
	public int getArticleNum() {
		return articleNum;
	}
	public void setArticleNum(int articleNum) {
		this.articleNum = articleNum;
	}
	public int getCommentNum() {
		return commentNum;
	}
	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}
	
	
	
	
}
