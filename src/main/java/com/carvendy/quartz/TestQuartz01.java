package com.carvendy.quartz;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.quartz.impl.StdSchedulerFactory;


public class TestQuartz01 {
	
	static Properties prop=new Properties();
	static{
		
			InputStream in = ClassLoader.getSystemResourceAsStream("quartz/quartz.properties");
			System.out.println();
			try {
				prop.load(in);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
	}
	
	public static void main(String[] args) throws Exception {
        System.out.println("start");
        //System.getProperties().put("org.quartz.properties",ClassLoader.getSystemResource("quartz/quartz.properties")); 
        
        System.getProperties().put("org.quartz.properties",prop); 
        
        StdSchedulerFactory.getDefaultScheduler().start();
        
        System.out.println("end"); 
    }
}
