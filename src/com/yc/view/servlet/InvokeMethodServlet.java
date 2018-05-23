package com.yc.view.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
// http://localhost:8081/dateView/invoke?subscriber=chartjsonInit.sqlToJson
public class InvokeMethodServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected static ApplicationContext ac;
	/**
     * @see HttpServlet#HttpServlet()
     */
    public InvokeMethodServlet() {
        // TODO Auto-generated constructor stub
    }
    public void init(ServletConfig sc) throws ServletException {
		super.init(sc);
		ac = WebApplicationContextUtils.getRequiredWebApplicationContext(sc.getServletContext());
	}
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doPost(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String subscriber = request.getParameter("subscriber");
    	try {
			invokeMethod(subscriber);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	System.out.println(subscriber);
    }
    
    public void invokeMethod(String subscriber) throws Exception{
		String managerName =  StringUtils.substringBefore(subscriber.trim(),".");;
		String methodName = StringUtils.substringAfter(subscriber.trim(),".");
		Object manager = ac.getBean(managerName);
		Boolean isError = Boolean.FALSE;
		try {
			Method method = manager.getClass().getMethod(methodName, new Class[]{});
			method.invoke(manager,new Object[]{});
		} catch (IllegalArgumentException e) {
			isError = Boolean.TRUE;
			e.printStackTrace();
		} catch (SecurityException e) {
			isError = Boolean.TRUE;
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			isError = Boolean.TRUE;
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			isError = Boolean.TRUE;
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			isError = Boolean.TRUE;
			e.printStackTrace();
		}
	}
}
