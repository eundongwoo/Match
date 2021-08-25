package article.service;

import java.sql.Connection;
import java.sql.SQLException;
import article.dao.ArticleDao;
import article.model.Article;
import jdbc.connection.ConnectionProvider;

public class ReadArticleService {
	private ArticleDao articleDao = new ArticleDao();
	
	
	public ArticleData getArticle(int articleNum, boolean increaseReadCount) {
		
		try(Connection conn = ConnectionProvider.getConnection()){
			Article article = articleDao.selectById(conn, articleNum);
			if(article == null) {
				throw new ArticleNotFoundException();
			}

			if(increaseReadCount) {
				articleDao.increaseReadCount(conn, articleNum);
			}
			
			return new ArticleData(article);
			
		}	catch (SQLException	e) {
			throw new RuntimeException(e);
		}
	}
}
