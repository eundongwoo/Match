package article.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.comment.service.ArticleCommentService;
import article.comment.service.CommentWriteRequest;
import member.service.User;
import mvc.command.CommandHandler;

public class CommentHandler implements CommandHandler{
	
	private ArticleCommentService commentservice = new ArticleCommentService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		System.out.println("handler진입");
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		User user = (User) req.getSession(false).getAttribute("authUser");
		CommentWriteRequest writeReq = createCommentRequest(user, req);
		writeReq.validate(errors);
		
		if(!errors.isEmpty()) {
			//return FORM_VIEW;
			System.out.println("에러가있어요.");
		}
		
		int newCommentNo = commentservice.write(writeReq);  //댓글번호를 받아올 예정..
		req.setAttribute("newCommentNo", newCommentNo);
		
		return null;
	}
	
	private CommentWriteRequest createCommentRequest(User user, HttpServletRequest req) {
		String noVal = (String) req.getParameter("num");
		int articleNumber = Integer.parseInt(noVal);
		
		return new CommentWriteRequest(
				req.getParameter("id"),
				req.getParameter("content"),
				articleNumber);
				
	}

}
