package com.yc.view.servlet;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartUtilities;
import com.yc.view.chart.BarChartLay;
import com.yc.view.utils.ChartGlobal;
//http://localhost:8080/DateView/chartJson?parameter=left_top_json
public class ChartImageServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public ChartImageServlet() {
        super();
        // TODO Auto-generated constructor stub
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
    	
//    	System.out.println("根目录所对应的绝对路径"+request.getServletPath());
//    	System.out.println("文件的绝对路径"+request.getSession().getServletContext().getRealPath(request.getRequestURI()));
//    	System.out.println("内容所在路径:"+request.getContextPath());
//    	System.out.println("绝对路径:"+request.getRequestURL());
//    	System.out.println(this.getServletContext().getRealPath("01.json"));
    	String name = null;
    	if(ChartGlobal.LEFT_TOP_JSON.equals(request.getParameter("parameter"))){
    		name = ChartGlobal.LEFT_TOP_JSON+ChartGlobal.imageEnd;
    		outBarChartLayJpeg(name);
    		returnRequest(response,name);
    	}else{
    		returnErrorRequest(response,"parameter is not avaliable!");
    	}
    	
    }
    
    protected void outBarChartLayJpeg(String name) throws IOException{
    	//图片是文件格式的,故要用到FileOutputStream用来输出.
    	 OutputStream os = new FileOutputStream(name);
    	//使用一个面向application的工具类,将chart转换成JPEG格式的图片.第3个参数是宽度,第4个参数是高度.
         ChartUtilities.writeChartAsJPEG(os, new BarChartLay().getJFreeChart(), 1024, 420);
         os.close();//关闭输出流
    }
    
    protected void returnRequest(HttpServletResponse response,String name) throws IOException {
    	try{
    		FileInputStream hFile = new FileInputStream(name); // 以byte流的方式打开文件
    		int i=hFile.available(); //得到文件大小
    		byte data[]=new byte[i];
    		hFile.read(data);  //读数据
    		hFile.close();
    		
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
