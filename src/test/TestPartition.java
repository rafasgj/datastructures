/*
 * Data Structures and Algorithms.
 * Copyright (C) 2016 Rafael Guterres Jeffman
 *
 * See the LICENSE file accompanying this source code, for
 * licensing restrictions that might apply.
 *
 */

package test;

import algorithms.Partition;
import util.Pair;

public class TestPartition {
	
	public static void main(String[] args) {
		Integer[] array = new Integer[10];

		for (int i = 0; i < 10; i++)
			array[i] = 10 - i;
		do_partition(array,5);
		
		for (int i = 0; i < 10; i++)
			array[i] = i;
		do_partition(array,5);

		for (int i = 0; i < 10; i++)
			array[i] = (int)(Math.random()*100);
		do_partition(array, (array[0]+array[4]+array[9])/3);		

		// nth-element
		System.out.print("Nth-Element (n=5): ");
		for (int i = 0; i < 10; i++)
			array[i] = (int)(Math.random()*100);
		for (int i = 0; i < 10; i++)
			System.out.print(array[i]+" ");
		System.out.println();
		Partition.nth_element(array, 5);		
		for (int i = 0; i < 5; i++)
			System.out.print(array[i]+" ");
		System.out.println();
		for (int i = 5; i < 10; i++)
			System.out.print(array[i]+" ");
		System.out.println();
	
	}

	private static void do_partition(Integer[] array, int pivot) {
		System.out.print("Data: ");
		for (int i = 0; i < array.length; i++)
			System.out.print(array[i]+" ");
		System.out.println();
		System.out.println("Pivot: " + pivot);
		Pair<Integer, Integer> p = Partition.partition(array, pivot);
		for (int i = 0; i < p.first; i++)
			System.out.print(array[i]+" ");
		System.out.println();
		for (int i = p.second; i < array.length; i++)
			System.out.print(array[i]+" ");
		System.out.println();		
	}

}
