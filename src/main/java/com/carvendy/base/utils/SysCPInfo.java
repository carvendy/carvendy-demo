package com.carvendy.base.utils;

import java.lang.management.ManagementFactory;


import com.sun.management.OperatingSystemMXBean;

public class SysCPInfo {

	static int kb = 1024;
	private static final int CPUTIME = 5000;
	private static final int PERCENT = 100;
	private static final int FAULTLENGTH = 10;

	public static void main(String[] args) {

		long totalMemory = Runtime.getRuntime().totalMemory() / kb;
		// 剩余多少内存
		long freeMemory = Runtime.getRuntime().freeMemory() / kb;
		// 最大可使用内存
		long maxMemory = Runtime.getRuntime().maxMemory() / kb;

		System.out.println(maxMemory);
		OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory
				.getOperatingSystemMXBean();

		// 操作系统
		String osName = System.getProperty("os.name");
		System.out.println(osName);

		// 总的物理内存
		long totalMemorySize = osmxb.getTotalPhysicalMemorySize() / kb;

		// 剩余的物理内存
		long freePhysicalMemorySize = osmxb.getFreePhysicalMemorySize() / kb;

		// 已使用的物理内存
		long usedMemory = (osmxb.getTotalPhysicalMemorySize() - osmxb
				.getFreePhysicalMemorySize()) / kb;

		
		System.out.println("totalMemorySize:"+totalMemorySize+" freePhysicalMemorySize:" +
				freePhysicalMemorySize+" usedMemory:"+usedMemory);
		
	}
	
	public void test(){
		/*
		 *  系统的一些基本配置
		 * 
		Properties prop = System.getProperties();
		for(Object key:prop.keySet()){
			System.out.println(key+"--"+prop.get(key));
			
		}*/
		System.getProperties().put(3, "hehei-3");
		System.out.println(System.getProperties().get(3));
		
		System.getProperties().put('3', "hehei-'3'");
		System.out.println(System.getProperties().get("3"));
		
		System.getProperties().put("3", "hehei-\"3\"");
		System.out.println(System.getProperties().get("3"));
	}
	

	
}
