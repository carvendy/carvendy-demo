package com.carvendy.crawler.httpclient;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class BaiduT {

	//public static String url="https://www.baidu.com/link?url=CRSU1Xt9zdDJj_1bJjbikEgexObNd8lPB0Ot3OMzunRUOOzpm5tgcElF4_D40htSj-QgpUSsawC0-IzaJOoNe_&wd=%E7%94%B5%E9%A5%AD%E9%94%85%20%E4%B8%80%E5%91%BC%E7%99%BE%E5%BA%94&issp=1&f=8&ie=utf-8&tn=baiduhome_pg";
	public static String url="https://www.baidu.com/link?url=0OYqotGTOjVt9oRnPqlAi2EIQYmFO0XO1lSi0DtFaORXWB78vIWG_iQ1UpSTEiRLHGKvF4asAOeeVLkDu3xTdq&ie=UTF-8&wd=%E7%94%B5%E9%A5%AD%E9%94%85";
	
	public static void main(String[] args) {
		String method = HttpClientUtils.getMethod(url);
		System.out.println(method);
		
		Document doc = Jsoup.parse(method);
		Element ele = doc.getElementsByTag("script").get(0);
		//System.out.println(ele.html());
		/*String html=ele.html();
		System.out.println(html.replaceAll("window.location.replace|\"|\\(|\\)", ""));*/
		
		ele = doc.getElementsByTag("meta").get(1);
		//System.out.println(ele.getElementsByAttributeValue("http-equiv", "refresh"));
		String meta = ele.getElementsByAttribute("http-equiv").toString();
		
		System.out.println(meta);
		/*System.out.println(meta
				.replaceAll("content=\"(.+)\"", "$1")
				.replaceAll("0;URL='(.+)'", "$1"));*/
		
		/*System.out.println(meta
				.replaceAll("(.+)URL='(.+)'\"(.+)", "$2"));*/
		
		//System.out.println(meta.replaceAll(".+(http:.+)", "$1"));
		System.out.println(HttpClientUtils.getUrl(meta));
		
	}
}
