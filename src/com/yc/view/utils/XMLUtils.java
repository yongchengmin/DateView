package com.yc.view.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;


/**
 * 处理XML工具类型
 *
 */
public class XMLUtils {

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
	
	/**
	 * 得到XML中指定节点
	 * @param xmlDoc
	 * @param elementName
	 * @return
	 */
	public static Element getElementByName(Document xmlDoc,String elementName){
		Element elements = xmlDoc.getRootElement();
		Element returnElement;
		Element node;
		for(Iterator element = elements.elementIterator(); element.hasNext();) {
			node = (Element) element.next();
			if(elementName.equals(node.getName())) {
				return node;
			}
			if (node.hasContent()) {
				returnElement = getElementByName(node,elementName);
				if(returnElement != null) {
					return returnElement;
				}
			}
		}
		return null;
	}
	
	public static Element getElementByName(Element element,String elementName){
		Element returnElement;
		Element node;
		for(Iterator elementI = element.elementIterator(); elementI.hasNext();) {
			node = (Element) elementI.next();
			if(elementName.equals(node.getName())) {
				return node;
			}
			if (node.hasContent()) {
				returnElement = getElementByName(node,elementName);
				if(returnElement != null) {
					return returnElement;
				}
			}
		}
		return null;
	}
	
	 /** 
     * 遍历当前节点元素下面的所有(元素的)子节点 
     *  
     * @param node 
     */  
    public static void listNodes(Element node) {  
        System.out.println("当前节点的名称 :" + node.getName());  
        // 获取当前节点的所有属性节点  
        List<Attribute> list = node.attributes();  
        // 遍历属性节点  
        for (Attribute attr : list) {  
            System.out.println("text:"+attr.getText() + ",name:" + attr.getName()  
                    + ",value:" + attr.getValue());  
        }  
        if (!(node.getTextTrim().equals(""))) {  
            System.out.println("文本内容 :" + node.getText());  
        }  

        // 当前节点下面子节点迭代器  
        Iterator<Element> it = node.elementIterator();  
        // 遍历  
        while (it.hasNext()) {  
            // 获取某个子节点对象  
            Element e = it.next();  
            // 对子节点进行遍历  
            listNodes(e);  
        }  
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
}
