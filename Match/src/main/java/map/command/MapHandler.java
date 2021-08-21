package map.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;

public class MapHandler implements CommandHandler{
	private static final String FORM_VIEW="/WEB-INF/view/reserve.jsp";
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(request.getMethod().equalsIgnoreCase("POST")) {
			return processForm(request, response);
		}else
		{
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		String placeName=request.getParameter("placeName");
		request.getSession().setAttribute("placeName", placeName);
		return FORM_VIEW;
			
	}

}
