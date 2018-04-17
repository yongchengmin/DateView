package com.yc.view.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateUtil {
	public static String dmy_hm = "yyyy-MM-dd HH:mm";
	public static String dmy = "yyyyMMdd";
	public static String y_m_d = "yyyy-MM-dd";
	public static String dmy_hms = "yyyy-MM-dd HH:mm:ss";
	
	public static String format(Date date,String format) {
		DateFormat df = new SimpleDateFormat(format);
		return df.format(date);
	}
}