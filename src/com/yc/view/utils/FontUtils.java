package com.yc.view.utils;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

public class FontUtils {

	/**
	 *  引入自定义的字体
	 * @param fontStyle 字体样式
	 * @param fontSize  字体大小
	 * @return
	 */
	public static Font getFont(int fontStyle, int fontSize) {
	    Font font = null;
	    FileInputStream fileInputStream = null;
	    URL url = FontUtils.class.getResource("ZQKZYT1.otf");
	    String fontUrl = "";
	    try {
	        switch (fontStyle) {
	            case 1:
	                //文悦新青年体
	                fontUrl = url.getPath();
	                break;
	            default:
	                fontUrl = url.getPath();
	                break;
	        }
	        fileInputStream = new FileInputStream(new File(fontUrl));
	        Font tempFont = Font.createFont(Font.TRUETYPE_FONT,fileInputStream);
	        font = tempFont.deriveFont(fontSize);
	        GraphicsEnvironment ge = GraphicsEnvironment
	                .getLocalGraphicsEnvironment();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            fileInputStream.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    return font;
	}
	
	/**
	 * 将字符串按照自定义的间隔写入
	 * @param str   目标字符串
	 * @param x     写入的位置的x轴
	 * @param y     写入的位置的y轴
	 * @param rate  写入间隔
	 * @param g     画布
	 * @param fontSize  字体的大小
	 */
	public static void drawString(String str,int x,int y,int rate, Graphics2D g,int fontSize){
	    String tempStr="";
	    int tempx=x;
	    int tempy=y;
	    while (str.length()>0){
	        tempStr=str.substring(0, 1);
	        str=str.substring(1, str.length());
	        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
	        g.drawString(tempStr, tempx, tempy);
	        tempx = tempx + fontSize - rate;
	    }
	}
}
