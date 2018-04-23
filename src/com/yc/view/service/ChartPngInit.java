package com.yc.view.service;

import java.util.Date;

import com.yc.view.utils.DateUtil;

public class ChartPngInit {
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
		while(true){
			System.out.println("C-4-LFET TOP >"+DateUtil.format(new Date(), DateUtil.dmy_hms));
//			ProjectUtils.deleteFile(sizeone, ChartGlobal.imageEnd);
//			ProjectUtils.deleteFile(sizetwo, ChartGlobal.imageEnd);
			
			try {
				Thread.sleep(1000*2);
			} catch (InterruptedException e) {
				break;
			}
			try {
				Thread.sleep(1000*20);
			} catch (InterruptedException e) {
				break;
			}
		}
	}
	public static void pngInit02Syn(){
		while(true){
			System.out.println("C-4-right top "+DateUtil.format(new Date(), DateUtil.dmy_hms));
			try {
				Thread.sleep(1000*20);
			} catch (InterruptedException e) {
				break;
			}
		}
	}
}
