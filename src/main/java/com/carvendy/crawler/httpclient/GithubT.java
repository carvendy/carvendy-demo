package com.carvendy.crawler.httpclient;

import java.util.HashMap;
import java.util.Map;

public class GithubT {

	public static void test(){
		String loginUrl="https://github.com/session";
		Map<String, String> paramMap=new HashMap<String, String>();
		paramMap.put("login", "1150483011@qq.com");
		paramMap.put("password", "github10086XHL");
		
		String cookies="logged_in=no; _ga=GA1.2.1313897348.1429079877; _octo=GH1.1.2066623551.1429079878; _gh_sess=eyJzZXNzaW9uX2lkIjoiOTkyODg4YzQ4ODA3MjJhY2UwZmNlZDE3NjA1ZGQ3ZTgiLCJfY3NyZl90b2tlbiI6IlQ3ZytIdVZhNkhoQ2VuekV6U0tnOG05Y0U1RHlRL0k0SWlrdXlyTFBtUGc9IiwicmVmZXJyYWxfY29kZSI6Imh0dHBzOi8vZ2l0aHViLmNvbS8ifQ%3D%3D--c97aca4b17a1fbd88e5b6b391eefdd2e05aa5c9d; tz=Asia%2FShanghai; _gat=1";
		String html = HttpClientUtils.postMethodAddCookies(loginUrl, paramMap,cookies);
		System.out.println(html);
	}
	
	public static void test2(){
		String url="https://github.com";
		String cookies="_octo=GH1.1.1520065674.1426653919; _gat=1; logged_in=yes; dotcom_user=carvendy; _gh_sess=eyJsYXN0X3dyaXRlIjoxNDMwMTA1MTY4MjA1LCJzZXNzaW9uX2lkIjoiYzI2YzEyODg4Y2Q3OTZmZGU3MGIxNWU4ZWRjZmMxNzAiLCJjb250ZXh0IjoiLyJ9--82236400b73773aa0f29ed4c47c7c6fc54008a9a; user_session=nHe3HCiCkY1AZzJjZSur9AOD_XcEnJ26s1nxiUiSu_AVBt_SVT2syvRHHez1TrMrBcs99IX8mIj9C938; tz=Asia%2FShanghai; _ga=GA1.2.110952478.1426653918";
		String html = HttpClientUtils.getMethod(url, cookies);
		System.out.println(html);
	}
	
	
	public static void main(String[] args) {
		test();
	}
}
