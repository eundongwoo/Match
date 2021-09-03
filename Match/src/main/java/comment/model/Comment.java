package comment.model;

import java.util.Date;

public class Comment {
	private int comment_num; //댓글 글번호
	private String id;       //댓글 작성자
	private Date comment_date; //댓글 작성일
	private int comment_article; //게시글 번호
	
	private String comment_content; //댓글 내용
	
	public Comment(Integer comment_num, String id, Date comment_date, int comment_article,
			String comment_content) {
		super();
		this.comment_num = comment_num;
		this.id = id;
		this.comment_date = comment_date;
		this.comment_article = comment_article;
		
		this.comment_content = comment_content;
	}

	public int getComment_num() {
		return comment_num;
	}

	public String getId() {
		return id;
	}

	public Date getComment_date() {
		return comment_date;
	}

	public int getComment_article() {
		return comment_article;
	}

	

	public String getComment_content() {
		return comment_content;
	}

	public void setComment_num(int comment_num) {
		this.comment_num = comment_num;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setComment_date(Date comment_date) {
		this.comment_date = comment_date;
	}

	public void setComment_article(int comment_article) {
		this.comment_article = comment_article;
	}

	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}	
}
