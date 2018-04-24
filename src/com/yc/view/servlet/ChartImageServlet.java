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

import org.jfree.chart.ChartUtilities;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.yc.view.chart.BarChartLay;
import com.yc.view.service.ChartJdbcInit;
import com.yc.view.utils.ChartGlobal;
import com.yc.view.utils.ProjectUtils;
//http://localhost:8080/DateView/chartJson?parameter=left_top_json
public class ChartImageServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected static ApplicationContext ac;
	protected ChartJdbcInit chartJdbcInit;

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
    	
    	/*int i = 0;
    	List list = chartJdbcInit.dataNo0QueryForList("select t.id,t.code,t.name from DRIVER t where t.creator = '王欢'");
    	Iterator iMes = list.iterator();
    	while(iMes.hasNext()){
    		Map m = (Map) iMes.next();
    		Long id = ((BigDecimal) m.get("ID")).longValue();
    		String code = m.get("code")==null?"-": m.get("code").toString();
    		String name = m.get("name")==null?"-": m.get("name").toString();
    		System.out.println(id+","+code+","+name);
    		i++;
    		if(i>=10){
    			break;
    		}
    	}*/
    	
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
