package comment.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import article.model.Article;
import comment.model.Comment;
import comment.service.CommentReadRequest;
import jdbc.JdbcUtil;

public class CommentDao {
	
	public HashMap<String, Object> insert(Connection conn, Comment comment) throws SQLException{
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		HashMap<String, Object> hm = new HashMap<>();		//hm객체 -> 여기에 result와 comments넣을 거임
		
		try {//insert
			pstmt = conn.prepareStatement("insert into article_comment values (article_comment_seq.nextval,?,?,?,?)");
			pstmt.setString(1, comment.getId());
			pstmt.setTimestamp(2, toTimestamp(comment.getComment_date()));
			pstmt.setInt(3, comment.getComment_article()); //article의 article_no를 가져와야 하나?			                                   
			pstmt.setString(4, comment.getComment_content());
			
			int insertedCount = pstmt.executeUpdate();		//insertedCount 행 몇개 추가 했는지 반환.
			System.out.println("insertedCount : "+insertedCount);
			
			List<Comment> comments = commentList(conn,comment.getComment_article(), 10);
			
			hm.put("result", insertedCount);		
			hm.put("comments", comments);		//hashmap에 comments리스트 넣어줌.
			
//			if(insertedCount >0) {
//				stmt = conn.createStatement();
//				rs = stmt.executeQuery("select ");
//				if(rs.next()) {
//					Integer newNum = rs.getInt(1);
//					return new Comment(newNum, comment.getId(), comment.getComment_date(), 
//							comment.getComment_article(), comment.getComment_parent(), comment.getComment_content() 
//							);
//				}
//			}
			return hm;
	
		}finally {
		
			JdbcUtil.close(pstmt);
		}
	}

	private Timestamp toTimestamp(Date date) {
		return new Timestamp(date.getTime());
	}
	
	//commentList comments반환
	public List<Comment> commentList(Connection conn, int ArticleNum, int pagesize) throws SQLException{
		ArrayList<Comment> list = new ArrayList<Comment>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Comment> comments= new ArrayList<Comment>();
		
		try {
			String sql="select comment_num, id, comment_date, comment_article, comment_content from article_comment";
			pstmt = conn.prepareStatement(sql);		
			rs=pstmt.executeQuery();
			while(rs.next()) {		//comment객체에 값 넣어준다. -> 반복
				Comment comment = new Comment(pagesize, sql, null, pagesize, sql);
				comment.setComment_num(rs.getInt(1));
				comment.setId(rs.getString(2));
				comment.setComment_date(rs.getDate(3)); //보류 
				comment.setComment_article(rs.getInt(4));
				comment.setComment_content(rs.getString(5));					
				comments.add(comment);		//comments리스트에 comment객체 넣어줌
									
			}
			
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return comments;		//comments(comment객체들 가지고 있는) 리스트 반환
	}

	public HashMap<String, Object> read(Connection conn, CommentReadRequest readReq, int pagesize) throws SQLException {	//read
		ArrayList<Comment> list = new ArrayList<Comment>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Comment> comments= new ArrayList<Comment>();
		HashMap<String, Object> hm = new HashMap<>();	
		
		try {
			String sql="select comment_num, id, comment_date, comment_article, comment_content from article_comment where comment_article=?";
			pstmt = conn.prepareStatement(sql);		
			pstmt.setInt(1, readReq.getArticleNum());
			rs=pstmt.executeQuery();
			while(rs.next()) {		//comment객체에 값 넣어준다. -> 반복
				Comment comment = new Comment(pagesize, sql, null, pagesize, sql);
				comment.setComment_num(rs.getInt(1));
				comment.setId(rs.getString(2));
				comment.setComment_date(rs.getDate(3)); //보류 
				comment.setComment_article(rs.getInt(4));
				comment.setComment_content(rs.getString(5));				
				comments.add(comment);		//comments리스트에 comment객체 넣어줌
									
			}
			
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		//hm.put("result", insertedCount);		
		hm.put("comments", comments);	
		return hm;		//comments(comment객체들 가지고 있는) 리스트 반환
		
		
		
	}
	
	
	
}
