package com.yc.view.utils;

import java.io.File;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.yc.utils.esbUtils.FileUtil;

public class CopyJarToLib {

	   private static String rootPath = "";  
	   
	   public static void main(String[] args) {
		   	String path = CopyJarToLib.class.getResource("CopyJarToLib.class").toString()
		   			,classPath = "",libPath = "";  
	        path = URLDecoder.decode(path);  
	        path.replaceAll("\\\\", "/");
	        //file:/D:/jac_gitee_v002/DateView/WebContent/dateView/WEB-INF/classes/com/yc/view/utils/CopyJarToLib.class
	        int index = path.indexOf("classes");
	        path = path.substring(0, index);
	        if (path.startsWith("file")) {  
	            path = path.substring(5);// /D:/jac_gitee_v002/DateView/WebContent/dateView/WEB-INF/
	        }  
//	        if (path.endsWith("/") || path.endsWith("\\")) {  
//	            path = path.substring(0, path.length() - 1);//  /D:/jac_gitee_v002/DateView
//	        }
	        // linux环境下第一个/是需要的  
	        String os = System.getProperty("os.name").toLowerCase();  
	        if (os.startsWith("win")) {  
	            path = path.substring(1);
	        }
	        System.out.println(path);// D:/jac_gitee_v002/DateView/WebContent/dateView/WEB-INF/
	        
	        libPath = path+"lib/";
	        System.out.println("libPath :"+libPath);// D:/jac_gitee_v002/DateView/WebContent/dateView/WEB-INF/lib/
	        
	        index = path.indexOf("WebContent");
	        classPath = path.substring(0, index);
	        classPath += ".classpath";
	        System.out.println("classpath :"+classPath);// D:/jac_gitee_v002/DateView/.classpath
	        File file = new File(classPath);
			if(!file.exists()){
				System.out.println(classPath+"  is null");
				return;
			}
			final String xml = FileUtil.readStrTxt(file, ChartGlobal.encodeing);
//			System.out.println(xml);
			
			String MAVEN_REPO = "D:/dev/maven-1.0.2/repository";
			String prefix = "MAVEN_REPO";
			
			Document document = null;
			try {
				document = DocumentHelper.parseText(XMLUtils.formatXML(xml.trim()));
				
				if (null != document) {
					Element root = document.getRootElement();
//					XMLUtils.listNodes(root);
					List<String> paths = new ArrayList<String>();
					paths = XMLUtils.getPath(root, prefix, paths);
					String jar = null;
					for(String s : paths){
//						System.out.println(s);// MAVEN_REPO/cxf/jars/cxf-2.6.2.jar
						jar = StringUtils.substringAfterLast(s, "/");
						s = s.replace(prefix, MAVEN_REPO);
//						System.out.println(s);//  D:/dev/maven-1.0.2/repository/cxf/jars/cxf-2.6.2.jar
						File sourceFile = new File(s);
						File destFile = new File(libPath+jar);
						ProjectUtils.copyFile(sourceFile, destFile);
					}
				}
			} catch (DocumentException e) {
				e.printStackTrace();
			}
			
	   }
	    private CopyJarToLib() {  
	        init();  
	    }  
	  
	    @SuppressWarnings("deprecation")  
	    private static void init() {  
	        String path = CopyJarToLib.class.getResource("CopyJarToLib.class")  
	                .toString();  
	        path = URLDecoder.decode(path);  
	        path.replaceAll("\\\\", "/");
	        //file:/D:/jac_gitee_v002/DateView/WebContent/dateView/WEB-INF/classes/com/yc/view/utils/CopyJarToLib.class
	        int index = path.indexOf("WEB-INF");  
	        if (index == -1) {  
	            index = path.indexOf("bin");  
	        }  
	        if (index == -1) {  
	            index = path.indexOf("lib");  
	        }  
	        if (index == -1) {  
	            int index2 = path.indexOf("jar!");  
	            if (index2 != -1) {  
	                path = path.substring(0, index2);  
	                System.out.println(path);  
	                System.out.println(File.separator);  
	                index = path.lastIndexOf("/");  
	                System.out.println(index);  
	            }  
	        }  
	        path = path.substring(0, index);// file:/D:/jac_gitee_v002/DateView/WebContent/dateView/
	        if (path.startsWith("jar")) {  
	            path = path.substring(9);  
	        }  
	        if (path.startsWith("file")) {  
	            path = path.substring(5);// /D:/jac_gitee_v002/DateView/WebContent/dateView/
	        }  
	        if (path.endsWith("/") || path.endsWith("\\")) {  
	            path = path.substring(0, path.length() - 1);//  /D:/jac_gitee_v002/DateView/WebContent/dateView
	        }  
	        // linux环境下第一个/是需要的  
	        String os = System.getProperty("os.name").toLowerCase();  
	        if (os.startsWith("win")) {  
	            path = path.substring(1);//  D:/jac_gitee_v002/DateView/WebContent/dateView
	        }  
	        rootPath = path;  
	    }  
	  
	    /** 
	     * 获取应用的根目录，路径分隔符为/，路径结尾无/ 
	     *  
	     * @return 
	     */  
	    public static String getProjectPath() {  
	        if ("".equals(rootPath)) {  
	            init();  
	        }  
	        return rootPath;// D:/jac_gitee_v002/DateView/WebContent/dateView
	    }  
	  
	}
