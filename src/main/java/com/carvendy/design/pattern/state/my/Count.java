package com.carvendy.design.pattern.state.my;

/*
 * 父类，方便提供统一show方法
 */
public class Count {

	public Number num;
	
	public Count(){
		num = new One();
	}
	
	public void show(){
		num=num.show();
	}
}
