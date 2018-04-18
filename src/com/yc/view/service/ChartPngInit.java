package com.yc.view.service;

import java.util.Date;

import org.jfree.chart.JFreeChart;

import com.yc.utils.files.PropertiesUtil;
import com.yc.view.chart.demo.BarChart;
import com.yc.view.chart.demo.DualaxisChart;
import com.yc.view.utils.ChartGlobal;
import com.yc.view.utils.ChartUtils;
import com.yc.view.utils.DateUtil;

public class ChartPngInit {
	//将来这块要可配置,目前是只对windows系统
	static String parentPath = "C://Users//"+ChartGlobal.PORTMESG;//发布路径
//	static String parentPath = "D://jac_gitee_v002//DateView//"+ChartGlobal.PORTMESG;//开发路径,慎用(担心发布前忘记还原)
	
	static String sizeone = null;
	static String sizetwo = null;
	static{
		sizeone = PropertiesUtil.getPropertiesKey(parentPath, ChartGlobal.SIZE_ONE);
		sizetwo = PropertiesUtil.getPropertiesKey(parentPath, ChartGlobal.SIZE_TWO);
		System.out.println("sizeonePath is "+sizeone);
		System.out.println("sizetwoPath is "+sizetwo);
	}
	public static void pngInit01(){
		new Thread(new Runnable() {
			@Override
			public void run() {
				pngInit01Syn();
			}
		}).start();
	}
	
	public static void pngInit02(){
		new Thread(new Runnable() {
			@Override
			public void run() {
				pngInit02Syn();
			}
		}).start();
	}
	
	public static void pngInit01Syn(){
		if(sizeone==null){
			sizeone = PropertiesUtil.getPropertiesKey(parentPath, ChartGlobal.SIZE_ONE);
		}
		if(sizetwo==null){
			sizetwo = PropertiesUtil.getPropertiesKey(parentPath, ChartGlobal.SIZE_TWO);
		}
		while(true){
			System.out.println("C-4-lfet top "+DateUtil.format(new Date(), DateUtil.dmy_hms));
			JFreeChart chart = new BarChart().getJFreeChart();
			ChartUtils.saveAsFile(chart, sizeone+"/001.png", 700, 320);
			ChartUtils.saveAsFile(chart, sizetwo+"/01.png", 1024, 420);
			try {
				Thread.sleep(1000*20);
			} catch (InterruptedException e) {
				break;
			}
		}
	}
	public static void pngInit02Syn(){
		if(sizeone==null){
			sizeone = PropertiesUtil.getPropertiesKey(parentPath, ChartGlobal.SIZE_ONE);
		}
		if(sizetwo==null){
			sizetwo = PropertiesUtil.getPropertiesKey(parentPath, ChartGlobal.SIZE_TWO);
		}
		while(true){
			System.out.println("C-4-right top "+DateUtil.format(new Date(), DateUtil.dmy_hms));
			JFreeChart chart = new DualaxisChart().getJFreeChart();
			ChartUtils.saveAsFile(chart, sizeone+"/002.png", 700, 320);
			ChartUtils.saveAsFile(chart, sizetwo+"/02.png", 1024, 420);
			try {
				Thread.sleep(1000*20);
			} catch (InterruptedException e) {
				break;
			}
		}
	}
}
