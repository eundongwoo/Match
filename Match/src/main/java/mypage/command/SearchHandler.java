package mypage.command;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.service.User;
import mvc.command.CommandHandler;
import mypage.model.CancelInfo;
import mypage.model.ReserveInfo;
import mypage.service.CancelReserveService;
import mypage.service.ListReserveService;
import reserve.model.ReserveRequest;

public class SearchHandler implements CommandHandler {

	private static final String FORM_VIEW="/WEB-INF/view/search.jsp";
	private ListReserveService listReserveService=new ListReserveService();
	private CancelReserveService cancelService=new CancelReserveService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(request.getMethod().equalsIgnoreCase("GET"))
		{
			return processForm(request,response);
		}
		else
		{
			response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest request, HttpServletResponse response) throws ParseException {
		User user=(User)request.getSession().getAttribute("authUser");
		String place_name=request.getParameter("place_name");
		if(place_name !=null)
		{
			CancelInfo cancelInfo=new CancelInfo(user,request.getParameter("place_name"),request.getParameter("reserve_date"),request.getParameter("reserve_time"), request.getParameter("reg_time"));
			cancelService.delete(cancelInfo);
		}	
		List<ReserveInfo> list=listReserveService.getReserveList(user);
		request.setAttribute("ReserveList", list);
		
		return FORM_VIEW;
	}
}
