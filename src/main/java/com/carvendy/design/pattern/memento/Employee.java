package com.carvendy.design.pattern.memento;

public class Employee{
    private String name;
    private int age;
    public Employee(String aName,int aAge){
       name = aName;
       age = aAge;
    }
    public void setName(String aName){
       name = aName;
    }
    public void setAge(int aAge){
       age = aAge;
    }
    public Memento  saveMemento(){
       return new Memento(name,age);
    }
    public void restoreMemento(Memento memento){
       age = memento.age;
       name = memento.name;
    }
    public int getAge(){
       return age;
    }
    public String getName(){
       return name;
    }
}
