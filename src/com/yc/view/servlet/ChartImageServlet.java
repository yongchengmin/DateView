package com.yc.view.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;

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
import com.yc.view.chart.DualaxisChartLay;
import com.yc.view.chart.LineChartLay;
import com.yc.view.chart.PieChartLay;
import com.yc.view.chart.demo.BarChart;
import com.yc.view.chart.demo.DefaultDemo;
import com.yc.view.chart.demo.DualaxisChart;
import com.yc.view.chart.demo.LineChart;
import com.yc.view.chart.demo.PieChart;
import com.yc.view.service.ChartJdbcInit;
import com.yc.view.service.ChartjsonInit;
import com.yc.view.utils.ChartGlobal;
import com.yc.view.utils.PictrueProess;
import com.yc.view.utils.ProjectUtils;
// http://localhost:8081/dateView/chartJson?parameter=left_top
// 本地调试URL
// http://localhost:8081/dateView/left_top_up.jsp
public class ChartImageServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected static ApplicationContext ac;
	protected ChartJdbcInit chartJdbcInit;
	protected ChartjsonInit chartjsonInit;
	private int width = 1024, height = 420;

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
    	String parameter = request.getParameter("parameter");
    	
    	String jsonFile = null,json = null,name = null;
    	if(ChartGlobal.LEFT_TOP.equals(parameter)){//左上显示
    		jsonFile = ChartGlobal.LEFT_TOP+ChartGlobal.jsonEnd;
    		File f = new File(jsonFile);
    		if(!f.exists()){
    			jsonFile = getBarChartLayPath();
    		}
    		name = ChartGlobal.LEFT_TOP+ChartGlobal.imageEnd;
    	} else if(parameter.startsWith(ChartGlobal.UP_DEMO)){//开发时根据实际传文件类型显示
    		parameter = parameter.substring(ChartGlobal.UP_DEMO.length());
    		if(parameter.equals(ChartGlobal.LEFT_TOP_DEMO)){
    			jsonFile = ChartGlobal.LEFT_TOP_DEMO+ChartGlobal.jsonEnd;
        		name = ChartGlobal.LEFT_TOP_DEMO+ChartGlobal.imageEnd;
    		} else if(parameter.equals(ChartGlobal.RIGHT_TOP_DEMO)){
    			jsonFile = ChartGlobal.RIGHT_TOP_DEMO+ChartGlobal.jsonEnd;
        		name = ChartGlobal.RIGHT_TOP_DEMO+ChartGlobal.imageEnd;
    		} else if(parameter.equals(ChartGlobal.LEFT_BOTTOM_DEMO)){
    			jsonFile = ChartGlobal.LEFT_BOTTOM_DEMO+ChartGlobal.jsonEnd;
        		name = ChartGlobal.LEFT_BOTTOM_DEMO+ChartGlobal.imageEnd;
    		} else if(parameter.equals(ChartGlobal.RIGHT_BOTTOM_DEMO)){
    			jsonFile = ChartGlobal.RIGHT_BOTTOM_DEMO+ChartGlobal.jsonEnd;
        		name = ChartGlobal.RIGHT_BOTTOM_DEMO+ChartGlobal.imageEnd;
			} else {
				jsonFile = DefaultDemo.defaultDemoPath;
	    		name = ChartGlobal.DEFAULT_DEMO+ChartGlobal.imageEnd;
			}
    	} else if(ChartGlobal.DEFAULT_DEMO.equals(parameter)){//开发菜单初始化显示
    		jsonFile = DefaultDemo.defaultDemoPath;
    		name = ChartGlobal.DEFAULT_DEMO+ChartGlobal.imageEnd;
    	} else if(ChartGlobal.RIGHT_TOP.equals(parameter)){//右上显示
    		// http://localhost:8081/dateView/chartJson?parameter=right_top
    		jsonFile = ChartGlobal.RIGHT_TOP+ChartGlobal.jsonEnd;
    		File f = new File(jsonFile);
    		if(!f.exists()){
    			jsonFile = getLineChartLayPath();
    		}
    		name = ChartGlobal.RIGHT_TOP+ChartGlobal.imageEnd;
    	} else if(ChartGlobal.LEFT_BOTTOM.equals(parameter)){//左下显示
    		// http://localhost:8081/dateView/chartJson?parameter=left_bottom
    		jsonFile = ChartGlobal.LEFT_BOTTOM+ChartGlobal.jsonEnd;
    		File f = new File(jsonFile);
    		if(!f.exists()){
    			jsonFile = getDualaxisChartPath();
    		}
    		name = ChartGlobal.LEFT_BOTTOM+ChartGlobal.imageEnd;
    	} else if(ChartGlobal.RIGHT_BOTTOM.equals(parameter)){//右下显示
    		// http://localhost:8081/dateView/chartJson?parameter=right_bottom
    		jsonFile = ChartGlobal.RIGHT_BOTTOM+ChartGlobal.jsonEnd;
    		File f = new File(jsonFile);
    		if(!f.exists()){
    			jsonFile = getPieChartLayPath();
    		}
    		name = ChartGlobal.RIGHT_BOTTOM+ChartGlobal.imageEnd;
    	}
    	if(!StringUtils.isEmpty(jsonFile)){
    		try {
    			json = ProjectUtils.getString(ProjectUtils.getByte(jsonFile));
    		} catch (IOException e) {
    			json = null;
    		}
    		if(ChartGlobal.RIGHT_TOP.equals(parameter)
    				|| ChartGlobal.RIGHT_TOP_DEMO.equals(parameter)){
    			if(StringUtils.isEmpty(json)){
    				jsonFile = getLineChartLayPath();
        			try {
            			json = ProjectUtils.getString(ProjectUtils.getByte(jsonFile));
            		} catch (IOException e) {
            			e.printStackTrace();
            		}
        		}
    			outLineChartLayJpeg(response, json, name);
    		} else if(ChartGlobal.LEFT_BOTTOM.equals(parameter)
    				|| ChartGlobal.LEFT_BOTTOM_DEMO.equals(parameter)){
    			if(StringUtils.isEmpty(json)){
    				jsonFile = getDualaxisChartPath();
        			try {
            			json = ProjectUtils.getString(ProjectUtils.getByte(jsonFile));
            		} catch (IOException e) {
            			e.printStackTrace();
            		}
        		}
    			outDualaxisChartLayJpeg(response, json, name);
    		} else if(ChartGlobal.RIGHT_BOTTOM.equals(parameter)
    				|| ChartGlobal.RIGHT_BOTTOM_DEMO.equals(parameter)){
    			if(StringUtils.isEmpty(json)){
    				jsonFile = getPieChartLayPath();
        			try {
            			json = ProjectUtils.getString(ProjectUtils.getByte(jsonFile));
            		} catch (IOException e) {
            			e.printStackTrace();
            		}
        		}
    			outPieChartLayJpeg(response, json, name);
    		} else {
    			outBarChartLayJpeg(response,json,name);
    		}
    		returnRequest(response,name);
    	}
    	else{
    		returnErrorRequest(response,"parameter is not avaliable!");
    	}
    }
    protected void outDualaxisChartLayJpeg(HttpServletResponse response,String json,String name) throws IOException{
    	 OutputStream os = new FileOutputStream(name);
    	 Boolean beError = false;
    	 try {
    		 ChartUtilities.writeChartAsJPEG(os, new DualaxisChartLay(json).getJFreeChart(), width, height);
    	 } catch (Exception e) {
			beError = true;
			System.out.println(e.getMessage());
    	 }finally{
			os.close();
			if(beError){
				PictrueProess p1 = new PictrueProess();
				p1.createJpegImage(name);
			}
		}
    }
    protected void outLineChartLayJpeg(HttpServletResponse response,String json,String name) throws IOException{
	   	OutputStream os = new FileOutputStream(name);
	   	Boolean beError = false;
	   	try {
	   		ChartUtilities.writeChartAsJPEG(os, new LineChartLay(json).getJFreeChart(), width, height);
	   	} catch (Exception e) {
			beError = true;
			System.out.println(e.getMessage());
	   	}finally{
			os.close();
			if(beError){
				PictrueProess p1 = new PictrueProess();
				p1.createJpegImage(name);
			}
		}
	}
    protected void outPieChartLayJpeg(HttpServletResponse response,String json,String name) throws IOException{
    	
	   	OutputStream os = new FileOutputStream(name);
	   	Boolean beError = false;
	   	try {
	   		ChartUtilities.writeChartAsJPEG(os, new PieChartLay(json).getJFreeChart(), width, height);
	   	} catch (Exception e) {
			beError = true;
			System.out.println(e.getMessage());
	   	}finally{
			os.close();
			if(beError){
				PictrueProess p1 = new PictrueProess();
				p1.createJpegImage(name);
			}
		}
	}
    protected void outBarChartLayJpeg(HttpServletResponse response,String json,String name) throws IOException{
    	//图片是文件格式的,故要用到FileOutputStream用来输出.
    	 OutputStream os = new FileOutputStream(name);
    	//使用一个面向application的工具类,将chart转换成JPEG格式的图片.第3个参数是宽度,第4个参数是高度.
    	 Boolean beError = false;
    	 try {
    		 ChartUtilities.writeChartAsJPEG(os, new BarChartLay(json).getJFreeChart(), width, height);
    	 } catch (Exception e) {
			beError = true;
			System.out.println(e.getMessage());
    	 }finally{
			os.close();//关闭输出流
			if(beError){
				PictrueProess p1 = new PictrueProess();
				p1.createJpegImage(name);
			}
		}
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
    protected String getBarChartLayPath(){
    	//创建默认的样式
    	URL url = BarChart.class.getResource("BarChart2.json");
		return url.getPath();
    }
    protected String getDualaxisChartPath(){
    	//创建默认的样式
		URL url = DualaxisChart.class.getResource("DualaxisChart.json");
		return url.getPath();
    }
    protected String getPieChartLayPath(){
    	//创建默认的样式
    	URL url = PieChart.class.getResource("PieChart.json");
		return url.getPath();
    }
    protected String getLineChartLayPath(){
    	//创建默认的样式
    	URL url = LineChart.class.getResource("LineChart.json");
		return url.getPath();
    }
}
