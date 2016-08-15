package com.carvendy.crawler.wenku;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import com.carvendy.base.url.HttpUtils;
import com.carvendy.crawler.httpclient.HttpClientUtils;

public class Wenku {
	
	static final String  ENCODING = "gbk";
	static String path = "D:\\download\\wenku4";
//	static String cookies = "Hm_lvt_59ce22710e353ee4d0f55960b28effd5=1453167932,1455516032; viewedPg=985ad41bb307e87101f696df%3D1%7C0%2687939ff631b765ce04081414%3D3%7C0%260e2432e949649b6648d747de%3D1%7C0%26ef0bce43c5da50e2524d7fbe%3D2%7C0%266f06ab08fc4ffe473368ab81%3D1%7C0%26701c1a681eb91a37f1115c67%3D2%7C0%264a82ce34168884868762d6ec%3D1%7C0%26e62eaf00f111f18583d05aca%3D2%7C0%264d717bd602d276a200292e9c%3D1%7C0%26a391b2e676eeaeaad1f330a4%3D1%7C0; BAIDUID=14CFF15EB26EF695B004216E489962EC:FG=1; BIDUPSID=14CFF15EB26EF695B004216E489962EC; wkview_gotodaily_tip=1; bdshare_firstime=1443066831613; BDUSS=HlNcU5YcDNQTUl4WmNMeHhpMU95WEJmaERYSzhDdVhkd2NxMjVjdEg4WWFJeXhXQVFBQUFBJCQAAAAAAAAAAAEAAAA-tgcoQ2FydmVuZHl4dQAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABqWBFYalgRWT; wk_novActivity=false; grownupTaskFinish=Carvendyxu%7C0; Hm_lvt_59ce22710e353ee4d0f55960b28effd5=1453167932; wk_2016spring=20160215; wk_shifen_pop_window=2355_2_1455516036236%7C2630_2_1455516044434%7C2644_2_1455520870636; wk_hotsearchword=%E5%81%9A%E7%84%A6%E8%A3%95%E7%A6%84%E5%BC%8F%E7%9A%84%E5%8E%BF%E5%A7%94%E4%B9%A6%E8%AE%B0%26%E5%BC%80%E5%AD%A6%E7%AC%AC%E4%B8%80%E8%AF%BE%26%E6%8A%97%E6%88%98%E8%83%9C%E5%88%A970%E5%91%A8%E5%B9%B4; wk_guidetips=2016-2-15; PSTM=1455675972; BDRCVFR[5-Mz6Yaq1uY]=mk3SLVN4HKm; H_PS_PSSID=17945; viewedPg=19f17f768e9951e79b892787%3D1%7C0%265da54aed0975f46527d3e137%3D1%7C0%26379b6c7ca32d7375a5178000%3D1%7C0%267a7f8194964bcf84b9d57bbb%3D3%7C0%268b5498e70975f46527d3e1c8%3D1%7C0%26985ad41bb307e87101f696df%3D1%7C0%2687939ff631b765ce04081414%3D3%7C0%260e2432e949649b6648d747de%3D1%7C0%26ef0bce43c5da50e2524d7fbe%3D2%7C0%264d717bd602d276a200292e9c%3D1%7C0; LoseUserAllPage=%7B%22status%22%3A1%2C%22expire_time%22%3A0%2C%22create_time%22%3A1455527635%2C%22type%22%3A0%2C%22cookie_time%22%3A1455786835%7D; Hm_lvt_d8bfb560f8d03bbefc9bdecafc4a4bf6=1453167682,1454553389,1455507824,1455678247; Hm_lpvt_d8bfb560f8d03bbefc9bdecafc4a4bf6=1455678260; BTAPOP=S7";
	static String cookies = "wkview_gotodaily_tip=1; BAIDUID=15463FADF12DF3DE30D0C58C068D7C9C:FG=1; bdshare_firstime=1455684995947; BDUSS=0FqV0oydC1LVzlmTHpCZlBjTkVKUDRVNk1uNFJtbUMtb21qZG9QZ0Zid09pLXRXQVFBQUFBJCQAAAAAAAAAAAEAAAAqOCaMAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA7-w1YO~sNWT; TOPMSG=1455685136-0; Hm_lvt_d8bfb560f8d03bbefc9bdecafc4a4bf6=1455684995; Hm_lpvt_d8bfb560f8d03bbefc9bdecafc4a4bf6=1455685139; BTAPOP=F7; wk_shifen_pop_window=2644_2_1455685144425; viewedPg=4d717bd602d276a200292e9c%3D1%7C0";
	static String formUrl = "http://wenku.baidu.com/user/submit/download";
	
	static Set<String> urlList = new HashSet<String>();
	
	static Map<String,String> finishMap = new HashMap<String, String>();
	
	public static void main(String[] args) {
		HttpUtils.cookies = cookies;
		
		loadUrl("紫薯凉糕");
	}
	
	public static void run(String kw){
		HttpUtils.cookies = cookies;
		
		urlList = new HashSet<String>();
		
		System.out.println("kw:"+kw);
		loadUrl(kw);
		
		for(String url : urlList){
			clawer(url);
		}
	}

	
	public static void loadUrl(String kw){
		String encode = "";
		try {
			encode = URLEncoder.encode(kw, ENCODING);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		for(int i =0 ; i < 5;i++){
			String searchUrl = "http://wenku.baidu.com/search?word=" + encode +"&lm=0&od=0&pn="+ i * 10;
			String html = HttpUtils.get(searchUrl);
			Document doc = Jsoup.parse(html); 
			
			Elements pList = doc.select(".search-result dl");
			//System.out.println(pList.size());
			for(Element p : pList){
				Elements a = p.select(".logFirstClickTime a");
				//System.out.println(p.select(".detail"));
				String detail = p.select(".detail").text();
				if(null != detail && detail.indexOf("0下载券") > -1){
					String href = a.attr("href"); 
					if(finishMap.get(href) == null){
						urlList.add(href);
						//System.out.println(href);
					}
					
				}
			}
			
			try {
				System.out.println("load-url-sleep : 500毫秒");
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		//System.out.println(urlList.size());
	}
	
	private static void clawer(String firstUrl) {
		System.out.println("load:" + firstUrl);
		//proxy();
		
		String html = HttpUtils.get(firstUrl);
		//System.out.println(html);
		Document doc = Jsoup.parse(html); 
		
		if(doc.select("form[name=\"downloadForm\"]").size() == 0){
			return;
		}
		Element form = doc.select("form[name=\"downloadForm\"]").get(0);
		//System.out.println(form);
	
		Map<String,String> map = new HashMap<String, String>();
		map.put("ct", "");
		map.put("doc_id", "");
		map.put("retType", "");
		map.put("sns_type", "");
		map.put("useTicket", "");
		map.put("target_uticket_num", "");
		map.put("downloadToken", "");
		map.put("sz", "");
		map.put("v_code", "");
		map.put("v_input", "");
		map.put("req_vip_free_doc", "");
		
		for(String key : map.keySet()){
			Element ele = form.getElementsByAttributeValue("name", key).get(0);
			String value = ele.attr("value");
			//System.out.println(value);
			map.put(key, value);
		}
		
		String downloadUrl = HttpClientUtils.postMethodDownload(formUrl, map, cookies);
		//System.out.println(downloadUrl);
		if(StringUtils.isNotBlank(downloadUrl)){
			download(downloadUrl, path);
		}
		
		int random = new Random().nextInt(5) + 1;
		try {
			System.out.println("download-sleep: 休眠"+random+"秒");
			Thread.sleep(random);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		finishMap.put(firstUrl, "haha");
	}
	
	public static void download(String urlStr,String path){
		if(StringUtils.isBlank(urlStr)){
			return;
		}
		System.out.println("download:"+urlStr);
		
		URLConnection conn = HttpUtils.getConn(urlStr);
		
		InputStream is =null;
		FileOutputStream fos =null;	
		
		String realName=urlStr.substring(urlStr.lastIndexOf("/"),urlStr.lastIndexOf("."));
		String suffix=urlStr.substring(urlStr.lastIndexOf("."));
		File dir=new File(path);
		dir.mkdirs();
		
		realName = realName.replaceAll(".*filename=\"", "");
		try {
			realName = URLDecoder.decode( realName , ENCODING );
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		suffix = suffix.replaceAll("\".*", "");
		try {
			conn.connect();
			is=conn.getInputStream();
			
			File file = new File(path+"/"+realName+"_"+
					new SimpleDateFormat("mmss").format(new Date())+suffix);
			file.createNewFile();
			
			fos=new FileOutputStream(file);
			int read=-1;
			while((read=is.read())!=-1){
				fos.write(read);
			}
					
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(is!=null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}finally{
					is=null;
				}
			}
			
			if(fos!=null){
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}finally{
					is=null;
				}
			}
		}
		System.out.println("下载完毕");
	}

	private static void proxy() {
		String[] ipArr = new String[]{
				"58.67.159.50:80",
				"60.29.248.142:8080",
				"58.253.238.242:80",
				"106.37.177.251:3128",
				"120.192.92.98:80"   ,
				"120.192.92.99:80"   ,
				"120.197.234.164:80" ,
				"121.199.60.143:3128",
				"182.92.148.71:3128" ,
				"202.106.16.36:3128" ,
				"210.82.92.77:3128"  ,
				"211.144.81.69:18000",
		};
		
		Random random = new Random();
		int nextInt = random.nextInt(ipArr.length);
		String s = ipArr[nextInt];
		String ip = s.split(":")[0];
		String proxy = s.split(":")[1];
		System.setProperty("http.proxyHost", ip );  
		System.setProperty("http.proxyPort", proxy);
	}
	
}
