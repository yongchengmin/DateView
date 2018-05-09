package com.yc.view.job;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;

public abstract class AbstractEdiJobHelper implements ApplicationListener,ApplicationContextAware{
	protected final Log logger = LogFactory.getLog(this.getClass());
	/** 服务是否启动 */
	protected boolean isApplicationAvailable = false;
	/** 线程默认休息时间 10s */
	protected int refreshInterval = 10000;
	/** 休息间隔间执行的条数 */
	protected int waitingThreshold = 25;
	/** 线程默认处理量 */
	protected int defaultProcessCount = 50;
	protected ApplicationContext applicationContext;
	
	public int getRefreshInterval() {
		return refreshInterval;
	}

	public void setRefreshInterval(int refreshInterval) {
		this.refreshInterval = refreshInterval;
	}
	public int getWaitingThreshold() {
		return waitingThreshold;
	}
	public void setWaitingThreshold(int waitingThreshold) {
		this.waitingThreshold = waitingThreshold;
	}
	public int getDefaultProcessCount() {
		return defaultProcessCount;
	}
	public void setDefaultProcessCount(int defaultProcessCount) {
		this.defaultProcessCount = defaultProcessCount;
	}

	public void onApplicationEvent(ApplicationEvent event) {
		if (event instanceof ContextClosedEvent) {
			logger.info("Context closing......................");
			isApplicationAvailable = false;
		} else if (event instanceof ContextRefreshedEvent) {
			logger.info("Context starting......................");
			isApplicationAvailable = true;
		}
	}
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}
	
}
