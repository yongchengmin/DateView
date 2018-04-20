package com.yc.view.utils;

import java.io.File;
import java.util.Random;

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
	
	public static void main(String[] args) {
		deleteFile("D:\\json", ".png");
//		System.out.println(getRandomUtils(3));
//		Random random = new Random();
//		System.out.println(random.nextInt(10));
	}
}
