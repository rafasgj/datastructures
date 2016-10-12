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

import algorithms.Sorting;

public class TestSorting {
	
	interface Algorithm<T> {
		long execute(T[] array);
	}
	
	static class Sort<T> {
		final String name;
		final Algorithm<T> algorithm;
		Sort(String name, Algorithm<T> algorithm) {
			this.name = name;
			this.algorithm = algorithm;
		}
	}
	
	private static Integer[] ordered = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
	private static Integer[] reversed = {16,15,14,13,12,11,10,9,8,7,6,5,4,3,2,1};
	private static Integer[] random = {14,14,8,7,2,3,16,15,10,1,9,12,6,13,4,5,11,17,17,14};
	
	@SuppressWarnings("unchecked")
	public static void main(String...args) {
		@SuppressWarnings("rawtypes")
		Sort[] simple = {
				new Sort<Integer>("Insertion",
						(n)-> Sorting.insertionSort((Integer[])n)),
				new Sort<Integer>("Bubble",
						(n)-> Sorting.bubbleSort((Integer[])n)),
				new Sort<Integer>("Selection",
						(n)-> Sorting.selectionSort((Integer[])n)),
		};
		
		@SuppressWarnings("rawtypes")
		Sort[] fast = {
				new Sort<Integer>("Quick",
						(n)-> Sorting.quickSort((Integer[])n)),
				new Sort<Integer>("Quick Iterative",
						(n)-> Sorting.qsort((Integer[])n)),
				new Sort<Integer>("Heap",
						(n)-> Sorting.heapSort((Integer[])n)),
				new Sort<Integer>("Intro",
						(n)-> Sorting.introSort((Integer[])n)),
				new Sort<Integer>("Merge",
						(n)-> Sorting.mergeSort((Integer[])n)),
				new Sort<Integer>("Merge Iteractive",
						(n)-> Sorting.msort((Integer[])n)),
		};

		// Small arrays to test correctness.
		for (Sort<Integer> p : simple)
			testAlgorithm(p.name, p.algorithm);
		for (Sort<Integer> p : fast)
			testAlgorithm(p.name, p.algorithm);

		// Large arrays to test speed.
		random = startLargeTest("Large", 64);
		for (Sort<Integer> p : simple)
			testAlgorithmWithArray(
					p.name,
					random.clone(),
					p.algorithm,
					false
			);
		for (Sort<Integer> p : fast)
			testAlgorithmWithArray(
					p.name,
					random.clone(),
					p.algorithm,
					false
			);
		
		// Really Large arrays to test usability of algorithms.
		random = startLargeTest("Huge", 8*1024);
		for (Sort<Integer> p : fast)
			testAlgorithmWithArray(
					p.name,
					random.clone(),
					p.algorithm,
					false
			);
	}

	private static Integer[] startLargeTest(String name, int kElements) {
		String unity = kElements > 1024 ? "M" : "K";
		int value = kElements > 1024 ? (kElements/1024) : kElements;
		System.out.println();
		System.out.println(new String(new char[20]).replace("\0", "="));
		System.out.println(name + ", "+value+" "+unity+" elements, random array sort.");		
		System.out.print("Creating array: ");System.out.flush();
		long s, e;
		s = System.currentTimeMillis();
		Integer[] data = new Integer[kElements*1024]; 
		for (int i = 0; i < kElements*1024; i++)
			data[i] = (int)(Math.random()*(kElements*1024));
		e = System.currentTimeMillis();
		System.out.println(((e-s)/1000.0) + "s");
		return data;
	}

	private static void testAlgorithm(String name, Algorithm<Integer> algorithm) {
		System.out.println();
		System.out.println(new String(new char[20]).replace("\0", "="));
		System.out.println(name + " Sort");
		testAlgorithmWithArray("ordered", ordered, algorithm, true);
		testAlgorithmWithArray("reversed", reversed, algorithm, true);
		testAlgorithmWithArray("random", random, algorithm, true);
		System.out.println(new String(new char[20]).replace("\0", "="));
	}

	private static <T extends Comparable<T>>
	void testAlgorithmWithArray(String style, T[] array, Algorithm <T> algorithm, boolean print) {
		long s, e;
		System.out.println(new String(new char[20]).replace("\0", "-"));
		System.out.println("Sorting " + style + ": ");
		s = System.currentTimeMillis();
		T[] clone = array.clone();
		e = System.currentTimeMillis();
		System.out.println("Cloning Time: " + ((e-s)/1000.0) + "s");
		s = System.currentTimeMillis();
		System.out.println("Operations: " + algorithm.execute(clone));
		e = System.currentTimeMillis();
		System.out.println("Execution Time: " + ((e-s)/1000.0) + "s");
		if (!validateArray(clone))
			System.err.println("There was an error sorting this array.");
		if (print)
			printArray(clone);
	}

	private static <T extends Comparable<T>> boolean validateArray(T[] array) {
		boolean ok = true;
		Comparator<T> cmp = (a,b) -> a.compareTo(b);
		T last = array[0];
		for (int i = 1; i < array.length; i++) {
			if (cmp.compare(last, array[i]) > 0) {
				System.out.println(last + " - " + array[i]);
				ok = false;
			}
		}
		return ok;
	}

	private static <T> void printArray(T[] array) {
		for (T i : array)
			System.out.print(i + " ");
		System.out.println();
	}
}
