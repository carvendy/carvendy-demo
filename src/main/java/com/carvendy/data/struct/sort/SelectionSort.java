package com.carvendy.data.struct.sort;

public class SelectionSort {

	public static void main(String[] args) {
		int[] numArr={2,5,11,4,7};
		
		
		for(int i=0;i<numArr.length-1;i++){
			for(int j=i+1;j<numArr.length;j++){
				if(numArr[i]>numArr[j]){
					numArr[i]=numArr[i]+numArr[j];
					numArr[j]=numArr[i]-numArr[j];
					numArr[i]=numArr[i]-numArr[j];
				}
			}
		}
		
		for(int i=0;i<numArr.length;i++){
			System.out.print(numArr[i]+" ");
		}
	}
}
