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

import com.yc.utils.files.PropertiesUtil;
import com.yc.view.service.ChartPngInit;
import com.yc.view.utils.ChartGlobal;

public class HtmlTemplate {
	static String pathtemplate = null,pathhtml = null;
	public HtmlTemplate(String r1) throws IOException {
		toHTml(r1);
	}

	static {
		URL urltemplate = HtmlTemplate.class.getResource("index.template");
		pathtemplate = urltemplate.getPath();
		
//		pathhtml = PropertiesUtil.getPropertiesKey(ChartPngInit.parentPath, ChartGlobal.SIZE_TWO)+"//index.html";
		pathhtml = ChartPngInit.sizetwo+"//index.html";
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
		if(pathhtml == null){
			pathhtml = PropertiesUtil.getPropertiesKey(ChartPngInit.parentPath, ChartGlobal.SIZE_TWO)+"//index.html";
		}
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
