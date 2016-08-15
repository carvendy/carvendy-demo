package com.carvendy.base.url;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class Main {
	
	//失效的时候，要更新cookie
	static String cookies="bdshare_firstime=1430879723217; __gads=ID=d66f1281c90c85e4:T=1430890640:S=ALNI_MaegQgVSR_C4C1ZptvWOiPLu3e3jA; pgv_pvi=6782529536; loginInfo=729440328@qq.com; CNZZDATA5111289=cnzz_eid%3D2897662-1432336641-http%253A%252F%252Fnews.youboy.com%252F%26ntime%3D1433058869; _sessionId=fba32016-c436-40e2-ad66-8840d9f85ea7; JSESSIONID=aaa-m-gN0QcT8bGi8US2u; __utma=123990018.1736658974.1430890545.1433059672.1433123450.22; __utmc=123990018; __utmz=123990018.1433054328.20.8.utmcsr=test.youboy.com:8080|utmccn=(referral)|utmcmd=referral|utmcct=/index.do; CNZZDATA5236406=cnzz_eid%3D1060813058-1431482911-%26ntime%3D1433122860; CNZZDATA4523560=cnzz_eid%3D1987824354-1431480445-%26ntime%3D1433121249; _41110151171138385608=671120_1431511722141; CNZZDATA5293032=cnzz_eid%3D497327380-1431508555-http%253A%252F%252Fmy.youboy.com%252F%26ntime%3D1433122302; CNZZDATA5838509=cnzz_eid%3D1232475464-1431509780-http%253A%252F%252Fmy.youboy.com%252F%26ntime%3D1433121299";
	
	public static void main(String[] args) {
		
		String path="d://urls.txt";
		run(path);
	}
	
	private static void run(String path){
		File file =new File(path);
		FileReader fr=null;
		BufferedReader br=null;
		
		try {
			fr=new FileReader(file);
			br=new BufferedReader(fr);
			
			String data=null;
			while((data=br.readLine())!=null){
				if(data!=null){data=data.trim();}
				System.out.println(data+"-"+getCode(data));
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(br!=null){
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}finally{
					br=null;
				}
			}
			
			if(fr!=null){
				try {
					fr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}finally{
					fr=null;
				}
			}
		}
		
	}
	
	
	private static String getCode(String urlStr){
		HttpURLConnection conn = (HttpURLConnection) getConn(urlStr);
		conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
		
		//失效的时候，要更新cookie
		conn.addRequestProperty("Cookie", cookies);//设置登录状态
		
		StringBuffer sb=new StringBuffer();
		 try {
			 InputStream is = conn.getInputStream();  
				
				//转码
				BufferedReader br = new BufferedReader(new InputStreamReader(is,"utf-8"));
				String str="";
				while((str=br.readLine())!=null){
					sb.append(str+"\n");
				}
				//输出页面内容
				//System.out.println(sb);
			return conn.getResponseCode()+"";
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "0";
	}
	
	private static URLConnection getConn(String urlStr){
		URLConnection conn =null;
		URL url=null;
		try {
			url=new URL(urlStr);
			conn = url.openConnection();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}
