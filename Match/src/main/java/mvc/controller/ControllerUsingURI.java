package mvc.controller;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import mvc.command.NullHandler;


public class ControllerUsingURI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map<String,CommandHandler> commandHandlerMap=
			new HashMap<String,CommandHandler>();
	@Override
	public void init() throws ServletException {
		System.out.println("ControllerUsingFile 서블릿 동작");
		String configFile=getInitParameter("configFile");
		System.out.println(configFile);
		Properties prop=new Properties();
		String configFilePath=getServletContext().getRealPath(configFile);
		System.out.println(configFilePath);
		try(FileReader fis=new FileReader(configFilePath)){
			prop.load(fis);
		}catch(IOException e) {
			throw new ServletException();
		}
		Iterator<Object> keyIter=prop.keySet().iterator();
		while(keyIter.hasNext()) {
			String command=(String)keyIter.next();
			String handlerClassName=prop.getProperty(command);
			System.out.println(command+" : "+handlerClassName);
			try {
				
				Class<?> handlerClass=Class.forName(handlerClassName);
				CommandHandler handlerInstance=(CommandHandler)handlerClass.newInstance();
				commandHandlerMap.put(command, handlerInstance);
			}catch(ClassNotFoundException | InstantiationException |
					IllegalAccessException e){
				throw new ServletException(e);
			}
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		process(request,response);
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("====>pro");
		//String command=request.getParameter("cmd");
		String command=request.getRequestURI();
		if(command.indexOf(request.getContextPath())==0){
			command=command.substring(request.getContextPath().length());
		}
		System.out.println("====>"+command);
		CommandHandler handler=commandHandlerMap.get(command);
		if(handler==null) {
			handler=new NullHandler();
		}
		String viewPage=null;
		try {
			viewPage=handler.process(request, response);
		}catch(Exception e) {
			throw new ServletException(e);
		}
		if(viewPage!=null) {
			RequestDispatcher dis=request.getRequestDispatcher(viewPage);
			dis.forward(request, response);
		}
	}
}
