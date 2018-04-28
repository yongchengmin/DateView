package com.yc.view.sql;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.yc.view.scan.StringUtil;
import com.yc.view.utils.ChartGlobal;

public class SqlpathPackageScanner implements SqlPackageScanner{
    private String basePackage;
    private ClassLoader cl;

    /**
     * 初始化
     * @param basePackage
     */
    public SqlpathPackageScanner(String basePackage) {
        this.basePackage = basePackage;
        this.cl = getClass().getClassLoader();
    }
    public SqlpathPackageScanner(String basePackage, ClassLoader cl) {
        this.basePackage = basePackage;
        this.cl = cl;
    }
    /**
     *获取指定包下的所有字节码文件的全类名
     */
	public Map<String, String> getFullyQualifiedSqlNameList()
			throws IOException {
		System.out.println("begin scan package "+basePackage+" files...");
		return doScan(basePackage, new HashMap<String,String>());
	}

    /**
     *doScan函数
     * @param basePackage
     * @param nameList
     * @return
     * @throws IOException
     */
    private Map<String,String> doScan(String basePackage, HashMap<String,String> maps) throws IOException {
        String splashPath = StringUtil.dotToSplash(basePackage);
        URL url = cl.getResource(splashPath);   //file:/D:/WorkSpace/java/ScanTest/target/classes/com/scan
        String filePath = StringUtil.getRootPath(url);
        List<String> names = readFromDirectory(filePath);
        for (String name : names) {
            if (isSqlFile(name)) {
            	maps.put(name, SqlLoad.class.getResource(name).getPath());
            }
        }
        return maps;
    }

    private List<String> readFromDirectory(String path) {
        File file = new File(path);
        String[] names = file.list();

        if (null == names) {
            return null;
        }

        return Arrays.asList(names);
    }

    private boolean isSqlFile(String name) {
        return name.endsWith(ChartGlobal.sqlEnd);
    }
    /**
     * For test purpose.
     */
    public static void main(String[] args) throws Exception {
    	SqlPackageScanner scan = new SqlpathPackageScanner(SqlLoad.class.getPackage().getName());
    	Map<String, String> maps = scan.getFullyQualifiedSqlNameList();
    	SqlLoad.initSqlPro(maps);
//    	Iterator<Entry<String, String>> iterator = maps.entrySet().iterator();
//    	while(iterator.hasNext()){
//    		Entry<String, String> entry = iterator.next();
//    		System.out.println(entry.getKey()+":"+"\n"+entry.getValue());
//    	}
    }
}
