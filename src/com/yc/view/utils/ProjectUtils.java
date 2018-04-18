package com.yc.view.utils;

import java.util.Random;

public class ProjectUtils {
//	static String parentPath = null;
//	static{
//		parentPath = ProjectUtils.class.getResource("../").getFile().toString();
//		System.out.println("parentPath load:"+parentPath);
//	}

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
		Random random = new Random();
		System.out.println(random.nextInt(10));
	}
}
