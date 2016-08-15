package com.carvendy.design.pattern.memento;

import java.util.Vector;

public class CareTaker{
    private Vector v;
    private int  current;
    public CareTaker(){
       current = -1;
       v = new Vector();
    }
    public void setMemento(Memento mem){
       current ++;
       v.add(mem);
    }
    public Memento getMemento(){
       if(current>0){
           current --;
           return(Memento) v.get(current);
       }
       return null;
    }
}
