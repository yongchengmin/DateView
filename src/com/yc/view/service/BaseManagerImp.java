package com.yc.view.service;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class BaseManagerImp implements BaseManager,ApplicationContextAware{
	protected ApplicationContext applicationContext;
	
	
	
	
	
	
	
	
	
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}
}
