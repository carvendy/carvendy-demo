package com.carvendy.crawler.httpclient;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


public class HttpClientUtils {

	public static String encoding = "utf-8";

	public static String userAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.106 Safari/537.36";

	static String cookies;
	
	public static boolean proxy = false;
	
	// 创建CookieStore实例
	public static CookieStore cookieStore = null;
	public static HttpClientContext context = null;

	public static String ip;
	public static int port;

	// --------get&set--------------
	public boolean isProxy() {
		return proxy;
	}

	public void setProxy(boolean proxy) {
		this.proxy = proxy;
	}

	/*
	 * 更改代理
	 */
	public static void setProxy(String ip, int port) {
		HttpClientUtils.ip = ip;
		HttpClientUtils.port = port;
	}

	/*
	 * get 方式
	 */
	public static String getMethod(String url) {
		return getMethod(url, null,null);
	}
	
	public static String getMethod(String url,String cookies){
		return getMethod(url, cookies,null);
	}
	
	public static String getMethodProxy(String url,String proxy){
		return getMethod(url, null,proxy);
	}

	public static String getStatusNum(String url){
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		HttpGet request = new HttpGet(url);
		request.setHeader("User-Agent", userAgent);
		
		request.setHeader("Content-Type", "text/html; charset=utf-8");
		
		//setTimeOut(request);
		
		try {
			response = getResponse(httpClient, request);
			return response.getStatusLine()+"";
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "";
	}
	
	public static String getMethod(String url,String cookie,String proxy) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		// 创建httpget.
		HttpGet request = new HttpGet(url);
		//request.setHeader("User-Agent", userAgent);
		request.setHeader("User-Agent", userAgent);
		
		request.setHeader("Connection", "keep-alive");
		request.setHeader("Content-Type", "text/html; charset=utf-8");
		
		setTimeOut(request);
		
		checkIsProxy(request, proxy);
		
		if(StringUtils.isNotBlank(cookie)){
			request.setHeader("Cookie", cookie);
		}
		
		if(request.getHeaders("cookies")==null
				&&StringUtils.isNotBlank(cookies)){
			request.setHeader("Cookie", cookies);
		}
		
		try {
			response = getResponse(httpClient, request);
			//System.out.println("response,"+response);
			//设置cookies
			//setCookieStore(response);
			
			//setContext();

			return getHtml(response);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			close(httpClient, response);
		}

		return null;
	}

	private static void setTimeOut(HttpGet request) {
		int time=10000;
		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(time)
				.setConnectTimeout(time).build();//设置请求和传输超时时间
		request.setConfig(requestConfig);
	}
	
	public static String postMethod(String url, Map<String, String> paramMap) {
		return postMethodAddCookies(url, paramMap, null);
	}
	
	
	public static String locationUrl;
	public static String postMethodAddCookies(String url, Map<String, String> paramMap, String cookies) {
		// 创建默认的httpClient实例.
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		HttpPost request = new HttpPost(url);
		//request.setConfig(createConfig(5000, true));
		
		//使用代理
		checkIsProxy(request);
		
		if(StringUtils.isNotBlank(cookies)){
			request.setHeader("Cookie", cookies);
		}
		
		// 创建参数队列
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		for (String key : paramMap.keySet()) {
			params.add(new BasicNameValuePair(key, paramMap.get(key)));
		}

		try {
			UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(
					params, encoding);
			request.setEntity(uefEntity);
			
			response = getResponse(httpClient, request);
			  
			//处理http返回码302的情况  
			if (response.getStatusLine().getStatusCode() == 302
					|| response.getStatusLine().getStatusCode() == 301) {  
				locationUrl=response.getLastHeader("Location").getValue();  
				//System.out.println("Location--->"+locationUrl);
				//return getMethodAddCookies(locationUrl,HttpClientUtils.cookies);
				
				request.abort();
				HttpGet httpGet = new HttpGet(locationUrl);
				CloseableHttpResponse response2 = httpClient.execute(httpGet);
				//设置cookie
				setCookies(response2);
				return getHtml(response2);
			}  
			
			return getHtml(response);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭连接,释放资源
			close(httpClient, response);
		}

		return null;
	}
	
	public static String postMethodDownload(String url, Map<String, String> paramMap, String cookies) {
		// 创建默认的httpClient实例.
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		HttpPost request = new HttpPost(url);
		
		String proxy2 = getProxy();
		String ip = proxy2.split(":")[0];
		String port = proxy2.split(":")[1];
		setProxy(ip, Integer.parseInt(port));
		
		//使用代理
		checkIsProxy(request);
		
		if(StringUtils.isNotBlank(cookies)){
			request.setHeader("Cookie", cookies);
		}
		
		// 创建参数队列
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		for (String key : paramMap.keySet()) {
			params.add(new BasicNameValuePair(key, paramMap.get(key)));
		}

		try {
			UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(
					params, encoding);
			request.setEntity(uefEntity);
			response = getResponse(httpClient, request);
			  
			//处理http返回码302的情况  
			if (response.getStatusLine().getStatusCode() == 302
					|| response.getStatusLine().getStatusCode() == 301) {  
				locationUrl=response.getLastHeader("Location").getValue();  
				System.out.println("Location--->"+locationUrl);
				
				return locationUrl;
			}  
			
			return getHtml(response);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭连接,释放资源
			close(httpClient, response);
		}

		return null;
	}
	
	
	public static void download(String url, String savePath) {

	}

	public void upload(String url, String path) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;

		try {
			HttpPost httppost = new HttpPost(url);

			FileBody bin = new FileBody(new File(path));
			StringBody comment = new StringBody("A binary file of some kind",
					ContentType.TEXT_PLAIN);
			HttpEntity reqEntity = MultipartEntityBuilder.create()
					.addPart("bin", bin).addPart("comment", comment).build();
			httppost.setEntity(reqEntity);

			response = httpClient.execute(httppost);

			//System.out.println(response.getStatusLine());
			HttpEntity resEntity = response.getEntity();
			if (resEntity != null) {
				System.out.println("Response content length: "
						+ resEntity.getContentLength());
			}
			EntityUtils.consume(resEntity);

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			close(httpClient, response);
		}
	}

	/*
	 * httpClient.getParams().setAuthenticationPreemptive(true);
	 * //如果代理需要密码验证，这里设置用户名密码 httpClient.getState().
	 * setProxyCredentials(AuthScope.ANY, new
	 * UsernamePasswordCredentials("llying.iteye.com","llying"));
	 */
	private static void checkIsProxy(HttpRequestBase request) {
		if (proxy) {
			HttpHost proxy = new HttpHost(ip, port);
			RequestConfig config = RequestConfig.custom().setProxy(proxy)
					.build();
			request.setConfig(config);
		}
	}
	
	private static void checkIsProxy(HttpRequestBase request,String proxyStr) {
		if (StringUtils.isNoneBlank(proxyStr)) {
			String[] split = proxyStr.split(":");
			String ip = split[0];
			Integer port =Integer.valueOf(split[1]);
			//System.out.print(","+ip+":"+port+",");
			HttpHost proxy = new HttpHost(ip, port);
			RequestConfig config = RequestConfig.custom().setProxy(proxy)
					.build();
			request.setConfig(config);
		}
	}

	private static void close(CloseableHttpClient httpClient,
			CloseableHttpResponse response) {
		if (response != null) {
			try {
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				response = null;
			}
		}

		if (httpClient != null) {
			try {
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				httpClient = null;
			}
		}
	}
	
	/*
	 * 响应数据
	 */
	public static Map<String,String> cookiesMap=new HashMap<String, String>();
	public static Map<String,String> headersMap=new HashMap<String, String>();
	
	private static CloseableHttpResponse getResponse(
			CloseableHttpClient httpClient, HttpRequestBase request)
			throws IOException, ClientProtocolException {
		
		//System.out.print("httpClient-"+httpClient+","+request);
		// 执行请求
		
	   CloseableHttpResponse response =null;
	   try{
		   response  = httpClient.execute(request);
	   }catch(Exception e){
		   e.printStackTrace();
		   try{
			   Thread.sleep(100);
		   }catch(Exception e2){
			   
		   }
		   System.err.print("服务器中断。。。。");
		   response  = httpClient.execute(request);
	   }
	
	   if(response==null){
		   try{
			   Thread.sleep(100);
		   }catch(Exception e2){
			   
		   }
		   response  = httpClient.execute(request);
	   }
	   
		// 打印响应状态
		//System.out.println("状态码： "+response.getStatusLine());
		String status = response.getStatusLine().toString();
		
		if(status.indexOf("200 OK") == -1){
			
		}
		
		//System.out.println("headers:");
	    HeaderIterator iterator = response.headerIterator();
	    /*while (iterator.hasNext()) {
	    	System.out.println("\t" + iterator.next());
	    }*/
		
		setCookies(response);
		
		return response;
	}
	
	private static String getHtml(HttpResponse response) throws ParseException, IOException{
		// 获取响应实体
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			// 打印响应内容
			return EntityUtils.toString(entity,"utf-8");
		}
		return "";
	}
	
	public static String getUrl(String html){
		if(StringUtils.isBlank(html)){
			return "";
		}
		return html.replaceAll(".*(http:.+)['\"].*", "$1");
	}
	
	private static void setCookies(CloseableHttpResponse response){
		cookies="";
		cookiesMap=new HashMap<String, String>();
		
		Header[] headers = response.getAllHeaders();
		for(Header header : headers){
			if("Set-Cookie".equals(header.getName())){
				cookies+=header.getValue()+";";
				if(cookiesMap.get("Set-Cookie")!=null){
					cookiesMap.put("Set-Cookie", cookiesMap.get("Set-Cookie")+";"+header.getValue());
				}else{
					cookiesMap.put("Set-Cookie", header.getValue());
				}
			}
		}
		
	}
	
	public static boolean checkProxyIp(String ip,int port,String url,int timeout){
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost request=new HttpPost(url);
		
		RequestConfig config = RequestConfig.custom()
				.setSocketTimeout(timeout)
				.setConnectTimeout(timeout)
				.setProxy(new HttpHost(ip, port))
					.build();
		
		request.setConfig(config);
		
		CloseableHttpResponse response = null;
		
		try {
			response = httpClient.execute(request);
			//System.out.print(",状态:"+response.getStatusLine()+",");
		} catch (ClientProtocolException e) {
			//e.printStackTrace();
			return false;
		} catch (IOException e) {
			//e.printStackTrace();
			return false;
		}
		
		if(response == null || response.getStatusLine()
				.toString().indexOf("200") == -1){
			return false;
		}
		
		return true;
	}
	
	public static boolean checkProxyIp(String ipAndPort,String url,int timeout){
		if("".equals(ipAndPort)||null==ipAndPort){
			return false;
		}
		String ipStr = ipAndPort.split(":")[0];
		int portInt = 80;
		try{
			portInt = Integer.valueOf(ipAndPort.split(":")[1]);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return checkProxyIp(ipStr,portInt, url, timeout);
	}
	
	public static void main(String[] args) {
		String clean_url = "http://www.youboy.com/s200911656.html";
	    String url = "http://cache.youboy.com/cleanUrl";

		//String data = getMethod(url);
	    
	    Map<String, String> paramMap=new HashMap<String, String>();
	    paramMap.put("clean_url", clean_url);
		String data=postMethod(url, paramMap);
		System.out.println(data);
	}

	public static void setCookies(String cookies) {
		HttpClientUtils.cookies = cookies;
	}
	
	public static String getProxy(){
		String[] proxyArr =new String[]{
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
				"211.144.81.69:18000"
		};
		
		Random random = new Random();
		int index = random.nextInt(proxyArr.length);
		return  proxyArr[index];
	}
	
	public static String getUserAgent (){
		String[] userAgentArr  = new String[]{
			"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.130 Safari/537.36",
			"Mozilla/5.0 (Windows NT 5.1; rv:5.0) Gecko/20100101 Firefox/5.0",
			"Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.2; Trident/4.0; .NET CLR 1.1.4322; .NET CLR 2.0.50727; .NET4.0E; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729; .NET4.0C)",
			"Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.2; .NET CLR 1.1.4322; .NET CLR 2.0.50727; .NET4.0E; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729; .NET4.0C)",
			"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 2.0.50727)",
			"Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; .NET4.0C; .NET4.0E)",
			"Opera/9.80 (Windows NT 5.1; U; zh-cn) Presto/2.9.168 Version/11.50",
			"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)",
			"Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.1; Trident/4.0; .NET CLR 2.0.50727; .NET CLR 3.0.04506.648; .NET CLR 3.5.21022; .NET4.0E; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729; .NET4.0C)",
			"Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN) AppleWebKit/533.21.1 (KHTML, like Gecko) Version/5.0.5 Safari/533.21.1",
			"Mozilla/5.0 (Windows; U; Windows NT 5.1; ) AppleWebKit/534.12 (KHTML, like Gecko) Maxthon/3.0 Safari/534.12",
			"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 2.0.50727; TheWorld)",
			"Mozilla/5.0 (iPod; U; CPU iPhone OS 4_3_2 like Mac OS X; zh-cn) AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8H7 Safari/6533.18.5",
			"Mozilla/5.0 (iPhone; U; CPU iPhone OS 4_3_2 like Mac OS X; zh-cn) AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8H7 Safari/6533.18.5",
			"MQQBrowser/25 (Linux; U; 2.3.3; zh-cn; HTC Desire S Build/GRI40;480*800)",
			"Mozilla/5.0 (Linux; U; Android 2.3.3; zh-cn; HTC_DesireS_S510e Build/GRI40) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1",
			"Mozilla/5.0 (SymbianOS/9.3; U; Series60/3.2 NokiaE75-1 /110.48.125 Profile/MIDP-2.1 Configuration/CLDC-1.1 ) AppleWebKit/413 (KHTML, like Gecko) Safari/413",
			"Mozilla/5.0 (iPad; U; CPU OS 4_3_3 like Mac OS X; zh-cn) AppleWebKit/533.17.9 (KHTML, like Gecko) Mobile/8J2",
			"Mozilla/5.0 (Windows NT 5.2) AppleWebKit/534.30 (KHTML, like Gecko) Chrome/12.0.742.122 Safari/534.30",
			"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/535.1 (KHTML, like Gecko) ",
			"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/534.51.22 (KHTML, like Gecko) Version/5.1.1 Safari/534.51.22",
			"Mozilla/5.0 (iPhone; CPU iPhone OS 5_0 like Mac OS X) AppleWebKit/534.46 (KHTML, like Gecko) Version/5.1 Mobile/9A5313e Safari/7534.48.3",
			"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/14.0.835.202 Safari/535.1",
			"Mozilla/5.0 (compatible; MSIE 9.0; Windows Phone OS 7.5; Trident/5.0; IEMobile/9.0; SAMSUNG; OMNIA7)",
			"Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0; XBLWP7; ZuneWP7)",
			"Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Maxthon 2.0)",
			"Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Trident/4.0; SE 2.X MetaSr 1.0; SE 2.X MetaSr 1.0; .NET CLR 2.0.50727; SE 2.X MetaSr 1.0)",
			"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_0) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.56 Safari/535.11"
		};
		
		Random random = new Random();
		int index = random.nextInt(userAgentArr.length);
		return  userAgentArr[index];
	}


}
