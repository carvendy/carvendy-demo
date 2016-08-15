package com.carvendy.base.url;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class HttpUtils {
	
	private static String fileDir="d:/download/haha/";
	public static String cookies;

	//----------  get&&post  ----------------
	public static String get(String urlStr){
		//proxy();
		StringBuilder sb=new StringBuilder();
		InputStream is=null;
		BufferedReader br=null;
		URL url=null;
		
		try {
			url=new URL(urlStr);
			URLConnection conn = url.openConnection();
			//先设置请求头，再获取流
			conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			//String cookies="bdshare_firstime=1430879723217; __gads=ID=d66f1281c90c85e4:T=1430890640:S=ALNI_MaegQgVSR_C4C1ZptvWOiPLu3e3jA; pgv_pvi=6782529536; loginInfo=729440328@qq.com; CNZZDATA5111289=cnzz_eid%3D2897662-1432336641-http%253A%252F%252Fnews.youboy.com%252F%26ntime%3D1433058869; _sessionId=fba32016-c436-40e2-ad66-8840d9f85ea7; JSESSIONID=aaa-m-gN0QcT8bGi8US2u; __utma=123990018.1736658974.1430890545.1433059672.1433123450.22; __utmc=123990018; __utmz=123990018.1433054328.20.8.utmcsr=test.youboy.com:8080|utmccn=(referral)|utmcmd=referral|utmcct=/index.do; CNZZDATA5236406=cnzz_eid%3D1060813058-1431482911-%26ntime%3D1433122860; CNZZDATA4523560=cnzz_eid%3D1987824354-1431480445-%26ntime%3D1433121249; _41110151171138385608=671120_1431511722141; CNZZDATA5293032=cnzz_eid%3D497327380-1431508555-http%253A%252F%252Fmy.youboy.com%252F%26ntime%3D1433122302; CNZZDATA5838509=cnzz_eid%3D1232475464-1431509780-http%253A%252F%252Fmy.youboy.com%252F%26ntime%3D1433121299";
			conn.setRequestProperty("Cookie", cookies);
			is = conn.getInputStream();  
		
			//转码
			try{
				br=new BufferedReader(new InputStreamReader(is,getFileEncoding(conn)));
			}catch(Exception e){
				br=new BufferedReader(new InputStreamReader(is,getFileEncoding2(url)));
			}
			
			String str="";
			while((str=br.readLine())!=null){
				sb.append(str+"\n");
			}
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(br!=null){
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if(is!=null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return sb.toString();
	}

	
	public static String post(String urlStr,String params){
		StringBuilder sb=new StringBuilder();
		URL url=null;
		
		PrintWriter out =null;
		InputStream is=null;
		BufferedReader br=null;
		
		try {
			url=new URL(urlStr);
			URLConnection  conn = url.openConnection();
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			 
			//post,设置这个
			conn.setDoInput(true);
			conn.setDoOutput(true);
			
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(params);
           // flush输出流的缓冲
            out.flush();
			
            is = conn.getInputStream();
			//转码
			br=new BufferedReader(new InputStreamReader(is));
			String str="";
			while((str=br.readLine())!=null){
				sb.append(str+"\n");
			}
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(out!=null){
				out.close();
			}
			
			if(br!=null){
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}finally{
					br=null;
				}
			}
			
			if(is!=null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}finally{
					is=null;
				}
			}
		}
		
		return sb.toString();
	}
	//---------------------------------------
	
	/*
	 * 统计出现次数
	 */
	public static int getSum(String content,String findStr){
		Pattern p = Pattern.compile(findStr);
		Matcher m = p.matcher(content);
		
		//System.out.println(m.find()+"--"+m.group());
		int sum=0;
		while (m.find()) {
			sum++;
			int gc = m.groupCount();
			for(int i = 0; i <= gc; i++){
				 //System.out.println( "m.group("+sum+"):" + m.group());  // 打印所有
			}
			
		} 
		return sum;
	}
	
	/*
	 * 通过路径返回 连接
	 */
	public static URLConnection getConn(String urlStr){
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
	
	
	/*
	 * 使用代理
	 */
	public static void proxy(){
		System.setProperty("http.maxRedirects", "50");  
        System.getProperties().setProperty("proxySet", "true");  
        // 如果不设置，只要代理IP和代理端口正确,此项不设置也可以  
        String ip = "210.83.81.243";  //80 
        ip = "223.4.168.234";  //3128
        
        System.getProperties().setProperty("http.proxyHost", ip);  
        System.getProperties().setProperty("http.proxyPort", "3128");
	}
	
	/*
	 * 下载图片(有后缀的)
	 */
	public static void download(String urlStr){
		URLConnection conn = getConn(urlStr);
		InputStream is =null;
		FileOutputStream fos =null;	
		
		String realName=urlStr.substring(urlStr.lastIndexOf("/"),urlStr.lastIndexOf("."));
		String suffix=urlStr.substring(urlStr.lastIndexOf("."));
		File dir=new File(fileDir);
		dir.mkdirs();
		
		try {
			conn.connect();
			is=conn.getInputStream();
			
			File file = new File(fileDir+"/"+realName+"_"+
					new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+suffix);
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
	
	/*
	 * 检测网页(编码)--1.遍历，编码集比较
	 */
	public static String getFileEncoding(URLConnection conn) {  
		String strencoding="";
	
		Map<String, List<String>> map = conn.getHeaderFields();
		Set<String> keys = map.keySet();
		Iterator<String> iterator = keys.iterator();

		// 遍历,查找字符编码
		String key = null;
		String tmp = null;
		while (iterator.hasNext()) {
		    key = iterator.next();
		    tmp = map.get(key).toString().toLowerCase();
		    // 获取content-type charset
		    if (key != null && key.equals("Content-Type")) {
		        int m = tmp.indexOf("charset=");
		        if (m != -1) {
		            strencoding = tmp.substring(m + 8).replace("]", "");
		            return strencoding;
		        }
		    }
		}
  
		 return strencoding;
    }  
	
	/*
	 * 检测网页(编码)--2.解析<meta>
	 */
	public static String getFileEncoding2(URL url){
		String strencoding="";
		StringBuffer sb = new StringBuffer();
		String line;
		try {
		    BufferedReader in = new BufferedReader(new InputStreamReader(url
		            .openStream()));
		    while ((line = in.readLine()) != null) {
		        sb.append(line);
		    }
		    in.close();
		} catch (Exception e) { // Report any errors that arise
		    System.err.println(e);
		    System.err
		            .println("Usage:   java   HttpClient   <URL>   [<filename>]");
		}
		String htmlcode = sb.toString();
		// 解析html源码，取出<meta />区域，并取出charset
		String strbegin = "<meta";
		String strend = ">";
		String strtmp;
		int begin = htmlcode.indexOf(strbegin);
		int end = -1;
		int inttmp;
		while (begin > -1) {
		    end = htmlcode.substring(begin).indexOf(strend);
		    if (begin > -1 && end > -1) {
		        strtmp = htmlcode.substring(begin, begin + end).toLowerCase();
		        inttmp = strtmp.indexOf("charset");
		        if (inttmp > -1) {
		            strencoding = strtmp.substring(inttmp + 7, end).replace(
		                    "=", "").replace("/", "").replace("\"", "")
		                    .replace("\'", "").replace(" ", "");
		            return strencoding;
		        }
		    }
		    htmlcode = htmlcode.substring(begin);
		    begin = htmlcode.indexOf(strbegin);
		}
		return strencoding;
	}
	
	
	
	/**main方法测试*/
	public static void main(String[] args) throws UnsupportedEncodingException {
		//http://www.baidu.com/baidu?wd=java+%C5%C0%B3%E6&tn=monline_4_dg
		//http://www.baidu.com/s?wd=%E5%B0%8F%E6%98%9F%E6%98%9F%E5%B0%8F%E6%98%9F%E6%98%9F&ie=utf-8&tn=baiduhome_pg&oq=%E5%B0%8F%E6%98%9F%E6%98%9F%E5%B0%8F%E6%98%9F%E6%98%9F&f=3&rsv_spt=1&issp=1&rsv_bp=0&rsv_sug3=7&rsv_sug4=1080&rsv_sug1=5&rsv_sug2=1&rsp=0&inputT=6946&pn=10&usm=1&rsv_page=1
		//http://www.baidu.com/s?wd=%E5%B0%8F%E6%98%9F%E6%98%9F%E5%B0%8F%E6%98%9F%E6%98%9F&rsv_spt=1&issp=1&rsv_bp=0&ie=utf-8&tn=baiduhome_pg&rsv_sug3=7&rsv_sug4=1080&rsv_sug1=5&oq=xxxxxx&rsv_sug2=1&f=3&rsp=0&inputT=6946
		
		/*String html=get("http://www.baidu.com/baidu?wd=java+%C5%C0%B3%E6&tn=monline_4_dg");
		//System.out.println(html);

		System.out.println(getSum(html,"class=\"m\""));*/
		
		//http://ww2.sinaimg.cn/square/61e7f8b0jw1eik78ehdtjj20qo140aiu.jpg
		///download("http://ww2.sinaimg.cn/bmiddle/a7fcd0bcjw1eimnbxbya3j2050050t8n.jpg");
	
		/*String clean_url = "http://www.youboy.com/s200911656.html";
	    String url = "http://cache.youboy.com/cleanUrl";
	    
	    String data = post(url, "&clean_url="+clean_url);
	    System.out.println(data);*/
		
		
		String url="http://my.youboy.com/shangpu/index.do";
		System.out.println(get(url));
	}
	
	

}
