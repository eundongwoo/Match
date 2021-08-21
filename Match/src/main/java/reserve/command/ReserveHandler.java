package reserve.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import map.model.Place;
import map.service.MapService;
import mvc.command.CommandHandler;
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
		{	//System.out.println((String)request.getAttribute("placeName"));
//			if(request.getAttribute("placeName")!=null) {
//				return processSubmit2(request,response);
//			}else {
			System.out.println(request.getAttribute("placeName"));
			return processSubmit2(request,response);
			
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
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		
		return null;
	}

}
