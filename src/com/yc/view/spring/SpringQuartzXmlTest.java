package com.yc.view.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringQuartzXmlTest{
	
	protected ApplicationContext applicationContext;

    public static void main(String[] args) {
        // 启动Spring 容器
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
                "classpath*:serviceContext.xml" +
                "classpath:dataSourceContext.xml");
        System.out.println("---");
    }
    
}