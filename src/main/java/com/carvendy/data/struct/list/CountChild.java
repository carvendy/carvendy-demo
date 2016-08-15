package com.carvendy.data.struct.list;

import java.util.ArrayList;
import java.util.LinkedList;

public class CountChild {

	public static void main(String[] args) {
		
		//ArrayList
		//LinkedList<Integer> list=new LinkedList<Integer>();
		
		countByTwoWayList();
	}
	
	
	/*
	 * 
	 */
	public static void countByTwoWayList(){
		Child first = initTwoWayList();
		Child temp;
		
		int count=0;
		int num=500;
		Child currentChild=first;
		currentChild=first;
		
		while(true){
			count++;
			
			if(count==3){
				//移除
				temp=currentChild.pre;
				// 1指向3
				temp.next=currentChild.next;
				// 3指向1
				currentChild.next.pre=temp;
				count=0;
				num--;
			}
			
			if(num==1){break;}
			
			currentChild=currentChild.next;
		}
		
		System.out.println(currentChild.data);
	}
	
	
	private static Child initTwoWayList(){
		
		Child first=new Child();
		Child last=new Child();
		first.data=1;
		first.pre=last;
		
		Child temp=new Child();
		
		temp=first;
		for(int i=2;i<500;i++){
			Child newChild = new Child();
			newChild.data=i;
			newChild.pre=temp;
			temp.next=newChild;
			
			temp=newChild;
		}
		
		temp.next=last;
		last.pre=temp;
		last.next=first;
		last.data=500;
		
		return first;
	}
	
	
	
	/*public static Child initList(){
		Child listHead=new Child();
		Child lastChild=new Child();
		
		lastChild=listHead;
		
		for(int i=1;i<=500;i++){
			Child newChild=new Child();
			newChild.data=i;
			newChild.next=null;
			
			lastChild.next=newChild;
			lastChild=newChild;
		}
		
		return listHead;
	}
	
	
	public static void showList(Child listHead){
		Child pChild=listHead.next;
		
		while(pChild!=null){
			System.out.println(pChild.data);
			pChild=pChild.next;
		}
	}*/
	
}
