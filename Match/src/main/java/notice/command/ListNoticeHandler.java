package notice.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import notice.service.ListNoticeService;
import notice.service.NoticePage;

public class ListNoticeHandler implements CommandHandler{

	private ListNoticeService listService = new ListNoticeService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String pageNoVal = req.getParameter("pageNo");
		int pageNo = 1;
		if(pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		NoticePage noticePage = listService.getNoticePage(pageNo);
		req.setAttribute("noticePage", noticePage);
		
		return "/WEB-INF/noticeview/listNotice.jsp";	
	}
}
