package com.yc.view.chart.demo;

import java.net.URL;

public class DefaultDemo {
	public static String defaultDemoPath = null;
	static {
		setDefaultDemoPath();
	}
	
	public static void setDefaultDemoPath(){
		URL url = DefaultDemo.class.getResource("default_demo.json");
		defaultDemoPath = url.getPath();
	}
}
