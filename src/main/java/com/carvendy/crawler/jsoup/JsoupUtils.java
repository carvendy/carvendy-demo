package com.carvendy.crawler.jsoup;

import java.io.IOException;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class JsoupUtils {
	
	public static String userAgent="Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.15)";
	
	public static int timeout=3000;
	
	public static Document getMethod(String url) {
		Document doc=null;
		
		try {	
			doc = Jsoup.connect(url)
					.userAgent(userAgent) 
					.timeout(timeout)
					.get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return doc;
	}
	
	public static Document postMethod(String url,Map<String,String> paramMap) {
		Document doc=null;
		
		try {	
			doc = Jsoup.connect(url)
					.userAgent(userAgent) 
					.timeout(timeout)
					.data(paramMap)
					.post();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return doc;
	}
}
