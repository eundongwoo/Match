package mypage.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.service.InvalidPasswordException;
import member.service.MemberNotFoundException;
import member.service.User;
import mvc.command.CommandHandler;
import mypage.service.PwdModifyService;

public class PwdModifyHandler implements CommandHandler {

	private static final String FORM_VIEW="/WEB-INF/view/pwdmodify.jsp";
	private PwdModifyService pwdService = new PwdModifyService();
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
		return FORM_VIEW;
	}

	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws IOException {
		User user=(User)request.getSession().getAttribute("authUser");
		Map<String , Boolean> errors=new HashMap<String, Boolean>();
		request.setAttribute("errors", errors);
		
		String newPwd=request.getParameter("newPwd");
		if(newPwd==null || newPwd.isEmpty())
		{
			errors.put("newPwd", Boolean.TRUE);
		}
		if(!errors.isEmpty())
		{
			return FORM_VIEW;
		}
		
		try
		{
			pwdService.changePassword(user.getId(),newPwd);
			return "/WEB-INF/view/successpwdModify.jsp";
		}catch (InvalidPasswordException e) {
			// TODO: handle exception
			errors.put("badCurPwd", Boolean.TRUE);
			return FORM_VIEW;
		}catch(MemberNotFoundException e)
		{
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
	}
}
