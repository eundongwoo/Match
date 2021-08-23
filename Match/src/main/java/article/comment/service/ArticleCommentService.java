package article.comment.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import article.comment.dao.CommentDao;
import article.comment.model.Comment;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class ArticleCommentService {
	private CommentDao commentDao = new CommentDao();
	
	public Integer write(CommentWriteRequest req) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Comment comment = toComment(req);
			Comment savedComment = commentDao.insert(conn, comment);
			if(savedComment == null) {
				throw new RuntimeException("fail to insert Comment");
			}
			System.out.println("insert완료");
			conn.commit();
			return savedComment.getComment_num();
			
		}catch(SQLException	e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}catch(RuntimeException e) {
			JdbcUtil.rollback(conn);
			throw e;
		}
		finally {
			JdbcUtil.close(conn);
		}
	}
	
	
	private Comment toComment(CommentWriteRequest req) {
		Date now = new Date();
		return new Comment(null, req.getId(), now, req.getArticleNum(), 0, req.getContent());
	}
	//게시글번호...주키를 가져와야한다.

//	public void readComment() {
//		
//	}
}
