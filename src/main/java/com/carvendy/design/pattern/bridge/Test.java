package com.carvendy.design.pattern.bridge;

public class Test {

	public static void main(String[] args){  
	      
	    AbstractRoad speedWay = new SpeedWay();  
	    speedWay.aCar = new Car();  
	    speedWay.run();  
	      
	    AbstractRoad street = new Street();  
	    street.aCar = new Bus();  
	    street.run();  
	}  
}
