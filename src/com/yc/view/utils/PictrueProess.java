package com.yc.view.utils;

/**
* 利用javax.imageio包生成图片的基本用法
*/ 
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.springframework.util.Assert;

public class PictrueProess{
	
	public void createImage(int x,int y,int month,int output){
		BufferedImage buf = new BufferedImage(600,300,BufferedImage.TYPE_INT_BGR);
		Graphics2D g2 = buf.createGraphics();
		g2.setPaint(Color.white);  //设好背景色
		g2.fillRect(0, 0, 600, 300); //把背景色弄成白的
		g2.setPaint(Color.red);  //设置字体颜色
		g2.drawRect(x, y, month, output); //画1
		g2.drawRect(x+20, y, month, output + 20);//画2
		g2.drawRect(x+40, y, month, output + 40);//画3
		g2.dispose(); //清停
		
		try {
			ImageIO.write(buf, "jpeg", new File("test.jpeg"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	public void createJpegImage(String imageName){
		BufferedImage buf = new BufferedImage(1024,720,BufferedImage.TYPE_INT_BGR);
		Graphics2D g2 = buf.createGraphics();
		g2.setPaint(Color.white);  //设好背景色
		g2.setPaint(Color.red);
		g2.setStroke(new BasicStroke(5));  
        g2.setFont(new Font("Serif", Font.PLAIN, 13)); 
        
      //写入标题，标题引入自定义字体
        // 微软雅黑 粗体显示 大小25
        Font font = new Font("微软雅黑", 0, 25);
        g2.setColor(Color.decode("#333333"));
        g2.setFont(font);
        
        FontUtils.drawString("something is error",20,105,6,g2,40);
        FontUtils.drawString("up your json commit",30,205,6,g2,40);
        FontUtils.drawString("if not ok,call me",40,305,6,g2,40);
        FontUtils.drawString("*_* *_* *_*",40,405,6,g2,40);
        try {
			ImageIO.write(buf, "jpeg", new File(imageName));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	public void urlJpegImage(String urlPath,String imageName){
		BufferedImage image = null;    
        try {    
    
            URL url = new URL(urlPath);    
            image = ImageIO.read(url);    
//            ImageIO.write(image, "jpg", new File("classicplus.jpg"));    
//            ImageIO.write(image, "gif", new File("classicplus.gif"));    
//            ImageIO.write(image, "png", new File("classicplus.png"));
            ImageIO.write(image, "jpeg", new File(imageName));
        } catch (IOException e) {    
            e.printStackTrace();    
        }    
	}
	/**
     * 将{@link BufferedImage}生成formatName指定格式的图像数据
     * @param source
     * @param formatName 图像格式名，图像格式名错误则抛出异常
     * @return
     */
    public static byte[] wirteBytes(BufferedImage source,String formatName){
        Assert.notNull(source, "source");
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Graphics2D g = null;
        try {
            for(BufferedImage s=source;!ImageIO.write(s, formatName, output);){
                if(null!=g){
                	throw new IllegalArgumentException(String.format("not found writer for '%s'",formatName));
                }
                s = new BufferedImage(source.getWidth(),
                        source.getHeight(), BufferedImage.TYPE_INT_RGB);
                g = s.createGraphics();
                g.drawImage(source, 0, 0,null);             
            }               
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (null != g)
                g.dispose();
        }
        return output.toByteArray();        
    }
	
	public static void main(String[] args){
		PictrueProess p1 = new PictrueProess();
//		p1.createImage(100,100,20, 60);
//		p1.urlJpegImage("http://220.178.49.203:8978/jac_parts_wms/images/login/login_1_1_log.jpg","left_top_demo"+".jpeg");
		p1.createJpegImage("error.jpeg");
	}
}