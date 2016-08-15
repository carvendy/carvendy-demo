package com.carvendy.crawler.htmlunit;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.CookieManager;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.util.Cookie;

public class HtmlUnitUtils {

	public static String getMethod(String url){
		WebClient wc = new WebClient(BrowserVersion.INTERNET_EXPLORER_8);
		wc.getOptions().setJavaScriptEnabled(true);
		wc.getOptions().setCssEnabled(false);
		
		HtmlPage page =null;
		try {
			page = wc.getPage(url);

		} catch (FailingHttpStatusCodeException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return page==null?"":page.asXml();
	}
	
	
	public static String getMethodAjax(String url){
		WebClient wc = new WebClient(BrowserVersion.INTERNET_EXPLORER_8);
		wc.getOptions().setJavaScriptEnabled(true);
		wc.getOptions().setCssEnabled(false);
		wc.getOptions().setActiveXNative(false);
		//wc.getOptions().setProxyConfig(proxyConfig);
		wc.setAjaxController(new NicelyResynchronizingAjaxController());//设置ajax代理
		wc.getOptions().setDoNotTrackEnabled(false);
		
		wc.setJavaScriptTimeout(2000);
		
		HtmlPage page =null;
		try {
			page = wc.getPage(url);
			Thread.sleep(2000);
		} catch (FailingHttpStatusCodeException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return page==null?"":page.asXml();
	}
	
	public static void test() throws FailingHttpStatusCodeException, MalformedURLException, IOException{
		//创建webclient
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        //htmlunit 对css和javascript的支持不好，所以请关闭之
        webClient.getOptions().setJavaScriptEnabled(false);
        webClient.getOptions().setCssEnabled(false);
        HtmlPage page = (HtmlPage)webClient.getPage("http://www.baidu.com/");
        //查找所有div
        List<?> hbList = page.getByXPath("//div");
        HtmlDivision hb = (HtmlDivision)hbList.get(0);
        System.out.println(hb.toString());
        //查找并获取特定input
        List<?> inputList = page.getByXPath("//input[@id='su']");
        HtmlInput input = (HtmlInput)inputList.get(0);
        System.out.println(input.toString());
        //关闭webclient
        webClient.closeAllWindows();
	}
	
	public static void main(String[] args) throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		String url="http://www.baidu.com";
		///getMethod(url);
		
		//创建webclient
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        //htmlunit 对css和javascript的支持不好，所以请关闭之
        webClient.getOptions().setJavaScriptEnabled(false);
        webClient.getOptions().setCssEnabled(false);
        HtmlPage page = (HtmlPage)webClient.getPage("http://www.baidu.com/");
        //获取搜索输入框并提交搜索内容
        HtmlInput input = (HtmlInput)page.getHtmlElementById("kw");
        System.out.println(input.toString());
        input.setValueAttribute("时光倒流七十年");
        System.out.println(input.toString());
        //获取搜索按钮并点击
        HtmlInput btn = (HtmlInput)page.getHtmlElementById("su");
        HtmlPage page2 = btn.click();
        //输出新页面的文本
       // System.out.println(page2.asText());
        
        System.out.println(page2.asXml());
	}
}
