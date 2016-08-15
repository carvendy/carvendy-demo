package com.carvendy.design.pattern.memento;

public class Client{
	
	/*
	 * (1)
			备忘录模式属于行为型模式，其意图是在不破坏封装性的前提下，捕获一个对象的内部状态，并在该对象之外保存这
			个状态，这样以后就可以将对象恢复到原先保存的状态。
			
	   (2)
		实例如下:	
			有一个对象Employee.除了属性外,还需要一个保存,还原状态的方法.
			有一个对象Memento,用来记录Employee每一个时刻的状态,
			CareTaker,用来保存,拿回Memento.需要一个保存,还原状态的方法.->需要一个指针,一个容器.
	 * 
	 */
	
    public static void show(Employee e){
       System.out.println("-----------------------------------");
       System.out.println("Name:"+e.getName());
       System.out.println("Age:" + e.getAge());
       System.out.println("-----------------------------------");
    }
    
    public static void main(String[] args){
       Employee e = new Employee("lili",25);
       
       CareTaker ct = new CareTaker();
       show(e);
       ct.setMemento(e.saveMemento());//最初的值
       //第一次修改
       e.setName("litianli");
       show(e);
       ct.setMemento(e.saveMemento());
       //第二次修改
       e.setAge(45);
       show(e);
       ct.setMemento(e.saveMemento());
       
       //restore
       e.restoreMemento(ct.getMemento());
       show(e);
       e.restoreMemento(ct.getMemento());
       show(e);
    }
}