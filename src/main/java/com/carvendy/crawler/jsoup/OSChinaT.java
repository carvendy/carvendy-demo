package com.carvendy.crawler.jsoup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.carvendy.base.url.HttpUtils;

public class OSChinaT {

	//"Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.15)") // 设置User-Agent 
	public static Document postDoc(String url,String params){
		Document doc=null;	
		String html = HttpUtils.post(url,params);
		doc = Jsoup.parse(html);	
		return doc;
	}
	
	
	public static Document getDoc(String url){
		Document doc=null;	
		String html = HttpUtils.get(url);
		doc = Jsoup.parse(html);	
		return doc;
	}
	
	
	/*
	 * oschina,有防抓取
	 * 
	 * 搜索：  http://www.oschina.net/search?scope=project&q=jsoup
	 * 
	 * 详情页： http://www.oschina.net/p/jsoup
	 */
	public static void main(String[] args) throws IOException {
		String url="http://www.oschina.net";
		Document doc = getDoc(url);
		//System.out.println(doc);
		
		Elements industryNews = doc.select("#IndustryNews");
		//System.out.println(select);
		
		Elements p1 = industryNews.select(".p1");

		List<String> urls = getUrls(p1.html());
		
		
		url=null;
		for(String s:urls){
			if(s.indexOf("/new")!=-1){
				url=s;
			}
		}
		
		System.out.println("采集网址："+url);
		
		doc = getDoc(url);
		//System.out.println(doc);
		
		Elements OSCTitle = doc.getElementsByClass("OSCTitle");
		System.out.println(OSCTitle.text());
		
		Elements NewsContent = doc.getElementsByClass("NewsContent");
		System.out.println(NewsContent);
	}
	
	
	public static List<String> getUrls(String txt){
		ArrayList<String> list = new ArrayList<String>();
		if(StringUtils.isBlank(txt)){
			return list;
		}
		
		txt=txt.replaceAll("href=\"/", "href=\"http://www.oschina.net/");
		//System.out.println(txt);
		
		String regex="href=\"(.*?)\"";
		Pattern p=Pattern.compile(regex);
		Matcher m = p.matcher(txt);
		
		while(m.find()){
			String url = m.group(1);
			//System.out.println(group);
			list.add(url);
		}
		
		return list;
	}

	
	
}
