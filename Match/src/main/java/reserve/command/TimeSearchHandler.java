package reserve.command;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
		
		HashMap<String, Integer> hm=new HashMap<String, Integer>();
		for(String s:list) {
			hm.put(s, 0);
		}
		hm= timeSearchService.makeMap(searchTimeRequest, hm);
		Set<String> hmKeySet= hm.keySet();
		Iterator<String> iterator= hmKeySet.iterator();
		System.out.println("해쉬맵");
		
		while(iterator.hasNext()) {
			String key= iterator.next();
			Integer value= hm.get(key);
			System.out.println(key+"시간대 사람수:"+value);
		}
		request.getSession().setAttribute("timeMap",hm);
		//request.setAttribute("timeList", list);
		request.getSession().setAttribute("timeList", list); // 해당 풋살장의 운영시간 세션으로 만들기.
		
		return FORM_VIEW;
	}	
}
