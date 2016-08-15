package com.carvendy.crawler.test;

import java.util.HashMap;
import java.util.Map;

import com.carvendy.crawler.httpclient.HttpClientUtils;


public class OSchinaT {

	private static String loginUrl="http://www.oschina.net/action/user/hash_login";
	
	private static Map<String,String> loginParams=new HashMap<String, String>();
	static{
		//loginParams.put("email", value);
		//loginParams.put("pwd", value);
	}
	
	
	//oscid=j2GUvWVtefsfjiKt38KHWOANW07XG7vJCtsXzCUuSOBAOMwLTMuuF59XGyhYeo7yb%2F1MBJpkSFUoDPsKNb7LRzq2vJ97aENIH2OjCqp6jRWyU%2FodUtbBTesIy9RU4DtH
	public static void main(String[] args) {
		String url="http://www.oschina.net";
		String html = HttpClientUtils.getMethod(url);
		System.out.println(html);
	}
}
