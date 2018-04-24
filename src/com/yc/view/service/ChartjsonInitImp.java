package com.yc.view.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.yc.utils.esbUtils.JsonTools;
import com.yc.view.utils.ProjectUtils;

public class ChartjsonInitImp implements ChartjsonInit{
	protected  final ChartJdbcInit chartJdbcInit;
	public ChartjsonInitImp(ChartJdbcInit chartJdbcInit){
		super();
		this.chartJdbcInit = chartJdbcInit;
	}
	public void leftTop() {
		String sqlPath = "D://jac_gitee_v002//DateView//WebContent//chartTable//left_top_insert.sql";
		String sql = null;
		try {
			sql = ProjectUtils.getString(ProjectUtils.getByte(sqlPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		chartJdbcInit.dataNo0Execute(sql);
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
    	List list = chartJdbcInit.dataNo0QueryForList("select TITLE,CATEGORYAXISLABEL,VALUEAXISLABEL from LEFT_TOP_HEAD");
    	Iterator iMes = list.iterator();
    	while(iMes.hasNext()){
    		Map m = (Map) iMes.next();
    		String title = m.get("TITLE")==null?"-": m.get("TITLE").toString();
    		String categoryaxislabel = m.get("CATEGORYAXISLABEL")==null?"-": m.get("CATEGORYAXISLABEL").toString();
    		String valueaxislabel = m.get("VALUEAXISLABEL")==null?"-": m.get("VALUEAXISLABEL").toString();
    		paramMap.put("TITLE", title);
    		paramMap.put("CATEGORYAXISLABEL", categoryaxislabel);
    		paramMap.put("VALUEAXISLABEL", valueaxislabel);
    		break;
    	}list.clear();
    	
    	list = chartJdbcInit.dataNo0QueryForList("select CATEGORIES,X,QUANTITY from LEFT_TOP_Y");
    	iMes = list.iterator();
    	while(iMes.hasNext()){
    		Map m = (Map) iMes.next();
    		String categories = m.get("CATEGORIES")==null?"-": m.get("CATEGORIES").toString();
    		String x = m.get("X")==null?"-": m.get("X").toString();
    		Double quantity = m.get("QUANTITY")==null?0D: Double.valueOf(m.get("QUANTITY").toString());
    	}
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
}
