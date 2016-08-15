package com.carvendy.design.pattern.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;


public class Client {
	static public void main(String[] args) throws Throwable {
		//RealSubject rs = new RealSubject(); // 在这里指定被代理类（需要修改，改为其他代理类）
		
		Subject rs=new NewSubject();
		InvocationHandler ds = new DynamicSubject(rs); // 初始化代理类
		Subject subject = (Subject) Proxy.newProxyInstance(
				rs.getClass().getClassLoader(),//实现类
				rs.getClass().getInterfaces(),//接口
				ds);//具体的代理类
		subject.request();
	}
}