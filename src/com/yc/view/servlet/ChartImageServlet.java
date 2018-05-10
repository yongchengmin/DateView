package com.yc.view.servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.jfree.chart.ChartUtilities;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.yc.view.chart.BarChartLay;
import com.yc.view.service.ChartJdbcInit;
import com.yc.view.service.ChartjsonInit;
import com.yc.view.utils.ChartGlobal;
import com.yc.view.utils.ProjectUtils;
// http://localhost:8081/dateView/chartJson?parameter=left_top
// 本地调试URL
// http://localhost:8081/dateView/chartJson?parameter=left_top&path=D:\json\left_top_normal.json
public class ChartImageServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected static ApplicationContext ac;
	protected ChartJdbcInit chartJdbcInit;
	protected ChartjsonInit chartjsonInit;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public ChartImageServlet() {
        // TODO Auto-generated constructor stub
    }
    public void init(ServletConfig sc) throws ServletException {
		super.init(sc);
		ac = WebApplicationContextUtils.getRequiredWebApplicationContext(sc.getServletContext());
		chartJdbcInit = (ChartJdbcInit) ac.getBean(ChartJdbcInit.BEAN);
		chartjsonInit = (ChartjsonInit) ac.getBean(ChartjsonInit.BEAN);
	}
    
    /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doPost(request, response);
    }
    
    /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String jsonFile = null,json = null,name = null;
    	if(ChartGlobal.LEFT_TOP.equals(request.getParameter("parameter"))){
    		jsonFile = ChartGlobal.LEFT_TOP+ChartGlobal.jsonEnd;
    	}else if(ChartGlobal.LEFT_TOP_DEMO.equals(request.getParameter("parameter"))){
    		jsonFile = ChartGlobal.LEFT_TOP_DEMO+ChartGlobal.jsonEnd;
    		
    	}
    	if(!StringUtils.isEmpty(jsonFile)){
    		try {
    			json = ProjectUtils.getString(ProjectUtils.getByte(jsonFile));
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    		name = ChartGlobal.LEFT_TOP+ChartGlobal.imageEnd;
    		outBarChartLayJpeg(json,name);
    		returnRequest(response,name);
    	}
    	else{
    		returnErrorRequest(response,"parameter is not avaliable!");
    	}
    	
    }
    
    protected void outBarChartLayJpeg(String json,String name) throws IOException{
    	//图片是文件格式的,故要用到FileOutputStream用来输出.
    	 OutputStream os = new FileOutputStream(name);
    	//使用一个面向application的工具类,将chart转换成JPEG格式的图片.第3个参数是宽度,第4个参数是高度.
         ChartUtilities.writeChartAsJPEG(os, new BarChartLay(json).getJFreeChart(), 1024, 420);
         os.close();//关闭输出流
    }
    
    protected void returnRequest(HttpServletResponse response,String name) throws IOException {
    	try{
    		byte data[] = ProjectUtils.getByte(name);
	    	// 重设为image/jpeg
			response.setContentType("image/jpeg");
			// 设置页面不缓存
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.setCharacterEncoding(ChartGlobal.encodeing);
    		
    		OutputStream toClient=response.getOutputStream(); //得到向客户端输出二进制数据的对象
    		toClient.write(data);  //输出数据
    		toClient.close();
    	} catch(IOException e) {
    		returnErrorRequest(response,name+" is not open!");
		}
	}
    
    protected void returnErrorRequest(HttpServletResponse response,String s) throws IOException {
    	PrintWriter toClient = response.getWriter(); //得到向客户端输出文本的对象
		response.setContentType("text/html;charset=utf-8");
		toClient.write(s);
		toClient.close();
    }
}
