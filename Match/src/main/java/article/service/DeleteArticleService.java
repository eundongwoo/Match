package article.service;

import java.sql.Connection;
import java.sql.SQLException;
import article.dao.ArticleDao;
import article.model.Article;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class DeleteArticleService {
	
	private ArticleDao articleDao = new ArticleDao();
	
	
	public void delete(ModifyRequest delReq) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Article article = articleDao.selectById(conn, delReq.getArticleNumber());
			if(article == null) {
				throw new ArticleNotFoundException();
			}
			if(!canDelete(delReq.getUserId(), article)) { //실제db와 , 요청에 의한 사용자
				throw new PermissionDeniedException();
			}
			
			articleDao.delete(conn, delReq.getArticleNumber());
			
			conn.commit();
		}catch(SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		}catch(PermissionDeniedException e) {
			JdbcUtil.rollback(conn);
			throw e;
		}finally {
			JdbcUtil.close(conn);
		}
	}

	private boolean canDelete(String userId, Article article) {
		return article.getWriter().getId().equals(userId);
	}
}
