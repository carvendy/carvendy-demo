package com.carvendy.design.pattern.chainOfResponsibility;

public class Girl {

	/*
	 * (1)
		Chain of Responsibility职责链模式：
		为了避免请求的发送者和接收者之间的耦合关系，使多个接受对象都有机会处理请求。将这些对象连成一条链，
		并沿着这条链传递该请求，直到有一个对象处理它为止。
		-->
		要沿着链转发请求，并保证接受者为隐式的，
		每个链上的对象都有一致的处理请求和访问链上后继者的接口(即如下实例中,在自己方法中再调用一次相同的方法)。
	 * 
	 */
	public static void main(String[] args) {
		// 这个boy没有车，也没有房，不过很有责任心
		Boy boy = new Boy(false, false, true);
		// 也可以使用setHanlder方法
		Handler handler = new CarHandler(new HouseHandler(
				new ResponsibilityHandler(null)));
		handler.handleRequest(boy);
	}
}