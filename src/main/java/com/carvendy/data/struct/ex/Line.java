package com.carvendy.data.struct.ex;


public class Line{
	private int k;
	private int b;
	
	public String getFunction(Point p1,Point p2){
		
		k=(p1.getY()-p2.getY())/(p1.getX()-p2.getX());
		b=p1.getY()-k*p1.getX();
		
		if(b>0){
			return "y="+k+"*x+"+b;
		}else{
			return "y="+k+"*x"+b;
		}
		
	}
	
	public static void main(String[] args) {
		Point p1 = new Point(2, 1);
		Point p2 = new Point(3, 5);
		
		Line line = new Line();
		String function = line.getFunction(p1, p2);
		System.out.println(function);
	}
}


class Point{
	private int x;
	private int y;
	
	public Point(){}
	public Point(int x,int y){
		this.setX(x);
		this.setY(y);
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
}

