package com.carvendy.crawler.ip;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.carvendy.crawler.httpclient.HttpClientUtils;

public class MutilThread {

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
		
		String url="http://www.youdaili.net/Daili/http/3616";
	    final List<String> ipList = new ArrayList<String>();
		
		loadIp(ipList,url+".html");
		
		for(int i=2;i<6;i++){
			loadIp(ipList,url+"_"+i+".html");
		}
		System.out.println("data:...........");
		/*for (String ip:ipList) {
			System.out.println(ip.replaceAll("@HTTP#", ",")+",否,	3秒	,2015-04-07 19:30:59");
			System.out.println(ip.replaceAll("@HTTP#", ",")+",,,");
		}*/
		
		List<List<String>> divideList = dividePart(ipList);
		
		for (int i = 0; i < THREAD_COUNT; i++) {
			final List<String> partList = divideList.get(i);
			
			new Thread() {
				public void run() {
					for (int j = 0; j < partList.size(); j++) {
						String proxyIp = partList.get(j);
						checkIp(proxyIp);
					}
					
					long endTime = System.currentTimeMillis();
					System.err.println("********耗时："+(endTime-startTime)+"********");
				};

			}.start();
		}
		
	}

	private static List<List<String>> dividePart(final List<String> ipList) {
		List<List<String>> partList = new ArrayList<List<String>>();
		for(int i=0 ; i< THREAD_COUNT; i++){
			partList.add(new ArrayList<String>());
		}
		
		for(int i =0; i<ipList.size();i++){
			List<String> part = partList.get( i % THREAD_COUNT); 
			part.add(ipList.get(i));
		}
		return partList;
	}

	private static void checkIp(String proxyIp) {
		//String testUrl="http://www.carvendy.com";
		String testUrl="https://www.sogou.com/web?query=%E4%BD%A0%E5%A5%BD+%E6%97%A7%E6%97%B6%E5%85%89&_asf=www.sogou.com&_ast=&w=01015002&p=40040100&ie=utf8&oq=%E4%BD%A0%E5%A5%BD&ri=1&sourceid=sugg&stj=1%3B0%3B0%3B0&stj2=0&stj0=1&stj1=0&hp=0&hp1=&sut=4467&sst0=1442540509379&lkt=0%2C0%2C0";
		String ipStr = proxyIp;
		String[] split = ipStr.split("@");
		int timeout = 3500;
		//System.out.println(split[0]);
		long start= System.currentTimeMillis();
		if(HttpClientUtils.checkProxyIp(split[0], testUrl, timeout)){
			//System.out.print(ipStr+",");
			System.out.print(split[0]+",");
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
