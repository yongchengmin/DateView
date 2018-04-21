package com.yc.view.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateUtil {
	public static String dmy_hm = "yyyy-MM-dd HH:mm";
	public static String dmy = "yyyyMMdd";
	public static String y_m_d = "yyyy-MM-dd";
	public static String dmy_hms = "yyyy-MM-dd HH:mm:ss";
	public static String h_m_s = "HH:mm:ss";
	public static String hms = "HHmmss";
	public static String yyMMddHHmmssSSS = "yyMMddHHmmssSSS";
	public static String hhmmssSSS = "HHmmssSSS";
	
	public static String format(Date date,String format) {
		DateFormat df = new SimpleDateFormat(format);
		return df.format(date);
	}
	
	public static void main(String[] args) {
		String redirtHtml = "index"+DateUtil.format(new Date(), DateUtil.hhmmssSSS)+ChartGlobal.htmlEnd;
		System.out.println(redirtHtml);
		redirtHtml = "index"+DateUtil.format(new Date(), DateUtil.hhmmssSSS)+ChartGlobal.htmlEnd;
		System.out.println(redirtHtml);
	}
}