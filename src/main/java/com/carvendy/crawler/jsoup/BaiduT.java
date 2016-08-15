package com.carvendy.crawler.jsoup;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.carvendy.base.url.HttpUtils;

public class BaiduT {

	//"Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.15)") // 设置User-Agent 
	public static Document test01(String url) {
		Document doc=null;
		
		Map<String, String> map=new HashMap<String, String>();
		map.put("wd", "java+%C5%C0%B3%E6");
		map.put("tn", "monline_4_dg");
		
		try {	
			doc = Jsoup.connect(url)
					.data(map)
					.userAgent("") 
					.cookie("auth", "token")
					.timeout(3000)
					 .get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return doc;
		
	}
	
	
	public static Document test02(String url){
		Document doc=null;
		
		String html = HttpUtils.get(url);
		//System.out.println(html);
		doc = Jsoup.parse(html);
		
		return doc;
	}
	
	
	//http://www.baidu.com/baidu?wd=java+%C5%C0%B3%E6&tn=monline_4_dg
	//http://www.baidu.com/s?tn=monline_4_dg&wd=java%20%E7%88%AC%E8%99%AB&pn=10&oq=java%20%E7%88%AC%E8%99%AB&ie=utf-8
	public static void main(String[] args) throws IOException {
		//Document doc = one("http://www.baidu.com/baidu");
		//System.out.println(doc);
		
		Document doc = test02("http://www.baidu.com/baidu?wd=java+%C5%C0%B3%E6&tn=monline_4_dg"); 
		//System.out.println(doc);
		
		/*Elements head = doc.getElementsByTag("head"); 
		System.out.println(head);*/
		
		Elements resultList = doc.getElementsByClass("result"); 
		
		
		//结果集。
		for (Element ele : resultList) {
			/*
			 * 结论：id就排名
			 */
			System.out.print("排名:"+ele.attr("id")+"  ");
			
			/*
			 * 标题
			 */
			Elements div = ele.select("div.c-tools");
			String titleJson=div.attr("data-tools");
			System.out.println(titleJson.replaceAll("\\{\"title\":\"(.*?)\",\"url(.*)", "$1"));
		}
		
	}
}
