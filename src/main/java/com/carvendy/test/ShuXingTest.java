package com.carvendy.test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ShuXingTest {

	
	public static void main(String[] args) {
		ShuXing sx = new ShuXing();
		sx.setA("a1");
		sx.setB("B");
		sx.setC("c1");
		obj(sx);
	}
	
	private static void obj(Object obj){
		Class clazz = obj.getClass();
		Field[] fields = clazz.getDeclaredFields();
		String str = "";
		
		for(int i=0; i< fields.length;i++){
			Field f =fields[i];
			String name = f.getName();
			String methodName ="get"+name.substring(0,1).toUpperCase()+name.substring(1);
			try {
				Method method = clazz.getMethod(methodName);
				Object invoke = method.invoke(obj);
				//System.out.println(name+"="+invoke);
				
				str += name+"="+invoke;
				if(i < fields.length - 1){
					str += "&";
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		System.out.println(str);
	}
}
