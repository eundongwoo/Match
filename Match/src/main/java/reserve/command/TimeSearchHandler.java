package reserve.command;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mvc.command.CommandHandler;
import reserve.model.SearchTimeRequest;
import reserve.service.TimeSearchService;

public class TimeSearchHandler implements CommandHandler {

	private static final String FORM_VIEW="/WEB-INF/view/reserve.jsp";
	private TimeSearchService timeSearchService=new TimeSearchService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		if(request.getMethod().equalsIgnoreCase("POST"))
		{
			return processSubmit(request,response);
		}
		else
		{
			response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("제발 넘어와라->"+request.getParameter("placeName"));
		System.out.println("제발 이것도->"+request.getParameter("placeDate"));
		SearchTimeRequest searchTimeRequest=new SearchTimeRequest(request.getParameter("placeName"),request.getParameter("placeDate"));
		
		List<String> list=timeSearchService.getTimeList(searchTimeRequest);
		
		//request.setAttribute("timeList", list);
		request.getSession().setAttribute("timeList", list); // 해당 풋살장의 운영시간 세션으로 만들기.
		
		return FORM_VIEW;
	}	
}
