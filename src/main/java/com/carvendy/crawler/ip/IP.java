package com.carvendy.crawler.ip;

import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.carvendy.crawler.httpclient.HttpClientUtils;


public class IP {

	public static String[] filterArr=new String[]{
		"泰国","美国","西班牙","伊拉克",
		"波兰","韩国","印度","俄罗斯",
		"意大利","法国","罗马尼亚","坦桑尼亚",
		"南非","利比里亚","马达加斯加","塞拉利昂",
		"赞比亚","柬埔寨","伊朗","土耳其","塞尔维亚",
		"台湾","马其顿","埃及","爱尔兰",
		"德国","加纳","英国","立陶宛",
		"黎巴嫩","沙特阿拉伯","拉脱维亚","哈萨克斯坦",
		"亚美尼亚","德国","捷克","加拿大",
		"孟加拉","乌克兰","保加利亚","菲律宾",
		"亚太地区","越南","北美地区","巴基斯坦",
		"尼泊尔","巴基斯坦","非洲","乌克兰",
		"巴西","哥伦比亚","阿根廷","拉美地区",
		"智利","委内瑞拉","墨西哥","巴拉圭",
		"瑞士","阿联酋","尼日利亚","津巴布韦",
		"莫桑比克","厄瓜多尔"
	};
	
	
	static final Integer THREAD_COUNT = 5;
	static Integer index = 0;
	
	
	public static void main(String[] args) {
		final long startTime = System.currentTimeMillis();
		
		//String url="http://www.youdaili.net/Daili/guonei/3430";
		//String url="http://www.youdaili.net/Daili/guonei/3459";//3469 ,3474
		String url="http://www.youdaili.net/Daili/http/3459";//3469 ,3474
		
	    final List<String> ipList = new ArrayList<String>();
		
		loadIp(ipList,url+".html");
		
		for(int i=2;i<6;i++){
			loadIp(ipList,url+"_"+i+".html");
		}
		System.out.println("data:...........");
		for (String ip:ipList) {
			//System.out.println(ip.replaceAll("@HTTP#", ",")+",否,	3秒	,2015-04-07 19:30:59");
			//System.out.println(ip.replaceAll("@HTTP#", ",")+",,,");
		}
		
		/*for(int i=0;i<ipList.size();i++){
			checkIp(ipList.get(i));
		}*/
		
		for(int i=0 ; i< THREAD_COUNT; i++){
			
			Thread thread = new Thread(){
				public void run() {
					for(;index< ipList.size();){
						String proxyIp = "";
						synchronized (index) {
							proxyIp = ipList.get(index);
							index++;
						}
						checkIp(proxyIp);
					}
					
					long endTime = System.currentTimeMillis();
					System.err.println("********耗时："+(endTime-startTime)+"********");
				};
			};
			
			thread.start();
		}
		
	}
	
	private static void checkIp(String record) {
		String testUrl="http://www.sogou.com/web?query=http%3A%2F%2Fwww.youboy.com%2Fs500000020.html";
		//String testUrl="http://www.haosou.com/s?q="+URLEncoder.encode("http://www.youboy.com/s500328389.html");
		//String testUrl="http://www.baidu.com/s?wd=%E5%8D%9A%E7%9D%BF&tn=999914124_hao_pg&ie=utf8";
		
		String ipStr = record;
		String[] split = ipStr.split("@");
		String proxyIp = split[0];
		
		int timeout = 3000;
		//System.out.println(split[0]);
		long start= System.currentTimeMillis();
		if(HttpClientUtils.checkProxyIp(proxyIp, testUrl, timeout)){
			System.out.print(proxyIp+",");
			//System.out.print(ipStr+",");
			long end= System.currentTimeMillis();
			DecimalFormat df =new DecimalFormat("#0.00");  
			System.out.println(df.format((end-start)/1000.0)+"秒");
		}else{
			//System.err.println("不符合的："+ipStr);
		}
	}
	
	private static void loadIp(List<String> ipList,String url){
		String html = HttpClientUtils.getMethod(url);
		//System.out.println(html);
		Document htmlDoc = Jsoup.parse(html);
		Elements cont_font = htmlDoc.select(".cont_font>p");
		//System.out.println(cont_font.html().replaceAll("<br\\s/>","\n"));
		
		String[] ipArr = cont_font.html().split("<br>");
		for (int i = 0; i < ipArr.length; i++) {
			
			if(filterIp(ipArr[i])){
				ipList.add(ipArr[i].trim());
			}
		}
		
	}
	
	private static boolean filterIp(String ip){
		for(String filter:filterArr){
			if("".equals(ip)||ip.indexOf(filter)!=-1){
				return false;
			}
		}
		return true;
	}
	
	/*private static boolean checkProxy(){
		String url="http://www.carvendy.com";
		int timeout=5000;
		String ip="202.116.160.89";
		int port=80;
		if( HttpClientUtils.checkProxyIp(ip, port, url, timeout)){
			System.out.println(1);
		}
		
	}*/
}
