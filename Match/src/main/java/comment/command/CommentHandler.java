package comment.command;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import comment.service.ArticleCommentService;
import comment.service.CommentDeleteRequest;
import comment.service.CommentReadRequest;
import comment.service.CommentWriteRequest;

import member.service.User;
import mvc.command.CommandHandler;

public class CommentHandler implements CommandHandler{
	
	private ArticleCommentService commentservice = new ArticleCommentService();
	private static final String FORM_VIEW = "/WEB-INF/view/readArticle.jsp";
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String judge = req.getParameter("judge");	//이걸로 요청 판단
	
		//댓글 삭제
		if(judge.equals("delete")) {
			String commentNum = req.getParameter("commentNum");
			String articleNum =req.getParameter("num");	//게시글 번호
			CommentDeleteRequest deleteReq = new CommentDeleteRequest(Integer.parseInt(articleNum) , Integer.parseInt(commentNum));		//삭제에 필요한 것들: 댓글번호(primary key), 게시글번호
			commentservice.delete(deleteReq);
			PrintWriter pw=res.getWriter();
			JSONObject jo = new JSONObject();
			pw.println(jo);
			return null;
		}		//댓글 보기기능	
		else if(judge.equals("read")) {
			
			res.setCharacterEncoding("utf-8"); 
			HashMap<String, Object> result = null;	//결과 해쉬맵
			CommentReadRequest readReq = new CommentReadRequest(Integer.parseInt(req.getParameter("num"))); 	//게시글번호 들어감
			
			
			User user = (User) req.getSession(false).getAttribute("authUser");
			result = commentservice.read(readReq);  //result 해시맵 객체 받음
			JSONObject jo = new JSONObject(result);		//JsonObject로 받음.
			PrintWriter pw = res.getWriter();
			pw.println(jo);
			
			return null;
		} else {
			//댓글쓰기 기능
			res.setCharacterEncoding("utf-8"); 
			HashMap<String, Object> result = null;	//결과 해쉬맵
			
			Map<String, Boolean> errors = new HashMap<>();
			req.setAttribute("errors", errors);
			User user = (User) req.getSession(false).getAttribute("authUser");
			
			CommentWriteRequest writeReq = createCommentRequest(user, req);		//댓글 객체(CommentWriteRequest) 생성
			writeReq.validate(errors);
			if(!errors.isEmpty()) {
				System.out.println("에러가있어요.");
			}//에러 처리 부분.
			

			result = commentservice.write(writeReq);  //result 해시맵 객체 받음
			JSONObject jo = new JSONObject(result);		//JsonObject로 받음.
			PrintWriter pw = res.getWriter();
			pw.println(jo);
		
			return null;
		}
		
	
		
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
