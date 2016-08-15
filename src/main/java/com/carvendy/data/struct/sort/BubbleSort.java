package com.carvendy.data.struct.sort;

public class BubbleSort {

	public static void main(String[] args) {
		
		int[] numArr={2,5,11,4,7};
		
		
		for(int i=0;i<numArr.length-1;i++){
			for(int j=0;j<numArr.length-1-i;j++){
				if(numArr[j]>numArr[j+1]){
					numArr[j]=numArr[j]+numArr[j+1];
					numArr[j+1]=numArr[j]-numArr[j+1];
					numArr[j]=numArr[j]-numArr[j+1];
				}
			}
		}
		
		for(int i=0;i<numArr.length;i++){
			System.out.print(numArr[i]+" ");
		}
	}
}
