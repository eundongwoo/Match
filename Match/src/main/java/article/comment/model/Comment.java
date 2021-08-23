package article.comment.model;

import java.util.Date;

public class Comment {
	private Integer comment_num; //댓글 글번호
	private String id;       //댓글 작성자
	private Date comment_date; //댓글 작성일
	private int comment_article; //게시글 번호
	private int comment_parent; //부모글
	private String comment_content; //댓글 내용
//	private int comment_level; //댓글- 답변글
	
	public Comment(Integer comment_num, String id, Date comment_date, int comment_article, int comment_parent,
			String comment_content) {
		super();
		this.comment_num = comment_num;
		this.id = id;
		this.comment_date = comment_date;
		this.comment_article = comment_article;
		this.comment_parent = comment_parent;
		this.comment_content = comment_content;
	}

	public Integer getComment_num() {
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

	public int getComment_parent() {
		return comment_parent;
	}

	public String getComment_content() {
		return comment_content;
	}
	
	

//	public int getComment_level() {
//		return comment_level;
//	}
	
}
