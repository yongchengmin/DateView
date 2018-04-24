package com.yc.view.service;

import java.util.List;

public interface ChartJdbcInit {
	public static final String BEAN = "chartJdbcInit";
	@SuppressWarnings("rawtypes")
	List dataNo0QueryForList(String sql);
	
	
	void dataNo0Execute(String sql);
}
