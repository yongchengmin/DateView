package com.yc.view.template;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.Date;

import com.yc.view.utils.ChartGlobal;
import com.yc.view.utils.DateUtil;

public class HtmlTemplate {
	static String pathtemplate = null,pathhtml = null;
	public HtmlTemplate(String r1) throws IOException {
		toHTml(r1);
	}

	static {
		URL urltemplate = HtmlTemplate.class.getResource("index.template");
		pathtemplate = urltemplate.getPath();
	}
	public void toHTml(String r1) throws IOException{
		if(pathtemplate==null){
			URL urltemplate = HtmlTemplate.class.getResource("index.template");
			pathtemplate = urltemplate.getPath();
		}
		String template= readByte(pathtemplate);
		String replacetr = new String();
		replacetr=template;
		replacetr=replacetr.replace("{block left top png}",r1);//动态
		replacetr=replacetr.replace("{title}","新港数据监控C-4 "+DateUtil.format(new Date(), DateUtil.h_m_s));//动态
//		String redirtHtml = "index"+DateUtil.format(new Date(), DateUtil.hhmmssSSS)+ChartGlobal.htmlEnd;
		String redirtHtml = "redirtHtml"+ChartGlobal.htmlEnd;
		replacetr=replacetr.replace("{redirtHtml}",redirtHtml);//动态
		createTxt(pathhtml, replacetr.trim(), ChartGlobal.encodeing);
	}
	
	public String readByte(String pathName){
  		InputStream in = null ;OutputStream out = null ;
  		StringBuffer sf = new StringBuffer();
  		try  {  
	        File localFile = new  File(pathName);  
	        in = new  BufferedInputStream( new  FileInputStream(localFile));  
	        byte [] buffer =  new   byte [ 1024 ];  
	        while  (in.read(buffer) != - 1 ) {  
	        	out = new ByteArrayOutputStream(1024);
	        	out.write(buffer);
	        	sf.append(out);
//	        	System.out.println(out.toString()+"\n"+"<!-- **********************************************  -->");
	            buffer = new byte [ 1024 ];  
	        }  
	    } catch  (Exception e) {  
	        e.printStackTrace();  
	    } finally  {  
	        try  { 
	        	if(out!=null){
	        		out.close();
	        	}
	        	if(in!=null){
	        		in.close(); 
	        	}
	        } catch  (IOException e) {  
	            e.printStackTrace();  
	        }  
	    }
		return sf.toString();
  	}
    
    public void createTxt(String file,String row,String character){
    	OutputStreamWriter osw = null;
    	try {
    		osw = new OutputStreamWriter(new FileOutputStream(file,false),character);
    		osw.write(row);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
	       	 if(osw!=null)//if(osw!=null)
	             try {
	             	osw.close();//osw.close();
	             } catch (IOException e) {
	                 e.printStackTrace();
	             }
	    }
    }
    
    public static void main(String[] args) throws IOException {
		new HtmlTemplate("000001.png");
	}
}
