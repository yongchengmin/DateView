package com.yc.view.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.Random;
import java.util.regex.Pattern;

import org.apache.tools.ant.util.FileUtils;

import com.yc.utils.files.PropertiesUtil;

public class ProjectUtils {
//	static String parentPath = null;
//	static{
//		parentPath = ProjectUtils.class.getResource("../").getFile().toString();
//		System.out.println("parentPath load:"+parentPath);
//	}
	public static final String numbers = "0123456789";
	public static String getRandomUtils(int length){
		 StringBuffer sb = new StringBuffer(); 
	     Random random = new Random(); 
	     for (int i = 0; i < length; i++){
	    	 sb.append(numbers.charAt(random.nextInt(numbers.length())));
	     }
	     return sb.toString();

	}
	//删除指定路径下指定类型的文件
	public static void deleteFile(String pathname,String fileType){
		File fileOne = new File(pathname);
		File files[] = fileOne.listFiles();     
		for(int i=0;i<files.length;i++){
			if(files[i].isFile()){
				if(files[i].getName().endsWith(fileType)){
					files[i].delete();  
				}
			}
        }
	}
	
	public static String getString(byte[] data){
		String str = null;
		try {
			str=new String( data ,ChartGlobal.encodeing);
		} catch (UnsupportedEncodingException e) {
			str = e.getMessage();
		}
		return str;
		/**
		 *使用案例 
		FileInputStream hFile;
		int i = 0;
		byte[] data = null;
		try {
			hFile = new FileInputStream("D:/jac_gitee_v002/DateView/WebContent/01.json");// 以byte流的方式打开文件
			try {
				i = hFile.available();//得到文件大小
			} catch (IOException e) {
				e.printStackTrace();
			} 
			data=new byte[i];
			try {
				hFile.read(data);//读数据
			} catch (IOException e) {
				e.printStackTrace();
			}  
			try {
				hFile.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		String str = getString(data);
		System.out.println(str);
		 */
	}
	
//	public static byte[] getByte(String str){
//		byte[] data = null;
//		try {
//			data = str.getBytes(ChartGlobal.encodeing);
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//		return data;
//	}
	
	public static byte[] getByte(String name) throws IOException{
		FileInputStream hFile = new FileInputStream(name); // 以byte流的方式打开文件
		int i=hFile.available(); //得到文件大小
		byte[] data = new byte[i];
		hFile.read(data);  //读数据
		hFile.close();
		return data;
	}
	
	public static String getPropertiesKey(String key){
		String pathname = null;
		Properties pp = new Properties();
		InputStream in = ProjectUtils.class.getClassLoader().getResourceAsStream(PropertiesUtil.PROPERTIES_ACCESS_TOKEN);
		 try {
			pp.load(in);
			pathname = pp.getProperty(key);
		} catch (IOException e1) {
			e1.printStackTrace();
		}finally{
        	pp = null;
        	in = null;
        }
		return pathname;
	}

	public static String getCurrentPath(){  
	       //取得根目录路径  
	       String rootPath=ProjectUtils.class.getResource("/").getFile().toString();  
	       System.out.println("rootPath="+rootPath);
	       //当前目录路径  
	       String currentPath1=ProjectUtils.class.getResource(".").getFile().toString(); 
	       System.out.println("currentPath1="+currentPath1);
	       String currentPath2=ProjectUtils.class.getResource("").getFile().toString();  
	       System.out.println("currentPath2="+currentPath2);
	       //当前目录的上级目录路径  
	       String parentPath=ProjectUtils.class.getResource("../").getFile().toString();  
	       System.out.println("parentPath="+parentPath);
	       return rootPath;         
	   
	       /*
	        parentPath load:/D:/jac_gitee_v002/DateView/build/classes/com/yc/view/
			rootPath=/D:/jac_gitee_v002/DateView/build/classes/
			currentPath1=/D:/jac_gitee_v002/DateView/build/classes/com/yc/view/utils/
			currentPath2=/D:/jac_gitee_v002/DateView/build/classes/com/yc/view/utils/
			parentPath=/D:/jac_gitee_v002/DateView/build/classes/com/yc/view/
	        * */
	   }
	
	/**复制文件*/
    public static void copyFile(File sourceFile, File destFile){
    	try {
			FileUtils.newFileUtils().copyFile(sourceFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public static void createTxt(String file,String row,String character){
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
    
    public static boolean isNumeric1(String str){
		  Pattern pattern = Pattern.compile("[0-9]*");
		  return pattern.matcher(str).matches();
	}
	
	public static void main(String[] args) {
		System.out.println(isNumeric1(""));
		System.out.println(isNumeric1("2"));
		System.out.println(isNumeric1("-"));
		System.out.println(isNumeric1("02"));
		System.out.println(isNumeric1("0.3"));
		/*FileInputStream hFile;
		int i = 0;
		byte[] data = null;
		try {
			hFile = new FileInputStream("D:/jac_gitee_v002/DateView/WebContent/01.json");// 以byte流的方式打开文件
			try {
				i = hFile.available();//得到文件大小
			} catch (IOException e) {
				e.printStackTrace();
			} 
			data=new byte[i];
			try {
				hFile.read(data);//读数据
			} catch (IOException e) {
				e.printStackTrace();
			}  
			try {
				hFile.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		String str = getString(data);
		System.out.println(str);*/
		
//		deleteFile("D:\\json", ".png");
//		System.out.println(getRandomUtils(3));
//		Random random = new Random();
//		System.out.println(random.nextInt(10));
	}
}
