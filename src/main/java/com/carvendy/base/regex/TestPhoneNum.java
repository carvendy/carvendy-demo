package com.carvendy.base.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestPhoneNum {

	public static void main(String[] args) {
		String mobile="13916733521";
		String regex="1[3578]\\d{9}";
		//System.out.println(Pattern.matches(regex, mobile));
		
		Pattern p=Pattern.compile("1[3578]\\d{9}");
		Matcher m=p.matcher(mobile);
		//System.out.println(m.find());
		
		Pattern pattern=Pattern.compile("([a-z]+)(\\d+)"); 
		Matcher matcher=pattern.matcher("aaa2223bb"); 
		while(matcher.find()){
			//System.out.println("group1:"+matcher.group());
			//System.out.println("group2:"+matcher.group(2));
		}
		
		String baidu="http://www.baidu.com";
		String alipay="https://www.alipay.com";
		regex="(https?):.+";
		//System.out.println(baidu.replaceAll(regex,"$1"));
		//System.out.println(alipay.replaceAll(regex,"$1"));
		
		String html="<a href='http://www.youboy.com'>youboy</a>";
		regex="<a.*?>(.+)</a>";
		System.out.println(html.replaceAll(regex, "$1"));
	}
}
