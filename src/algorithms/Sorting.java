/*
 * Data Structures and Algorithms.
 * Copyright (C) 2016 Rafael Guterres Jeffman
 *
 * See the LICENSE file accompanying this source code, for
 * licensing restrictions that might apply.
 *
 */

package algorithms;

import java.util.Comparator;

import util.BinaryHeap;
import util.FunctionObjects;

public class Sorting {

	/**
	 * Sort the given data using insertion sort.
	 * @param array The array to be sorted.
	 * @return The number of operations (comparisons and swaps) performed.
	 */
	public static <T extends Comparable<T>>
	long insertionSort(T[] array)
	{
		return insertionSort(array, FunctionObjects.less());
	}

	/**
	 * Sort the given data using insertion sort and the given comparator.
	 * @param array The array to be sorted.
	 * @param cmp The comparator to use.
	 * @return The number of operations (comparisons and swaps) performed.
	 */
	public static <T>
	long insertionSort(T[] array, Comparator<T> cmp)
	{
		int i,j,ops = 0;
		for (i = 0; i < array.length-1; i++) {
			for (j = i+1; j > 0; j--) {
				ops++;
				if (cmp.compare(array[j],array[j-1]) < 0) {
					ops++;
					T t = array[j]; array[j] = array[j-1]; array[j-1] = t;
				} else break;
			}
		}
		return ops;
	}

	/**
	 * Sort the given data using selection sort.
	 * @param array The array to be sorted.
	 * @return The number of operations (comparisons and swaps) performed.
	 */
	public static <T extends Comparable<T>>
	long selectionSort(T[] array)
	{
		return selectionSort(array, FunctionObjects.less());
	}

	/**
	 * Sort the given data using selection sort and the given comparator.
	 * @param array The array to be sorted.
	 * @param cmp The comparator to use.
	 * @return The number of operations (comparisons and swaps) performed.
	 */
	public static <T>
	long selectionSort(T[] array, Comparator<T> cmp)
	{
		int i,j,ops = 0;
		for (i = 0; i < array.length-1; i++) {
			T min = array[i];
			int n = i;
			for (j = i+1; j < array.length; j++) {
				ops++;
				if (cmp.compare(array[j],min) < 0) {
					min = array[j];
					n = j;
				}
			}
			ops++;
			T t = array[i]; array[i] = array[n]; array[n] = t;
		}
		return ops;
	}

	/**
	 * Sort the given data using bubble sort.
	 * @param array The array to be sorted.
	 * @return The number of operations (comparisons and swaps) performed.
	 */
	public static <T extends Comparable<T>>
	long bubbleSort(T[] array)
	{
		return bubbleSort(array, FunctionObjects.less());
	}

	/**
	 * Sort the given data using bubble sort and the given comparator.
	 * @param array The array to be sorted.
	 * @param cmp The comparator to use.
	 * @return The number of operations (comparisons and swaps) performed.
	 */
	public static <T>
	long bubbleSort(T[] array, Comparator<T> cmp)
	{
		int i,j,ops = 0;
		boolean swap = false;
		for (i = 0; i < array.length-1; i++) {
			for (j = i+1; j < array.length; j++) {
				ops++;
				if (cmp.compare(array[i],array[j]) > 0) {
					ops++;
					T t = array[i]; array[i] = array[j]; array[j] = t;
					swap = true;
				}
			}
			if (!swap) break;
		}
		return ops;
	}

	/**
	 * Sort the given data using quick sort.
	 * @param array The array to be sorted.
	 * @return The number of operations (comparisons and swaps) performed.
	 */
	public static <T extends Comparable<T>>
	long quickSort(T[] array)
	{
		return quickSort(array, FunctionObjects.less());
	}

	/**
	 * Sort the given data using quick sort and the given comparator.
	 * @param array The array to be sorted.
	 * @param cmp The comparator to use.
	 * @return The number of operations (comparisons and swaps) performed.
	 */
	public static <T>
	long quickSort(T[] array, Comparator<T> cmp)
	{
		return do_quick_sort(array, 0, array.length-1, cmp);
	}

	private static <T>
	long do_quick_sort(T[] array, int s, int e, Comparator<T> cmp)
	{
		long ops = 0;
		int med = (s + e)/2;
		T pivot = Partition.median_of_three(array[s],array[med],array[e],cmp);
		int n = Partition.partition(array, s, e, pivot, cmp);
		ops += Partition.partitionOperations;
		if (n > s+1)
			ops += do_quick_sort(array, s, n-1, cmp);
		if (n < e-1)
			ops += do_quick_sort(array, n+1, e, cmp);
		return ops;
	}
	

	/**
	 * Sort the given data using heap sort.
	 * @param array The array to be sorted.
	 * @return The number of operations (comparisons and swaps) performed.
	 */
	public static <T extends Comparable<T>>
	long heapSort(T[] array)
	{
		return heapSort(array, FunctionObjects.less());
	}

	/**
	 * Sort the given data using heap sort and the given comparator. The
	 * comparator 
	 * @param array The array to be sorted.
	 * @param cmp The comparator to use.
	 * @return The number of operations (comparisons and swaps) performed.
	 */
	public static <T>
	long heapSort(T[] array, Comparator<T> cmp) {
		cmp = cmp.reversed(); // for a max heap, reverse the comparator.
		BinaryHeap<T> heap = BinaryHeap.heapify(array, cmp);
		long ops = heap.heapifyOperations;
		while (!heap.isEmpty()) {
			array[heap.size()-1] = heap.pop();
			ops += heap.popOperations;
		}
		return ops;
	}

	/**
	 * Sort the given data using heap sort.
	 * @param array The array to be sorted.
	 * @return The number of operations (comparisons and swaps) performed.
	 */
	public static <T extends Comparable<T>>
	long mergeSort(T[] array)
	{
		return mergeSort(array, FunctionObjects.less());
	}

	/**
	 * Sort the given data using heap sort and the given comparator. The
	 * comparator 
	 * @param array The array to be sorted.
	 * @param cmp The comparator to use.
	 * @return The number of operations (comparisons and swaps) performed.
	 */
	public static <T>
	long mergeSort(T[] array, Comparator<T> cmp)
	{
		long ops = 0;
		int length = array.length;
		@SuppressWarnings("unchecked")
		T[] temp = (T[]) new Object[length];
		for (int sz = 1; sz < length; sz *= 2) {
			for (int i = 0; i < length; i += 2*sz) {
				int iLeft = i;
				int iRight = Math.min(i + sz, array.length);
				int lim = iRight;
				int iEnd = Math.min(i + 2*sz, array.length);
				for (int j = iLeft; j < iEnd; j++) {
					ops++;
					if (iLeft < lim && (iRight >= iEnd ||
						cmp.compare(array[iLeft],array[iRight])<0))
					{
						temp[j] = array[iLeft++];
					} else {
						temp[j] = array[iRight++];
					}
				}
			}
			ops += length;
			System.arraycopy(temp, 0, array, 0, length);
		}
		return ops;
	}
	/**
	 * Sort the given data using introsort.
	 * @param array The array to be sorted.
	 * @return The number of operations (comparisons and swaps) performed.
	 */
	public static <T extends Comparable<T>>
	long introSort(T[] array)
	{
		return introSort(array, FunctionObjects.less());
	}

	/**
	 * Sort the given data using heap sort and the given comparator. The
	 * comparator 
	 * @param array The array to be sorted.
	 * @param cmp The comparator to use.
	 * @return The number of operations (comparisons and swaps) performed.
	 */
	public static <T>
	long introSort(T[] array, Comparator<T> cmp)
	{
		int depth = (int)(2 * Math.log(array.length)/Math.log(2));
		return do_introsort(array, 0, array.length-1, depth, cmp);
	}

	private static <T>
	long do_introsort(T[] array, int s, int e, int depth, Comparator<T> cmp)
	{
		long ops = 0;
		int sz = 1+e-s;
		if (sz <= 1) return 0;
		if (depth == 0) {
			@SuppressWarnings("unchecked")
			T[] temp = (T[])new Object[sz];
			System.arraycopy(array, s, temp, 0, sz);
			ops = heapSort(temp, cmp);
			System.arraycopy(temp, 0, array, s, sz);
			return ops;
		} else {
			int med = (s + e)/2;
			T pivot=Partition.median_of_three(array[s],array[med],array[e],cmp);
			int n = Partition.partition(array, s, e, pivot, cmp);
			ops = Partition.partitionOperations;
			ops += do_introsort(array, s, n-1, depth-1, cmp);
			ops += do_introsort(array, n+1, e, depth-1, cmp);
			return ops;
		}
	}
}
