package com.carvendy.run;

import com.carvendy.crawler.httpclient.HttpClientUtils;


public class TestNginx {

	private  static int count =0;
	
	public static void main(String[] args) {
		
		runMutilThread(100);
		
		System.out.println(count);
	}
	
	private static int runSingle(){
		String url = "http://192.168.20.59/test.html";
		
		long start  = System.currentTimeMillis();
		
		int i;
		for (i = 0; i < 10000; i++) {
			HttpClientUtils.getMethod(url);
			//Thread.sleep(10);
			
			long end  = System.currentTimeMillis();
			if((end - start) / 1000 > 10){
				break;
			}
			//System.out.println((end - start) / 1000);
		}
		
		long end  = System.currentTimeMillis();
		System.out.println(i);
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return i;
	}

	private static void runMutilThread(int thread){
		final String url = "http://192.168.20.59:8080/test/index.html";
		
		final long start  = System.currentTimeMillis();
		
		for(int i = 0 ;i < thread ;i++ ){
			new Thread(){
				public void run() {
					long end ;
					while(true){
						end  = System.currentTimeMillis();
						if((end - start) / 1000 > 60 ){
							break;
						}
						
						//System.out.println(Thread.currentThread().getName());
						HttpClientUtils.getMethod(url);
						count++;
						
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				};
			}.start();
		}
		
	}
}
