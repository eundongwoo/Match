package article.service;

import java.sql.Connection;
import java.sql.SQLException;
import article.dao.ArticleDao;
import article.model.Article;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class ModifyArticleService {
	private ArticleDao articleDao = new ArticleDao();
	
	public void modify(ModifyRequest modReq) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Article article = articleDao.selectById(conn, modReq.getArticleNumber());
			if(article == null) {
				throw new ArticleNotFoundException();
			}
			if(!canModify(modReq.getUserId(), article)) { //article ->글번호에 해당하는 실제db에있는 것.
				throw new PermissionDeniedException();
			}
			articleDao.update(conn, modReq.getArticleNumber(), modReq.getTitle(), modReq.getContent());
			conn.commit();
			
		}catch(SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}catch(PermissionDeniedException e) {
			JdbcUtil.rollback(conn);
			throw e;
		}finally {
			JdbcUtil.close(conn);
		}
	}

	private boolean canModify(String modyfyingUserId, Article article) {  //방금요청한 것과 db에있는 것.
		return article.getWriter().getId().equals(modyfyingUserId);
	}
}
