package com.briup.servlet;

public class Insert{
	public static void insertSort(int[] array){
		int n =array.length;
		for (int i=1; i<n;i++){
			for (int j=i-1;j>=0;j--){
				if (array[i]<array[j]){
					array[i]+=array[j];
					array[j]=array[i]-array[j];
					array[i]-=array[j];
					i=j;
				}
				else{
					break;
				}
			}
		}
		printArray(array);
	}
	public static void printArray(int[] array){
		for (int i=0;i<array.length;i++){
			System.out.print(array[i]+" ");
		}
	}

	public static void main(String[] args){
		int[] array = {2, 5, -5, 98, 198, 43};
		insertSort(array);
	}
}
