package com.carvendy.crawler.jsoup;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.carvendy.base.url.HttpUtils;
import com.sun.org.apache.xml.internal.security.utils.HelperNodeList;

public class WeiboT {

	/*
	 * ?topnav=1&wvr=5&topsug=1
	 */
	public static Document getDoc(String url){
		
		Document doc=null;	
		String html = HttpUtils.get(url);
		doc = Jsoup.parse(html);
		return doc;
	}
	
	/*
	 * post
	 */
	public static Document postDoc(String url,String params){
		Document doc=null;	
		String html = HttpUtils.post(url,params);
		doc = Jsoup.parse(html);
		return doc;
	}
	
	/*
	 *  weibo:搜索不用登录 
	 *  查看微博，需要登录
	 */
	
	/*  萤火虫
	 * http://s.weibo.com/weibo/%E8%90%A4%E7%81%AB%E8%99%AB%E6%BC%AB%E5%B1%95?topnav=1&wvr=5&topsug=1
	 * 
	 * 胡歌
	 * http://s.weibo.com/weibo/%25E8%2583%25A1%25E6%25AD%258C&Refer=STopic_box
	 */
	
	public static void main(String[] args) {
		String url="http://s.weibo.com/weibo/%E8%90%A4%E7%81%AB%E8%99%AB%E6%BC%AB%E5%B1%95?topnav=1&wvr=5&topsug=1";
		Document doc = getDoc(url);
		//System.out.println(doc.getElementsByTag("title"));
		
		//System.out.println(doc);
		
		Elements dlList = doc.select("dl[action-type]");
		//System.out.println(dlList.get(0));
		//
		/*for (Element dl : dlList) {
			Elements a = dl.select("a[nick-name]");
			//System.out.println("nick-name:"+a.attr("nick-name")+",weibo:"+a.attr("href"));
			//System.out.println(a);
		}*/
		
		
		
		/*
		 * 作用：抓取图片
		 *  
		 *  头像：
		 *  	
		 *  	http://tp2.sinaimg.cn/1930439997/50/40060234240/1
		 *  
		 *  微博内容中的图：(形式)大，矩形，正方形	
		 * 		http://ww2.sinaimg.cn/bmiddle/a7fcd0bcjw1eimnbxbya3j2050050t8n.jpg
		 * 		http://ww2.sinaimg.cn/square/a7fcd0bcjw1eimnbxbya3j2050050t8n.jpg
		 * 		http://ww2.sinaimg.cn/thumbnail/a7fcd0bcjw1eimnbxbya3j2050050t8n.jpg
		 */
		Elements imgList = doc.select("img");
		//System.out.println(imgList);
		
		for (Element ele : imgList) {
			//System.out.println(ele.attr("src"));
		}
		
		//System.out.println(imgList.get(0));
		//System.out.println(imgList.get(1));
		HttpUtils.download(imgList.get(1).attr("src"));
		
		//xxx
		
		
	}
}
