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
		Connection conn = null;		
		try {
			conn=ConnectionProvider.getConnection(); 			
			conn.setAutoCommit(false);
			HashMap<String, Object> result = null;			
			Comment comment = toComment(commentRequest);	//Comment객체			
			result = commentDao.insert(conn, comment);		//result해시맵 안에 comments리스트 들어있음.
			
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


	public HashMap<String, Object> read(CommentReadRequest readReq) {
		Connection conn = null;		
		try {
			conn=ConnectionProvider.getConnection(); 			
			conn.setAutoCommit(false);
			HashMap<String, Object> result = null;			
			
			result = commentDao.read(conn, readReq, 1);	
			conn.commit();
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


	public void delete(CommentDeleteRequest deleteReq) throws SQLException {
		Connection conn = null;
		conn = ConnectionProvider.getConnection();
		conn.setAutoCommit(false);
		System.out.println("delte메소드 안 삭제할 댓글번호: "+deleteReq.getCommentNum());
		commentDao.delete(conn, deleteReq);
		conn.commit();
		JdbcUtil.close(conn);
		
	}
}
