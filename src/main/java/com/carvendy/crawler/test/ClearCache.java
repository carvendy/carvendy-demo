package com.carvendy.crawler.test;

import com.carvendy.crawler.httpclient.HttpClientUtils;

public class ClearCache {

	public static void main(String[] args) {
		runNum();
	}
	
	
	private static void runNum(){
		/*String url="http://news.youboy.com/ybpurge/";
		
		for(int i=1;i<26;i++){
			String html = HttpClientUtils.getMethod(url+"hotWords.do?initial="+(char)('a'+i));
			System.out.println(html);
			//System.out.println(url+"hotWords.do?initial="+(char)('a'+i));
		}*/
		
		/*String url="http://book.youboy.com/ybpurge/";
		for(int i=1;i<1000;i++){
			String html = HttpClientUtils.getMethod(url+"comshow.do?id="+i);
			System.out.println(html);
		}*/
		
		
		String url="http://qiye.youboy.com/ybpurge/";
		for(int i=1;i<40;i++){
			String html = HttpClientUtils.getMethod(url+"cb.do?id="+i);
			System.out.println(html);
		
		}
	}
	
	
	private static void runArr(){
		String[] arr=new String[]{""};
		String url="http://news.youboy.com/ybpurge/";
		for(String val:arr){
			String html = HttpClientUtils.getMethod(url);
			System.out.println(html);
		}
	}
}
