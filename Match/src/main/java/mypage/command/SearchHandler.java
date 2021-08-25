package mypage.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.service.User;
import mvc.command.CommandHandler;
import mypage.model.ReserveInfo;
import mypage.service.ListReserveService;
import reserve.model.ReserveRequest;

public class SearchHandler implements CommandHandler {

	private static final String FORM_VIEW="/WEB-INF/view/search.jsp";
	private ListReserveService listReserveService=new ListReserveService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user=(User)request.getSession().getAttribute("authUser");
		System.out.println(user.getId()+":"+user.getName()); //테스트		
		List<ReserveInfo> list=listReserveService.getReserveList(user);
		request.setAttribute("ReserveList", list);
		return FORM_VIEW;
	}
}
