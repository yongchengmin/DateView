package com.yc.view.job;

import java.io.IOException;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.yc.view.sql.SqlLoad;
import com.yc.view.sql.SqlPackageScanner;
import com.yc.view.sql.SqlpathPackageScanner;

public class ViewTimerJob implements ServletContextListener{
	Timer timer = new Timer();
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		timer.cancel();  
        event.getServletContext().log("ViewTimerJob is destoryed"); 
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		//2秒后开始执行,然后每隔5秒执行一次。
//		timer.scheduleAtFixedRate(new TimerTask() {  
//		    @Override  
//		    public void run() {  
//		        System.out.println("Timer is running "+DateUtil.format(new Date(), DateUtil.dmy_hms));  
//		    }  
//		}, 2000, 5000); 
		
		timer.schedule(new TimerTask(){  
		    public void run(){
//		    	ChartPngInit.pngInit01();
//		    	System.out.println("pngInit001 running "+DateUtil.format(new Date(), DateUtil.dmy_hms));  
//		    	ChartPngInit.pngInit02();
//		    	System.out.println("pngInit002 running "+DateUtil.format(new Date(), DateUtil.dmy_hms));
		    	
		    	SqlPackageScanner scan = new SqlpathPackageScanner(SqlLoad.class.getPackage().getName());
		    	Map<String, String> maps = null;
				try {
					maps = scan.getFullyQualifiedSqlNameList();
				} catch (IOException e) {
					e.printStackTrace();
				}
				if(maps != null){
					SqlLoad.initSqlPro(maps);
				}
		    }  
		 }, 2000);//启动2秒后开始执行,就一次
//        System.out.println("ִviewTimerJob initialized time:" + DateUtil.format(new Date(), DateUtil.dmy_hms));  
        event.getServletContext().log("viewTimerJob is initialized...");  
	}

}
