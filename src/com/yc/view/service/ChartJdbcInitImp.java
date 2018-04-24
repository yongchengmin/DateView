package com.yc.view.service;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

public class ChartJdbcInitImp extends BaseManagerImp implements ChartJdbcInit{
	private JdbcTemplate jdbcExtendDataNo0;//dataSource
	
	@SuppressWarnings("rawtypes")
	public List dataNo0QueryForList(String sql){
		return jdbcExtendDataNo0.queryForList(sql);
	}

	public JdbcTemplate getJdbcExtendDataNo0() {
		return jdbcExtendDataNo0;
	}

	public void setJdbcExtendDataNo0(JdbcTemplate jdbcExtendDataNo0) {
		this.jdbcExtendDataNo0 = jdbcExtendDataNo0;
	}
	
	
}
