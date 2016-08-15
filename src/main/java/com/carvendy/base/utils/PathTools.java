package com.carvendy.base.utils;

import java.io.InputStream;
import java.net.URL;

public class PathTools {

	public URL getUrl(){	
		return this.getClass().getResource("log4j.xml");
	}
	
	public InputStream getStream(){
		return this.getClass().getResourceAsStream("");
	}
	
	public URL getURL2(){
		return this.getClass() .getClassLoader().getResource("name");
	}
	
	public URL getURL3(){
		return Thread.currentThread().getContextClassLoader ().getResource("");
	}
	
	public static void main(String[] args) {
		//classPath+"你的参数"【找到才显示的】
		//打印：file:/D:/workspace/spring-security-demo/target/classes/log4j.xml
		System.out.println(ClassLoader.getSystemResource("log4j.xml").getPath());//
		/*
		 * 最前面不加“/”是相对于classPath,就class存放的根目录
		 */
		
		
		//InputStream in = ClassLoader.getSystemResourceAsStream("log4j.xml");
		//获得的in就可以到后面读取，io操作了
		
		
		int a=12;
		a+=a-=a*a;
		System.out.println(a);
	}
}
