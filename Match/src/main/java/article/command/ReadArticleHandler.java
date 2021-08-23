package article.command;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comment.service.ArticleCommentService;
import comment.service.CommentWriteRequest;
import article.service.ArticleData;
import article.service.ArticleNotFoundException;
import article.service.ReadArticleService;
import member.service.User;
import mvc.command.CommandHandler;

public class ReadArticleHandler implements CommandHandler{
	private static final String FORM_VIEW="/WEB-INF/view/readArticle.jsp";
	
	private ReadArticleService readService = new ReadArticleService();
	
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String noVal = req.getParameter("no");
		int articleNumber = Integer.parseInt(noVal);
		try {
			ArticleData articleData = readService.getArticle(articleNumber, true);
			req.setAttribute("articleData", articleData);
			
			return FORM_VIEW;
			
		}catch(ArticleNotFoundException e) {
			req.getServletContext().log("no article", e);
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		
	}

	
	
}
