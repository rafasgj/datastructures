/*
 * Data Structures and Algorithms.
 * Copyright (C) 2016 Rafael Guterres Jeffman
 *
 * See the LICENSE file accompanying this source code, for
 * licensing restrictions that might apply.
 *
 */

package test;

import java.util.Comparator;

import datastructures.BinaryHeap;

public class TestHeap {
	public static void main(String...strings) {
		Comparator<Integer> heapOrder = new Comparator<Integer>(){
			@Override
			public int compare(Integer o1, Integer o2) {
				//return o1 - o2; // min heap
				return o2 - o1; // max heap
			}
		};
		BinaryHeap<Integer> heap = new BinaryHeap<>(heapOrder);
		
		for (int i = 0; i < 20; i++)
			heap.push(i);
		
		while (!heap.isEmpty())
			System.out.print(heap.pop()+" ");
		System.out.println();
		System.out.println(new String(new char[20]).replace("\0", "-"));
		Integer[] array = {14,8,7,2,3,16,15,10,1,9,12,6,13,4,5};
		heap = BinaryHeap.heapify(array, heapOrder);
		for (int i : array)
			System.out.println(i);
		while (!heap.isEmpty())
			System.out.print(heap.pop()+" ");
		System.out.println();
	}
}
