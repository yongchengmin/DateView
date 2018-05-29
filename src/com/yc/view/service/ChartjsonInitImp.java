package com.yc.view.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import com.yc.utils.esbUtils.JsonTools;
import com.yc.utils.files.PropertiesUtil;
import com.yc.view.exception.BusinessException;
import com.yc.view.sql.SqlContainerKey;
import com.yc.view.sql.SqlLoad;
import com.yc.view.utils.ChartJsonUtils;
import com.yc.view.utils.ProjectUtils;

public class ChartjsonInitImp implements ChartjsonInit{
	protected  final ChartJdbcInit chartJdbcInit;
	public ChartjsonInitImp(ChartJdbcInit chartJdbcInit){
		super();
		this.chartJdbcInit = chartJdbcInit;
	}
	
	static String kround,sqlPath;
	static{
		kround = PropertiesUtil.getPropertiesKey(SqlLoad.sqlContainerPath, SqlContainerKey.KROUND);
//		sqlPath = PropertiesUtil.getPropertiesKey(SqlLoad.sqlLoadPath, kround);
	}
	public String leftTop() {
		if(kround == null){
			kround = PropertiesUtil.getPropertiesKey(SqlLoad.sqlContainerPath, SqlContainerKey.KROUND);
		}
//		System.out.println("kround === "+ kround);
		//轮播逻辑  begin
		String keyName = "";
		String kno = PropertiesUtil.getPropertiesKey(SqlLoad.sqlKeyPath, SqlContainerKey.KNO);
		String[] keys = kround.split(ChartJsonUtils.COMMA);
		if(kno == null){
			keyName = keys[0];
			kno = keys[0];
		} else {
			String[] knos = kno.split(ChartJsonUtils.COMMA);
			if(keys.length == knos.length){
				keyName = keys[0];
				kno = keys[0];
			} else {
				for(String n : knos){
					for(String k : keys){
						if(!k.equals(n)){
							keyName = k;
							kno += ","+k;
							break;
						}
					}
				}
			}
		}
		
//		System.out.println(SqlLoad.sqlKeyPath);
//		System.out.println("kno ====== " + kno);
		try {
			File f= new File(SqlLoad.sqlKeyPath);
			PropertiesUtil.saveKey(f, SqlContainerKey.KNO,kno,new FileInputStream(f));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		//轮播逻辑  end
		sqlPath = PropertiesUtil.getPropertiesKey(SqlLoad.sqlLoadPath, keyName);
		String sql = null;
		try {
			sql = ProjectUtils.getString(ProjectUtils.getByte(sqlPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
//		System.out.println("keyName ====== " + keyName);
//		System.out.println("sqlPath ====== " + sqlPath);
		try {
			chartJdbcInit.dataNo0Execute(sql);
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		
		//select to json
		String json = sqlToJson();
		return json;
//		String sqlPath = ProjectUtils.getPropertiesKey(ChartGlobal.LEFT_TOP);
//		if(sqlPath == null){
//			throw new BusinessException(PropertiesUtil.PROPERTIES_ACCESS_TOKEN+"未配置"+ChartGlobal.LEFT_TOP+"路径");
//		}
	}
	public String sqlToJson(){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
    	List list = chartJdbcInit.dataNo0QueryForList("SELECT TITLE,CATEGORYAXISLABEL,VALUEAXISLABEL FROM LEFT_TOP_HEAD");
    	Iterator iMes = list.iterator();
    	while(iMes.hasNext()){
    		Map m = (Map) iMes.next();
    		String title = m.get("TITLE")==null?"-": m.get("TITLE").toString().trim();
    		String categoryaxislabel = m.get("CATEGORYAXISLABEL")==null?"-": m.get("CATEGORYAXISLABEL").toString().trim();
    		String valueaxislabel = m.get("VALUEAXISLABEL")==null?"-": m.get("VALUEAXISLABEL").toString().trim();
    		paramMap.put("TITLE", title);
    		paramMap.put("CATEGORYAXISLABEL", categoryaxislabel);
    		paramMap.put("VALUEAXISLABEL", valueaxislabel);
    		break;
    	}list.clear();
    	
    	List<String> categoriesS = new ArrayList<String>();
    	List<String> xS = new ArrayList<String>();
    	Map<String,List<Double>> yM = new HashMap<String,List<Double>>();//骏铃南线:[95,24,22]
    	list = chartJdbcInit.dataNo0QueryForList("SELECT CATEGORIES,X,QUANTITY FROM LEFT_TOP_Y ORDER BY LINE,X ASC");
    	iMes = list.iterator();
    	
    	List<Double> l = null;
    	while(iMes.hasNext()){
    		Map m = (Map) iMes.next();
    		String categorie = m.get("CATEGORIES")==null?"-": m.get("CATEGORIES").toString().trim();
    		String x = m.get("X")==null?"-": m.get("X").toString().trim();
    		Double quantity = m.get("QUANTITY")==null?0D: Double.valueOf(m.get("QUANTITY").toString());
    		
			if(yM.containsKey(categorie)){
				l = yM.get(categorie);
			}else {
				l = new ArrayList<Double>();
			}
			l.add(quantity);
			yM.put(categorie, l);
    		
			if(!categoriesS.contains(categorie)){
				categoriesS.add(categorie);
			}
    		if(!xS.contains(x)){
    			xS.add(x);
    		}
    	}list.clear();
    	paramMap.put("CATEGORIES", categoriesS);
    	paramMap.put("X", xS);
    	paramMap.put("Y", yM);
    	String json = JsonTools.getCreateJson(paramMap);
    	paramMap.clear();
//    	System.out.println(json);
    	/**
    	 * {
			"CATEGORYAXISLABEL":"-",
			"CATEGORIES":[
				"3分钟以内",
				"任务数",
				"5分钟以上",
				"3-5分钟"
			],
			"Y":{
				"3分钟以内":[
					78
				],
				"任务数":[
					95
				],
				"5分钟以上":[
					6
				],
				"3-5分钟":[
					10
				]
			},
			"X":"[2018-04-25 09:26:53]",
			"VALUEAXISLABEL":"未执行(个)",
			"TITLE":"装车单未执行监控"
			}
    	 */
		return json;
	}

	public static void main(String[] args) {
		String infoBodyStr = null;
//		infoBodyStr =JsonTools.getCreateJson("t_item", getListMap());
//		infoBodyStr = JsonTools.getCreateJson("t_item", getListArray());
		infoBodyStr = JsonTools.getCreateJson("t_item", getListList());
		System.out.println(infoBodyStr);
	}
	//String infoBodyStr = JsonTools.getCreateJson("t_item", getListMap());
	public static List<Map<String, Object>> getListMap(){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (int i = 1; i <= 1; i++)
		{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("ebeln", "6010000019");
			map.put("matnr", "ES8111SAAICN410111010000000001HPWLOB");
			list.add(map);
		}
		return list;
		/**
		 * 	{
			    "t_item":[
			        {
			            "ebeln":"10000000",
			            "matnr":"ES80000000001"
			        }
			    ]
			}
		 */
	}
	public static List<String> getListArray(){
		List<String> list = new ArrayList<String>();
		list.add("tt1");
		list.add("tt2");
		list.add("tt3");
		return list;
		/**
		 * {
			  "t_item":[
			  		"tt1",
			   		"tt2",
			   		"tt3"
			  ]
		 * }
		 */
	}
	
	public static Map<String,List<Integer>> getListList(){
		Map<String,List<Integer>> map = new HashMap<String,List<Integer>>();
		
		for(int i = 0 ; i<=2 ; i++){
			List<Integer> l = new ArrayList<Integer>();
			l.add(i);
			map.put("k"+i, l);
		}
		
		/*
		 * 
			{
				"t_item": {
					"k0": [0],
					"k1": [1],
					"k2": [2]
				}
			}
		 */
		return map;
	}
	
	@SuppressWarnings("unused")
	private static String resetStr(String str,String separatorAfter,String separatorLast){
		String parameter = org.apache.commons.lang.StringUtils.substringAfter(str, separatorAfter);
		parameter = org.apache.commons.lang.StringUtils.substringBeforeLast(parameter,separatorLast);
		return parameter;
		
	}
	
	@SuppressWarnings("unused")
	private static StringBuffer resetParam(Map<String, Object> paramMap){
		String parameter = org.apache.commons.lang.StringUtils.substringAfter(paramMap.toString(), "{");
		parameter = org.apache.commons.lang.StringUtils.substringBeforeLast(parameter, "}");
		String[] s1 = parameter.split(",");
		StringBuffer sb = new StringBuffer(s1.length);
		for(String ss : s1){
			sb.append(ss.trim()).append("&");
		}
		return sb;
	}
}
