package admin.command;

import java.sql.SQLException;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import admin.service.AdminService;
import map.model.Place;
import member.service.JoinService;
import mvc.command.CommandHandler;

public class AdminHandler implements CommandHandler{

	private static final String FORM_VIEW="/WEB-INF/view/addPlace.jsp";	
	//private JoinService joinService=new JoinService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		if(request.getMethod().equalsIgnoreCase("GET"))
		{
			return processForm(request,response);
		}else if(request.getMethod().equalsIgnoreCase("POST"))
		{
			return processSubmit(request,response);
		}else
		{
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		String placeName = request.getParameter("placeName");
		String placeAddr = request.getParameter("placeAddr");
		String placeTel = request.getParameter("placeTel");
		String timeRowNum = request.getParameter("timeRowNum");
		int rowNum= Integer.parseInt(timeRowNum);
		HashMap<Integer, String> hm = new HashMap<Integer, String>();	//시간대 가지고 있는 해시맵
		for(int i=1; i<=rowNum; i++) {
			String operationTime = request.getParameter("operationTime"+i);
			hm.put(i, operationTime);	//해시맵에 넣기 
		}
		System.out.println(placeName+":"+placeAddr+":"+placeTel+":"+timeRowNum);
		Place place = new Place(placeName, placeAddr, placeTel); 	//place객체 생성
		AdminService adminService = new AdminService();
		
		adminService.addPlace(place, rowNum, hm);
	 
		System.out.println("끝자락");
		return "/main.jsp";
	}
	
	//get
	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		
		
		return FORM_VIEW;
	}

}
