package com.yc.view.spring.factory;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

public class TransactionManagerFactoryBean implements FactoryBean, BeanFactoryAware {

	private String transactionManagerBeanName;
	
	private final String DEFAULT = "transactionManager";
	
	private BeanFactory beanFactory;
	
	public Object getObject() throws Exception {
		if(transactionManagerBeanName == null) {
			transactionManagerBeanName = DEFAULT;
		}
		return beanFactory.getBean(transactionManagerBeanName);
	}

	@SuppressWarnings("rawtypes")
	public Class getObjectType() {
		return PlatformTransactionManager.class;
	}

	public boolean isSingleton() {
		return false;
	}

	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
	}

	public String getTransactionManagerBeanName() {
		return transactionManagerBeanName;
	}

	public void setTransactionManagerBeanName(String transactionManagerBeanName) {
		this.transactionManagerBeanName = transactionManagerBeanName;
	}

}
