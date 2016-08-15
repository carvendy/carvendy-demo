package com.carvendy.base.reflact;

import java.lang.reflect.Method;

public class ReflactUtils {

	public static void print(Object obj,Class myclass) {
		Class myClass = (Class) obj.getClass();
		Method[] methods = myClass.getDeclaredMethods();
		
		try{
			//遍历所有方法
			for(int i=0;i<methods.length;i++){
				// 有get的方法，打印
				if(methods[i].getName().indexOf("get")!=-1){
					System.out.print(methods[i].getName()+"---");
					System.out.println(methods[i].invoke(obj)+"");
					
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("----------------------------------");
	} 
	
	public static void main(String[] args) {
		
	}
}
