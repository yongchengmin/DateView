
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.tools.ant.util.FileUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.yc.utils.esbUtils.FileUtil;

public class CopyJarToLib {
	public static String encodeing = "UTF-8";

	   private static String rootPath = "";  
	   
	   public static void main(String[] args) {
		   initJar();
	   }
	   private static List<File> copyJar(String path){
		   	String classPath = "",libPath = "";
	        
	        libPath = path+"lib/";
	        System.out.println("libPath :"+libPath);// D:/jac_gitee_v002/DateView/WebContent/dateView/WEB-INF/lib/
	        
	        int index  = path.indexOf("WebContent");
	        classPath = path.substring(0, index);
	        classPath += ".classpath";
	        System.out.println("classpath :"+classPath);// D:/jac_gitee_v002/DateView/.classpath
	        File file = new File(classPath);
			if(!file.exists()){
				System.out.println(classPath+"  is null");
				return null;
			}
			final String xml = FileUtil.readStrTxt(file, encodeing);
//			System.out.println(xml);
			
			String MAVEN_REPO = "D:/dev/maven-1.0.2/repository";
			String prefix = "MAVEN_REPO";
			List<File> libFiles = new ArrayList<File>();
			Document document = null;
			try {
				document = DocumentHelper.parseText(formatXML(xml.trim()));
				
				if (null != document) {
					Element root = document.getRootElement();
//					XMLUtils.listNodes(root);
					List<String> paths = new ArrayList<String>();
					paths = getPath(root, prefix, paths);
					String jar = null;
					for(String s : paths){
//						System.out.println(s);// MAVEN_REPO/cxf/jars/cxf-2.6.2.jar
						jar = StringUtils.substringAfterLast(s, "/");
						s = s.replace(prefix, MAVEN_REPO);
//						System.out.println(s);//  D:/dev/maven-1.0.2/repository/cxf/jars/cxf-2.6.2.jar
						File sourceFile = new File(s);
						File destFile = new File(libPath+jar);
						copyFile(sourceFile, destFile);
						
						libFiles.add(destFile);
					}
				}
			} catch (DocumentException e) {
				e.printStackTrace();
			}
			return libFiles;
	   }
	   private static void zipPath(String zipPath,String srcPathName){
		   ZipCompressor zc = new ZipCompressor(zipPath); 
		   zc.compress(srcPathName);
	   }
	   @SuppressWarnings("deprecation")
	public static void initJar(){
		   String path = CopyJarToLib.class.getResource("CopyJarToLib.class").toString();  
	       path = URLDecoder.decode(path);  
	       path.replaceAll("\\\\", "/");
	       //file:/D:/jac_gitee_v002/DateView/WebContent/dateView/WEB-INF/classes/com/yc/view/utils/CopyJarToLib.class
	       int index = path.indexOf("classes");
	       path = path.substring(0, index);
	       if (path.startsWith("file")) {  
	           path = path.substring(5);// /D:/jac_gitee_v002/DateView/WebContent/dateView/WEB-INF/
	       }  
//	       if (path.endsWith("/") || path.endsWith("\\")) {  
//	           path = path.substring(0, path.length() - 1);//  /D:/jac_gitee_v002/DateView
//	       }
	       // linux环境下第一个/是需要的  
	       String os = System.getProperty("os.name").toLowerCase();  
	       if (os.startsWith("win")) {  
	           path = path.substring(1);
	       }
	       System.out.println(path);// D:/jac_gitee_v002/DateView/WebContent/dateView/WEB-INF/
	       List<File> libFiles = copyJar(path);
		   
		   index  = path.indexOf("WebContent");
		   String zipPath = path.substring(0, index);// D:/jac_gitee_v002/DateView/
		   zipPath += "dateView.zip";
		   
		   index  = path.indexOf("/WEB-INF");
		   String srcPathName = path.substring(0, index);//"D:/jac_gitee_v002/DateView/WebContent/dateView";
		   zipPath(zipPath,srcPathName);
		   
		   clearJar(libFiles);
	   }
	   private static void clearJar(List<File> libFiles){
		   if(libFiles!=null && libFiles.size()>0){
			   for(File file : libFiles){
				   file.delete();
			   }
			   System.out.println("clearJar is done :"+libFiles.size());
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
	    /**
		 * 去除回车
		 * @param in
		 * @return 返回处理后字符串
		 */
		public static String formatXML(String in){
			
			if(StringUtils.isEmpty(in.trim())){
				return "";
			}
			StringBuilder out = new StringBuilder();
			in = in.trim();
			int length =in.length();
			char[] ch;
			ch=new char[length+1];
			ch=in.toCharArray() ;
			for(int i=0;i<ch.length ;i++){
				if(ch[i]!='\r' && ch[i]!='\t' && ch[i]!='\n'){
					out.append(ch[i]);
				}
			}
			return out.toString();
		}
		public static List<String> getPath(Element node,String prefix,List<String> paths){
	    	if("classpathentry".equals(node.getName())){
	            List<Attribute> list = node.attributes();  
	            for (Attribute attr : list) {  
	                if("path".equals(attr.getName())){
	                	if(attr.getValue().startsWith(prefix)){
	                		paths.add(attr.getValue());
	                	}
	                }
	            }
	    	}
	        Iterator<Element> it = node.elementIterator();  
	        // 遍历  
	        while (it.hasNext()) {  
	            // 获取某个子节点对象  
	            Element e = it.next();  
	            // 对子节点进行遍历  
	            getPath(e,prefix,paths);  
	        }
			return paths; 
	    }
		public static void copyFile(File sourceFile, File destFile){
	    	try {
				FileUtils.newFileUtils().copyFile(sourceFile, destFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	}
