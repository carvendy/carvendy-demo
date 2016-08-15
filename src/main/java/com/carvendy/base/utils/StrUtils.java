package com.carvendy.base.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class StrUtils {

	
	/******
	 * 匹配什么，返回什么
	 * 
	 * */
	public static List<String> getOutter(String content,String regex){
		List<String> strList=new ArrayList<String>();
		
		Pattern p=Pattern.compile(regex);
		Matcher m = p.matcher(content);
		
		while(m.find()){
			strList.add(m.group(0));
		}
		return strList;
	}
	
	
	/******
	 * 匹配：http://www.baidu.com
	 * 
	 * 返回：www.baidu.com
	 * */
	public static List<String> getInner(String content,String matchReg,String getReg){
		List<String> strList=new ArrayList<String>();
		Pattern p=Pattern.compile(matchReg);
		Matcher m = p.matcher(content);
		
		while(m.find()){
			matchReg.replaceAll(getReg, "$1");
			strList.add(m.group(0));
		}
		
		return strList;
	}
	
	
	
	public static void main(String[] args) {
		String str="a=http://www.abc.com;b=http://www.ac.com;https://www.a.com";

		String regex="https?://(w{3}\\.\\w+\\.com)";
		List<String> outter = getOutter(str, regex);
		for (int i = 0; i < outter.size(); i++) {
			System.out.println(outter.get(i)+"----->"
					+outter.get(i).replaceAll(regex, "$1"));
		}
		
	}
}
