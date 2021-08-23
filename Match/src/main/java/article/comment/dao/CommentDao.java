package article.comment.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import article.comment.model.Comment;
import article.model.Article;
import jdbc.JdbcUtil;

public class CommentDao {
	
	public Comment insert(Connection conn, Comment comment) throws SQLException{
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement("insert into article_comment values (article_comment_seq.nextval,?,?,?,?,?)");
			pstmt.setString(1, comment.getId());
			pstmt.setTimestamp(2, toTimestamp(comment.getComment_date()) );
			pstmt.setInt(3, comment.getComment_article()); //article의 article_no를 가져와야 하나?
			pstmt.setInt(4, comment.getComment_parent());                                    
			pstmt.setString(5, comment.getComment_content());
			pstmt.executeUpdate();
			
			int insertedCount = pstmt.executeUpdate();
			
			if(insertedCount >0) {
				stmt = conn.createStatement();
				rs = stmt.executeQuery("select max(comment_num) from article_comment");
				if(rs.next()) {
					Integer newNum = rs.getInt(1);
					return new Comment(newNum, comment.getId(), comment.getComment_date(), 
							comment.getComment_article(), comment.getComment_parent(), comment.getComment_content() 
							);
				}
			}
			return null;
	
		}finally {
			JdbcUtil.close(conn);
			JdbcUtil.close(stmt);
			JdbcUtil.close(pstmt);
		}
	}

	private Timestamp toTimestamp(Date date) {
		return new Timestamp(date.getTime());
	}
	
	public ArrayList<Comment> CommentList(Connection conn, int ArticleNum) throws SQLException{
		ArrayList<Comment> list = new ArrayList<Comment>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql="select level, comment_num, comment_article, "
					+ "comment_id, comment_date, comment_parent, comment_content "
					+ "from article_comment where comment_article = ? start with comment_parent = 0 "
					+ "connect by prior comment_num = comment_parent";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ArticleNum);
			
			rs=pstmt.executeQuery();
			while(rs.next()) {
				//Comment comment = new Comment(ArticleNum, sql, null, ArticleNum, ArticleNum, sql, ArticleNum);
				
				//코맨트들을 생성자로 생성하고 list.add할 예정.
				
			}
			
		}finally {
			
		}
		return list;
	}
	
	
	
}
