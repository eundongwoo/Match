package mypage.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.service.MemberNotFoundException;
import member.service.User;
import mvc.command.CommandHandler;
import mypage.service.TelModifyService;

public class TelModifyHandler implements CommandHandler {

	private static final String FORM_VIEW="/WEB-INF/view/telmodify.jsp";
	private TelModifyService telService=new TelModifyService();
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
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

	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return FORM_VIEW;
	}

	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws IOException {
		User user=(User)request.getSession().getAttribute("authUser");
		Map<String, Boolean> errors=new HashMap<String, Boolean>();
		request.setAttribute("errors", errors);
		String newTel=request.getParameter("newTel");
		user.setTel(newTel);
		if(newTel==null || newTel.isEmpty())
		{
			errors.put("newTel", Boolean.TRUE);
		}
		if(!errors.isEmpty())
		{
			return FORM_VIEW;
		}
		
		try
		{
			telService.changeTel(user.getId(),newTel);
			request.setAttribute("authUser", user);
			return "/WEB-INF/view/successTelModify.jsp";
		}catch(MemberNotFoundException e)
		{
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
	}

}
