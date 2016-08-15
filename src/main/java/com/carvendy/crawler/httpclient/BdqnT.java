package com.carvendy.crawler.httpclient;

import java.util.HashMap;
import java.util.Map;

public class BdqnT {

	/*
	 * 模拟登录-cookie
	 * http://home.bdqn.cn/forum.php?mod=post&action=newthread&fid=99
	 */
	public static void loginByCookie(){
		String cookies="qwLE_7175_nofavfid=1; qwLE_7175_home_readfeed=1427776273; qwLE_7175_saltkey=cZK4z54S; qwLE_7175_lastvisit=1428895451; qwLE_7175_auth=ff67tx6qQ%2FJ4Hq5t4ZY7%2F%2BCjqZYtjqfMPGbH7hfLG3AA7sAzV3gRG4IcrecH8esrHqSyTs4jILVFCUNtxuQcc7n04QlWV4CXNqeUa0%2Bm40%2BZ22CJpaQB%2BEF7yfkcyvY2aJc3lKc; qwLE_7175_loginTime=1430108885; qwLE_7175_security_cookiereport=9a21ilqoLNeOpGJy0uIJAdOMyF2r7vT8cCVR1IsQPGomUjEG54ex; qwLE_7175_st_t=161040%7C1430110383%7Ca5221b881a849e0cc4f77dc8c19dc207; qwLE_7175_forum_lastvisit=D_55_1426739943D_188_1427274425D_99_1429504191D_167_1429764483D_180_1430108892D_221_1430109980D_122_1430110165D_175_1430110383; qwLE_7175_visitedfid=175D122D221D180D183D167D99D279D179D191; qwLE_7175_smile=4D1; qwLE_7175_seccode=3050.2c295621fd0ad5d9eb; qwLE_7175_clearUserdata=forum; qwLE_7175_st_p=161040%7C1430110486%7Cfbe1b38c54b3aa83e3eadbc9eb138297; qwLE_7175_viewid=tid_54607; qwLE_7175_sid=Gtu03k; qwLE_7175_lip=61.174.9.85%2C1430112987; qwLE_7175_lastact=1430112995%09api.php%09js; Hm_lvt_7ced51c827c3a33db126bfa816d97326=1429590562,1429763241,1429849633,1430108842; Hm_lpvt_7ced51c827c3a33db126bfa816d97326=1430112996";
		String url="http://home.bdqn.cn/portal.php";
		//跟user-Agent绑定了
		HttpClientUtils.userAgent="Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.89 Safari/537.36";
		String html = HttpClientUtils.getMethod(url, cookies);
		System.out.println(html);
	}
	
	/*
	 * 密码
	 */
	public static void login(){
		String loginUrl="http://i.bdqn.cn/login";
		Map<String, String> paramMap=new HashMap<String, String>();
		paramMap.put("UserLoginForm[redirect_url]", "http://home.bdqn.cn/portal.php");
		paramMap.put("UserLoginForm[username]", "carvendy@163.com");
		paramMap.put("UserLoginForm[password]", "dd457a21cd542495764509290bb08562");
		//										 dd457a21cd542495764509290bb08562
		paramMap.put("yt0", "登 录");
		String html = HttpClientUtils.postMethod(loginUrl, paramMap);
		System.out.println(HttpClientUtils.cookies+"\n");
		
		System.out.println(html);
		
	}
	
	
	public static void publish(){
		String cookies="qwLE_7175_nofavfid=1; qwLE_7175_home_readfeed=1427776273; qwLE_7175_saltkey=cZK4z54S; qwLE_7175_lastvisit=1428895451; qwLE_7175_auth=ff67tx6qQ%2FJ4Hq5t4ZY7%2F%2BCjqZYtjqfMPGbH7hfLG3AA7sAzV3gRG4IcrecH8esrHqSyTs4jILVFCUNtxuQcc7n04QlWV4CXNqeUa0%2Bm40%2BZ22CJpaQB%2BEF7yfkcyvY2aJc3lKc; qwLE_7175_loginTime=1430108885; qwLE_7175_security_cookiereport=9a21ilqoLNeOpGJy0uIJAdOMyF2r7vT8cCVR1IsQPGomUjEG54ex; qwLE_7175_st_t=161040%7C1430110383%7Ca5221b881a849e0cc4f77dc8c19dc207; qwLE_7175_forum_lastvisit=D_55_1426739943D_188_1427274425D_99_1429504191D_167_1429764483D_180_1430108892D_221_1430109980D_122_1430110165D_175_1430110383; qwLE_7175_visitedfid=175D122D221D180D183D167D99D279D179D191; qwLE_7175_smile=4D1; qwLE_7175_seccode=3050.2c295621fd0ad5d9eb; qwLE_7175_clearUserdata=forum; qwLE_7175_st_p=161040%7C1430110486%7Cfbe1b38c54b3aa83e3eadbc9eb138297; qwLE_7175_viewid=tid_54607; qwLE_7175_sid=Gtu03k; qwLE_7175_lip=61.174.9.85%2C1430112987; qwLE_7175_lastact=1430112995%09api.php%09js; Hm_lvt_7ced51c827c3a33db126bfa816d97326=1429590562,1429763241,1429849633,1430108842; Hm_lpvt_7ced51c827c3a33db126bfa816d97326=1430112996";
		//String url="http://home.bdqn.cn/forum.php?mod=post&action=newthread&fid=99";
		String url="http://home.bdqn.cn/forum.php?mod=post&amp;action=newthread&amp;fid=99&amp;extra=&amp;topicsubmit=yes";
		HttpClientUtils.userAgent="Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.89 Safari/537.36";
		String html = HttpClientUtils.getMethod(url, cookies);
		System.out.println(html);
	}
	
	public static void main(String[] args) {
		//loginByCookie();
		login();
		//publish();
	}
}
