package member.command;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import member.service.LoginFailException;
import member.service.LoginService;
import member.service.User;
import mvc.command.CommandHandler;

public class LoginHandler implements CommandHandler{
	
	private static final String FORM_VIEW ="/WEB-INF/view/loginForm.jsp";		//로그인폼(jsp) 위치
	private LoginService loginService = new LoginService();		//loginservice
	
	
	@Override			//핸들러는 request랑 response를 받아서 처리.
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(request.getMethod().equalsIgnoreCase("GET")) {		
			return  processForm(request, response);
		}else if(request.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(request, response);
		}else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
		}
		
		return null;
	}


	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = trim(request.getParameter("id"));
		String password = trim(request.getParameter("password"));
		
		Map<String, Boolean> errors = new HashMap<>();		//에러에 대한 정보 담을 map 객체 생성
		request.setAttribute("errors", errors);
		
		if(id==null || id.isEmpty())
			errors.put("id", Boolean.TRUE);		//true나 false도 객체형태로 map에 저장
		if(password==null||password.isEmpty())
			errors.put("password", Boolean.TRUE);		
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		
		try {
			User user = loginService.login(id, password);	
			request.getSession().setAttribute("authUser", user);  //session객체 가져와서 user에 대한 정보 속성을 추가해준다
			response.sendRedirect(request.getContextPath()+"/index.jsp");	//--->이상 없으면 여기로 redirect해준다.				
			
			return null;
		}catch(LoginFailException e) {		
			errors.put("idOrPwNotMatch", Boolean.TRUE);
			return FORM_VIEW;
		}
		
	}

	private String processForm(HttpServletRequest request, HttpServletResponse response) {		
		return FORM_VIEW;
	}
	

	private String trim(String str) {
		return str==null? null: str.trim();
	}

}