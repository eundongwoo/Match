package article.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.service.ArticleData;
import article.service.ArticleNotFoundException;
import article.service.DeleteArticleService;
import article.service.ModifyRequest;
import article.service.PermissionDeniedException;
import article.service.ReadArticleService;
import member.service.User;
import mvc.command.CommandHandler;

public class DeleteArticleHandler implements CommandHandler{
	private DeleteArticleService deleteService = new DeleteArticleService();
	
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		User authUser = (User) req.getSession().getAttribute("authUser");
		String noVal = req.getParameter("no");
		int articleNum = Integer.parseInt(noVal);

		
		ModifyRequest delReq = new ModifyRequest(authUser.getId(), articleNum, req.getParameter("title"),
				req.getParameter("content"));
		req.setAttribute("delReq", delReq);
		
		try {
			deleteService.delete(delReq);
			return "article_list.do";
		}catch(ArticleNotFoundException e) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}catch(PermissionDeniedException e) {
			resp.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
		
	}

	private boolean canDelete(User authUser, ArticleData articleData) {
		String writerId = articleData.getArticle().getWriter().getId();
		return authUser.getId().equals(writerId);
	}

}
