package com.yc.view.sql;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.yc.utils.files.PropertiesUtil;


public class SqlLoad {
	public static String sqlLoadPath = null,sqlLoadPathFile = "sqlLoadPath.properties";
	static {
		setSqlLoadPath();
	}
	public static void initSqlPro(Map<String,String> maps){
		if(maps != null){
			if(sqlLoadPath == null){
				setSqlLoadPath();
			}
			File f= new File(sqlLoadPath);
			Iterator<Entry<String, String>> iterator = maps.entrySet().iterator();
	    	while(iterator.hasNext()){
	    		Entry<String, String> entry = iterator.next();
	        	try {
	    			PropertiesUtil.saveKey(f, entry.getKey(),
		    				entry.getValue(),new FileInputStream(f));
	    		} catch (FileNotFoundException e) {
	    			e.printStackTrace();
	    		}
//	        	String value = PropertiesUtil.getPropertiesKey(sqlLoadPath, entry.getKey());
//	        	System.out.println(entry.getKey()+">>"+value);
	    	}
	    	System.out.println("initSqlPro...successfully");
		}
	}
	
	public static void setSqlLoadPath(){
		URL url = SqlLoad.class.getResource(sqlLoadPathFile);
		sqlLoadPath = url.getPath();
	}

	/*static String sqlPath = null;
	static {
		//此处动态加载所有sql文件到内存
		URL url = SqlLoad.class.getResource("xg_date_1_myc.sql");
		sqlPath = url.getPath();
		Package p = SqlLoad.class.getPackage();
		System.out.println("SqlLoad:.............."+p.getName());//com.yc.view.sql
		
	}
	//动态加载sql内存信息
	public static String loadSqlPath(String sqlName){
		if(sqlPath == null){
			URL url = SqlLoad.class.getResource(sqlName+ChartGlobal.sqlEnd);
			sqlPath = url.getPath();
		}
		return sqlPath;
	}*/
}
