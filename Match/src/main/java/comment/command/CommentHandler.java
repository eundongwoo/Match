package comment.command;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import comment.service.ArticleCommentService;
import comment.service.CommentWriteRequest;
import member.service.User;
import mvc.command.CommandHandler;

public class CommentHandler implements CommandHandler{
	
	private ArticleCommentService commentservice = new ArticleCommentService();
	private static final String FORM_VIEW = "/WEB-INF/view/readArticle.jsp";
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		HashMap<String, Object> result = null;	//결과 해쉬맵
		
		System.out.println("handler진입");
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		System.out.println("여기 1");
		User user = (User) req.getSession(false).getAttribute("authUser");
		System.out.println("여기 2");
		CommentWriteRequest writeReq = createCommentRequest(user, req);		//댓글 객체(CommentWriteRequest) 생성
		writeReq.validate(errors);
		System.out.println("여기 3");
		if(!errors.isEmpty()) {
			//return FORM_VIEW;
			System.out.println("에러가있어요.");
		}
		System.out.println("여기 4");
		result = commentservice.write(writeReq);  //result 해시맵 객체 받음
		System.out.println("여기 10");
		JSONObject jo = new JSONObject(result);		//JsonObject로 받음.
		PrintWriter pw = res.getWriter();
		pw.println(jo);
		//req.setAttribute("newCommentNo", newCommentNo);
		
		return null;
	}
	
	private CommentWriteRequest createCommentRequest(User user, HttpServletRequest req) {
		String noVal = req.getParameter("num");	//artice num(게시글 번호)
		String content= req.getParameter("content");	//댓글 내용
		int articleNumber = Integer.parseInt(noVal);
		
		return new CommentWriteRequest(
				user.getId(),
				content,
				articleNumber);
				
	}

}
