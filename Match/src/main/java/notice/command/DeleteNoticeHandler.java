package notice.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import member.service.User;
import mvc.command.CommandHandler;
import notice.model.Notice;
import notice.service.DeleteNoticeService;
import notice.service.ModifyRequest;
import notice.service.NoticeNotFoundException;
import notice.service.PermissionDeniedException;

public class DeleteNoticeHandler implements CommandHandler{
	private DeleteNoticeService deleteService = new DeleteNoticeService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		User authUser = (User) req.getSession().getAttribute("authUser");
		String noVal = req.getParameter("no");
		int noticeNum = Integer.parseInt(noVal);

		
		ModifyRequest delReq = new ModifyRequest(authUser.getId(), noticeNum, req.getParameter("title"),
				req.getParameter("content"));
		req.setAttribute("delReq", delReq);
		
		try {
			deleteService.delete(delReq);
			return "notice_list.do";
		}catch(NoticeNotFoundException e) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}catch(PermissionDeniedException e) {
			resp.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
	}

	private boolean canDelete(User authUser, Notice notice) {
		String writerId = notice.getWriter().getId();
		return authUser.getId().equals(writerId);
	}

}
