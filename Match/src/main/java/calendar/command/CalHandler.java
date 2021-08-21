package calendar.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import calendar.model.Calendar;
import mvc.command.CommandHandler;

public class CalHandler implements CommandHandler{
	private static final String FORM_VIEW="/WEB-INF/view/reserve.jsp";
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(request.getMethod().equalsIgnoreCase("GET")) {
			System.out.println("여기 calhandler1");
			return processForm(request, response);
		}else
		{
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
		
	}
	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		String date = request.getParameter("date");
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		Calendar calendar = new Calendar(year, month, date);
		request.getSession().setAttribute("calendar", calendar);
		System.out.println("여기 calhandler2");
		return FORM_VIEW;
		
		
	}

}
