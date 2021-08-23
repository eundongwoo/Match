package comment.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;

import org.json.JSONObject;

import comment.dao.CommentDao;
import comment.model.Comment;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class ArticleCommentService {
	private CommentDao commentDao = new CommentDao();
	
	public HashMap<String, Object> write(CommentWriteRequest commentRequest) throws SQLException {
		System.out.println("여기 6");
		Connection conn = null;
		
		try {
			conn=ConnectionProvider.getConnection(); 
			System.out.println("여기 7");
			
			conn.setAutoCommit(false);
			HashMap<String, Object> result = null;
			System.out.println("여기 8");
			Comment comment = toComment(commentRequest);	//Comment객체
			System.out.println("여기 9");
			result = commentDao.insert(conn, comment);		//result해시맵 안에 comments리스트 들어있음.
			System.out.println("여기 10");
			
			
//			Comment savedComment = commentDao.commentList(conn, 0, 0)
//			if(savedComment == null) {
//				throw new RuntimeException("fail to insert Comment");
//			}
			
			conn.commit();
			
//			return savedComment.getComment_num();
			return result;
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
		return new Comment(0, req.getId(), now, req.getArticleNum(), req.getContent());		
	}
	//게시글번호...주키를 가져와야한다.

//	public void readComment() {
//		
//	}
}
