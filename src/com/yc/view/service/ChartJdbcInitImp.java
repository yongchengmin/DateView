package com.yc.view.service;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

public class ChartJdbcInitImp extends BaseManagerImp implements ChartJdbcInit{
	private JdbcTemplate jdbcExtendDataNo0;//dataSource
	
	
	/** JdbcTemplate.queryForList(String sql)
	 If you add the appropriate data source and want to perform queryForList method, then increase the number here
	 **/
	public static final String QFL = "QueryForList";
	@SuppressWarnings("rawtypes")
	public List dataNo0QueryForList(String sql){
		return jdbcExtendDataNo0.queryForList(sql);
	}
	
	
	/***JdbcTemplate.execute(String sql).
	 If you add the appropriate data source and want to perform execute method, then increase the number here
	 **/
	public static final String EXT = "Execute";
	public void dataNo0Execute(String sql){
		jdbcExtendDataNo0.execute(sql);
	}
	
	

	public JdbcTemplate getJdbcExtendDataNo0() {
		return jdbcExtendDataNo0;
	}

	public void setJdbcExtendDataNo0(JdbcTemplate jdbcExtendDataNo0) {
		this.jdbcExtendDataNo0 = jdbcExtendDataNo0;
	}
	
	
}
