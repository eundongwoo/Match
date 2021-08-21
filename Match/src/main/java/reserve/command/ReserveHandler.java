package reserve.command;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import map.model.Place;
import map.service.MapService;
import member.service.User;
import mvc.command.CommandHandler;
import reserve.model.ReserveRequest;
import reserve.service.ReserveService;

public class ReserveHandler implements CommandHandler {

	private static final String FORM_VIEW="/WEB-INF/view/reserve.jsp";
	private ReserveService reserveService=new ReserveService();
	private MapService mapService=new MapService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(request.getMethod().equalsIgnoreCase("GET"))
		{
			return processForm(request,response);
		}else if(request.getMethod().equalsIgnoreCase("POST"))
		{
			return processSubmit(request, response);
		}
		else
		{
			response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	
	private String processSubmit2(HttpServletRequest request, HttpServletResponse response) {
		//request.getAttribute("placeName");
		System.out.println("processSubmit2");
		return FORM_VIEW;
	}


	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		MapService mapService=new MapService();
		List<Place> list=mapService.initMap();
		//request.setAttribute("list", list);
		request.getSession().setAttribute("list", list);
		return FORM_VIEW;
	}
	
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		User user= (User)request.getSession().getAttribute("authUser");
		System.out.println("제발좀:"+user.getId());
		ReserveRequest reserveRequest = new ReserveRequest(user, request.getParameter("place"), request.getParameter("date"), 
				request.getParameter("time"));
		System.out.println("reserveHandler 옴");
		reserveService.reserve(reserveRequest);
		return "/main.jsp";
	}

}
