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
			return processForm(request, response);
		}else 
		{	
			return processForm(request, response);
		}
		
	}
	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		String date = request.getParameter("date");
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		Calendar calendar = new Calendar(year, month, date);
		request.getSession().setAttribute("calendar", calendar);
		
		return FORM_VIEW;	
	}
}
