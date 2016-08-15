package com.carvendy.guava;

public class Person {

	//public Person(1, 1, "a", "46546", 1, 20);
	
	private int id;
	private int sex;
	

	private String name;
	private String no;
	private int money;
	private int classNo;
	
	//
	public Person(){}
	public Person(int id,int age,String name,
			String no,int sex,int money){
		this.id=id;
		this.sex=sex;
		this.name=name;
		this.no=no;
		this.money=money;
		this.setClassNo(age);
	}
	
	//------------
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}
	public int getClassNo() {
		return classNo;
	}
	public void setClassNo(int classNo) {
		this.classNo = classNo;
	}

}
