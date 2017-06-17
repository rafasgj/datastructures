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
import util.FunctionObjects;

public class TestHeap {
	public static void main(String...strings) {
		// min heap
		// Comparator<Integer> cmp = FunctionObjects.less();
		// max heap
		Comparator<Integer> cmp = FunctionObjects.greater();
		
		BinaryHeap<Integer> heap = new BinaryHeap<Integer>(cmp);
		
		for (int i = 0; i < 20; i++)
			heap.push(i);
		
		while (!heap.isEmpty())
			System.out.print(heap.pop()+" ");
		System.out.println();
		System.out.println(new String(new char[20]).replace("\0", "-"));
		Integer[] array = {14,8,7,2,3,16,15,10,1,9,12,6,13,4,5};
		heap = BinaryHeap.heapify(array, cmp);
		for (int i : array)
			System.out.println(i);
		while (!heap.isEmpty())
			System.out.print(heap.pop()+" ");
		System.out.println();
	}
}
