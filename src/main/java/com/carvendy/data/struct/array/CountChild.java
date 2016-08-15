package com.carvendy.data.struct.array;

public class CountChild {

	public static void main(String[] args) {
		
		int[] childArray=new int[500];
		int childLeavel=500;
		int index=-1;
		
		int count=0;
		
		while(childLeavel!=1){
			if(index<500-1){
				index++;
			}else{
				index=0;
			}
			
			if(childArray[index]!=0){
				continue;
			}
			
			count++;
			if(count==3){
				childArray[index]=1;
				childLeavel--;
				count=0;
			}
						
		}
		
		System.out.println(index+1);
		
	}
	
}
