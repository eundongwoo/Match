package notice.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import notice.model.Notice;
import notice.service.NoticeNotFoundException;
import notice.service.ReadNoticeService;

public class ReadNoticeHandler implements CommandHandler{
	private static final String FORM_VIEW="/WEB-INF/noticeview/readNotice.jsp";
	private ReadNoticeService readService = new ReadNoticeService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String noVal = req.getParameter("no");
		int noticeNumber = Integer.parseInt(noVal);
		try {
			
			Notice notice = readService.getNotice(noticeNumber, true);
			req.setAttribute("notice", notice);
			return FORM_VIEW;
			
		}catch(NoticeNotFoundException e) {
			req.getServletContext().log("no notice", e);
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}	
	}
}
