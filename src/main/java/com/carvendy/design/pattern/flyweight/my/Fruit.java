package com.carvendy.design.pattern.flyweight.my;

import java.util.HashMap;
import java.util.Map;

public class Fruit {

	private static Map<String,Fruit> fruitMap=new HashMap<String, Fruit>();
	
	public static Fruit getFruit(String name){
		Fruit fruit=null;
		
		if("apple".equals(name)){
			if(fruitMap.get(name)!=null){
				fruit = fruitMap.get(name);
			}else{ 
				fruit = new Apple();
				fruitMap.put(name, fruit);
			}
		}
		
		if("banana".equals(name)){
			if(fruitMap.get(name)!=null){
				fruit = fruitMap.get(name);
			}else{ 
				fruit = new Banana();
				fruitMap.put(name, fruit);
			}
		}
		
		if("orange".equals(name)){
			if(fruitMap.get(name)!=null){
				fruit = fruitMap.get(name);
			}else{ 
				fruit = new Orange();
				fruitMap.put(name, fruit);
			}
		}
		
		return fruit;
	}
}
