package com.carvendy.cxf;

import org.apache.cxf.aegis.databinding.AegisDatabinding;
import org.apache.cxf.frontend.ServerFactoryBean;

public class MainTest {
	
	public static void main(String[] args) throws InterruptedException {
		HelloWorldImpl hwi = new HelloWorldImpl();
		ServerFactoryBean fac = new ServerFactoryBean();
		fac.setServiceClass(HelloWorld.class);
		fac.setAddress("http://localhost:8899/hello");
		fac.setServiceBean(hwi);
		fac.getServiceFactory().setDataBinding(new AegisDatabinding());
		fac.create();
	    System.out.println("Server ready...");
	
	    Thread.sleep(5 * 60 * 1000);
	    System.out.println("Server exiting");
	    System.exit(0);
	}
}

