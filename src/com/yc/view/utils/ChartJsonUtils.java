package com.yc.view.utils;

import java.io.File;

import net.sf.json.JSONObject;

import com.yc.utils.esbUtils.FileUtil;

public class ChartJsonUtils {
	public static String TITLE = "title";
	/**主标题下内容*/
	public static String CATEGORYAXISLABEL = "categoryAxisLabel";
	/**Y轴(左)描述*/
	public static String VALUEAXISLABEL = "valueAxisLabel";
	/**Y轴(右)描述*/
	public static String NUMBERAXIS = "numberaxis";
	
	public static String CATEGORIES = "categories";
	public static String PILLAR = "pillar";//柱状
	public static String LINE = "line";//折线
	
	public static String Y = "y";
	public static String X = "x";
	public static String DATE = "date";
	public static String COMMA = ",";
	
	public static void main(String[] args) {
		String json = FileUtil.readStrTxt(new File("D://json//BarChart.json"), ChartGlobal.encodeing);
//		System.out.println(json+"\n"+"-----------------------------------------------");
		JSONObject jsonObject = JSONObject.fromObject(json);  
//    	String categories = jsonObject.getString("categories");
//    	System.out.println(categories);//["Tokyo","New York","London","Berlin"]
//    	String x = jsonObject.getString("x");
//    	System.out.println(x);//["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"]
    	String y = jsonObject.getString("y");
//    	System.out.println(y);//{"Tokyo":[49.9,71.5,106.4,129.2,144,176,135.6,148.5,216.4,194.1,95.6,54.4],"New York":[83.6,78.8,98.5,93.4,106,84.5,105,104.3,91.2,83.5,106.6,92.3],"London":[48.9,38.8,39.3,41.4,47,48.3,59,59.6,52.4,65.2,59.3,51.2],"Berlin":[42.4,33.2,34.5,39.7,52.6,75.5,57.4,60.4,47.6,39.1,46.8,51.1]}
		
    	JSONObject jsonY = JSONObject.fromObject(y);
    	String Tokyo = jsonY.getString("Tokyo");
//    	System.out.println(Tokyo);//[49.9,71.5,106.4,129.2,144,176,135.6,148.5,216.4,194.1,95.6,54.4]
    	String t = Tokyo.substring(1, Tokyo.length()-1);
    	System.out.println(t);//49.9,71.5,106.4,129.2,144,176,135.6,148.5,216.4,194.1,95.6,54.4
	}
}
