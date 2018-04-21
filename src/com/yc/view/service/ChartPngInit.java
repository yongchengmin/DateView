package com.yc.view.service;

import java.io.IOException;
import java.util.Date;

import org.jfree.chart.JFreeChart;

import com.yc.utils.files.PropertiesUtil;
import com.yc.view.chart.BarChartLay;
import com.yc.view.chart.demo.DualaxisChart;
import com.yc.view.template.HtmlTemplate;
import com.yc.view.utils.ChartGlobal;
import com.yc.view.utils.ChartUtils;
import com.yc.view.utils.DateUtil;
import com.yc.view.utils.ProjectUtils;

public class ChartPngInit {
	//将来这块要可配置,目前是只对windows系统
	public static String parentPath = "C://Users//"+ChartGlobal.PORTMESG;//发布路径
//	static String parentPath = "D://jac_gitee_v002//DateView//"+ChartGlobal.PORTMESG;//开发路径,慎用(担心发布前忘记还原)
	
	public static String sizeone = null;
	public static String sizetwo = null;
	static{
		sizeone = PropertiesUtil.getPropertiesKey(parentPath, ChartGlobal.SIZE_ONE);
		sizetwo = PropertiesUtil.getPropertiesKey(parentPath, ChartGlobal.SIZE_TWO);
		System.out.println(">>>>>>>>>>>>>size one Path is "+sizeone);
		System.out.println(">>>>>>>>>>>>>size two Path is "+sizetwo);
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
			System.out.println("C-4-LFET TOP >"+DateUtil.format(new Date(), DateUtil.dmy_hms));
//			ProjectUtils.deleteFile(sizeone, ChartGlobal.imageEnd);
//			ProjectUtils.deleteFile(sizetwo, ChartGlobal.imageEnd);
			
			String pngRandom = ProjectUtils.getRandomUtils(3),
//				   png_01 = "001"+pngRandom+ChartGlobal.imageEnd,
//				   png_02 = "01"+pngRandom+ChartGlobal.imageEnd;
					png_01 = "001"+ChartGlobal.imageEnd,
					   png_02 = "01"+ChartGlobal.imageEnd;
				
			JFreeChart chart = new BarChartLay(sizetwo+"/01.json").getJFreeChart();//将来动态获取
			ChartUtils.saveAsFile(chart, sizeone+"/"+png_01, 700, 320);
			ChartUtils.saveAsFile(chart, sizetwo+"/"+png_02, 1024, 420);
			try {
				Thread.sleep(1000*2);
			} catch (InterruptedException e) {
				break;
			}
			try {
				new HtmlTemplate(png_02);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
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
