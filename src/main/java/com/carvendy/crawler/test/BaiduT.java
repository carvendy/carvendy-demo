package com.carvendy.crawler.test;


import java.io.UnsupportedEncodingException;
import java.util.List;

import com.carvendy.base.url.HttpUtils;


public class BaiduT {

	//单纯地使用正则，不一定起效的。【获取的内容要按照，dom模型来】
	
	/**main方法测试*/
	public static void main(String[] args) throws UnsupportedEncodingException {
		//http://www.baidu.com/baidu?wd=java+%C5%C0%B3%E6&tn=monline_4_dg
		//http://www.baidu.com/s?wd=%E5%B0%8F%E6%98%9F%E6%98%9F%E5%B0%8F%E6%98%9F%E6%98%9F&ie=utf-8&tn=baiduhome_pg&oq=%E5%B0%8F%E6%98%9F%E6%98%9F%E5%B0%8F%E6%98%9F%E6%98%9F&f=3&rsv_spt=1&issp=1&rsv_bp=0&rsv_sug3=7&rsv_sug4=1080&rsv_sug1=5&rsv_sug2=1&rsp=0&inputT=6946&pn=10&usm=1&rsv_page=1
		//http://www.baidu.com/s?wd=%E5%B0%8F%E6%98%9F%E6%98%9F%E5%B0%8F%E6%98%9F%E6%98%9F&rsv_spt=1&issp=1&rsv_bp=0&ie=utf-8&tn=baiduhome_pg&rsv_sug3=7&rsv_sug4=1080&rsv_sug1=5&oq=xxxxxx&rsv_sug2=1&f=3&rsp=0&inputT=6946
		
		String html=HttpUtils.get("http://www.baidu.com/baidu?wd=java+%C5%C0%B3%E6&tn=monline_4_dg");
		//System.out.println(html);
		//System.out.println(HttpUtils.getSum(html,"data-click="));
		
		String regex="\\<div.*\\>.*\\<\\/div\\>";
		/*List<String> outterList = StrUtils.getOutter(html, regex);
		
		for (int i = 0; i < outterList.size(); i++) {
			System.out.println(outterList.get(i));
		}*/
	}
}
