package com.carvendy.design.pattern.bridge.more;

import com.carvendy.design.pattern.bridge.AbstractRoad;
import com.carvendy.design.pattern.bridge.Car;
import com.carvendy.design.pattern.bridge.SpeedWay;
import com.carvendy.design.pattern.bridge.Street;

public class Test {

	/*
	 * 类似矩阵
	 * 
	 *     例子： 2（人）*3（车）*2（道路）
	 *     走不同的桥，就会变成不同行走方式
	 */
	public static void main(String[] args) {  
		  
	    //AbstractRoad speedWay = new SpeedWay();  
	    AbstractRoad speedWay = new Street(); 
	    speedWay.aCar = new Car();  
	      
	    People man = new Woman();  
	    man.road = speedWay;  
	    man.run();  
	}  
}
