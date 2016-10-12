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
import util.Functions;
import util.Stack;

public class Sorting {

	/**
	 * Sort the given data using insertion sort.
	 * @param array The array to be sorted.
	 * @return The number of operations (comparisons and swaps)
	 * performed.
	 */
	public static <T extends Comparable<T>>
	long insertionSort(T[] array)
	{
		return insertionSort(array, FunctionObjects.less());
	}

	/**
	 * Sort the given data using insertion sort and the given
	 * comparator.
	 * @param array The array to be sorted.
	 * @param cmp The comparator to use.
	 * @return The number of operations (comparisons and swaps)
	 * performed.
	 */
	public static <T>
	long insertionSort(T[] array, Comparator<T> cmp)
	{
		long ops = 0;
		int i,j = 0;
		for (i = 0; i < array.length-1; i++) {
			for (j = i+1; j > 0; j--) {
				ops++;
				if (cmp.compare(array[j],array[j-1]) < 0) {
					ops++;
					Functions.swap(array, j, j-1);
				} else break;
			}
		}
		return ops;
	}

	/**
	 * Sort the given data using selection sort.
	 * @param array The array to be sorted.
	 * @return The number of operations (comparisons and swaps)
	 * performed.
	 */
	public static <T extends Comparable<T>>
	long selectionSort(T[] array)
	{
		return selectionSort(array, FunctionObjects.less());
	}

	/**
	 * Sort the given data using selection sort and the given
	 * comparator.
	 * @param array The array to be sorted.
	 * @param cmp The comparator to use.
	 * @return The number of operations (comparisons and swaps)
	 * performed.
	 */
	public static <T>
	long selectionSort(T[] array, Comparator<T> cmp)
	{
		long ops = 0;
		int i,j;
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
			Functions.swap(array, i, n);
		}
		return ops;
	}

	/**
	 * Sort the given data using bubble sort.
	 * @param array The array to be sorted.
	 * @return The number of operations (comparisons and swaps)
	 * performed.
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
	 * @return The number of operations (comparisons and swaps)
	 * performed.
	 */
	public static <T>
	long bubbleSort(T[] array, Comparator<T> cmp)
	{
		long ops = 0;
		int i,j = 0;
		boolean swap = false;
		for (i = 0; i < array.length-1; i++) {
			for (j = i+1; j < array.length; j++) {
				ops++;
				if (cmp.compare(array[i],array[j]) > 0) {
					ops++;
					Functions.swap(array, i, j);
					swap = true;
				}
			}
			if (!swap) break;
		}
		return ops;
	}

	/**
	 * Sort the given data using an iterative version of quick sort.
	 * @param array The array to be sorted.
	 * @return The number of operations (comparisons and swaps)
	 * performed.
	 */
	public static <T extends Comparable<T>>
	long qsort(T[] array)
	{
		return qsort(array, FunctionObjects.less());
	}

	/**
	 * Sort the given data using an iterative quick sort and the given
	 * comparator.
	 * @param array The array to be sorted.
	 * @param cmp The comparator to use.
	 * @return The number of operations (comparisons and swaps)
	 * performed.
	 */
	public static <T>
	long qsort(T[] array, Comparator<T> cmp)
	{
		class Pair {
			int s, e;
			public Pair(int s, int e) {
				this.s = s;
				this.e = e;
			}
		}
		long ops = 0;
		Stack<Pair> callStack = new Stack<>();
		callStack.push(new Pair(0,array.length-1));
		while (!callStack.isEmpty()) {
			Pair p = callStack.pop();
			int s = p.s, e = p.e;
			if (s >= e) continue;
			int med = (s + e)/2;
			T pivot = Partition.median_of_three(array[s],array[med],array[e],cmp);
			int n = Partition.partition(array, s, e, pivot, cmp);
			ops += Partition.partitionOperations;
			int a = n - s;
			int b = e - n;
			if (a < b) {
				callStack.push(new Pair(s,n));
				callStack.push(new Pair(n+1,e));
			} else {
				callStack.push(new Pair(n+1,e));
				callStack.push(new Pair(s,n));
			}
		}
		return ops;
	}

	
	/**
	 * Sort the given data using quick sort.
	 * @param array The array to be sorted.
	 * @return The number of operations (comparisons and swaps)
	 * performed.
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
	 * @return The number of operations (comparisons and swaps)
	 * performed.
	 */
	public static <T>
	long quickSort(T[] array, Comparator<T> cmp)
	{
		return do_quick_sort(array, 0, array.length-1, cmp);
	}

	private static <T>
	long do_quick_sort(T[] array, int s, int e, Comparator<T> cmp)
	{
		if (s >= e) return 0;
		long ops = 0;
		int med = (s + e)/2;
		T pivot = Partition.median_of_three(array[s],array[med],array[e],cmp);
		int n = Partition.partition(array, s, e, pivot, cmp);
		ops += Partition.partitionOperations;
		int a = n - s;
		int b = e - n;
		if (a < b) {
			ops += do_quick_sort(array, s, n, cmp);
			ops += do_quick_sort(array, n+1, e, cmp);
		} else {
			ops += do_quick_sort(array, n+1, e, cmp);
			ops += do_quick_sort(array, s, n, cmp);
		}
		return ops;
	}
	

	/**
	 * Sort the given data using heap sort.
	 * @param array The array to be sorted.
	 * @return The number of operations (comparisons and swaps)
	 * performed.
	 */
	public static <T extends Comparable<T>>
	long heapSort(T[] array)
	{
		return heapSort(array, FunctionObjects.less());
	}

	/**
	 * Sort the given data using heap sort and the given comparator.
	 * @param array The array to be sorted.
	 * @param cmp The comparator to use.
	 * @return The number of operations (comparisons and swaps)
	 * performed.
	 */
	public static <T>
	long heapSort(T[] array, Comparator<T> cmp) {
		cmp = cmp.reversed(); //for a max heap, reverse the comparator.
		BinaryHeap<T> heap = BinaryHeap.heapify(array, cmp);
		heap.heapifyOperations = 0;
		while (!heap.isEmpty()) {
			array[heap.size()-1] = heap.pop();
		}
		return heap.heapifyOperations;
	}

	/**
	 * Sort the given data using an inplace merge sort.
	 * @param array The array to be sorted.
	 * @return The number of operations (comparisons and swaps)
	 * performed.
	 */
	public static <T extends Comparable<T>>
	long inplaceMergeSort(T[] array)
	{
		return mergeSort(array, FunctionObjects.less());
	}
	/**
	 * Sort the given data using a recursive merge sort and the
	 * given comparator. 
	 * @param array The array to be sorted.
	 * @param cmp The comparator to use.
	 * @return The number of operations (comparisons and swaps)
	 * performed.
	 */
	public static <T>
	long inplaceMergeSort(T[] array, Comparator<T> cmp)
	{
		return do_SplitMerge(array, 0, array.length-1, cmp);
	}

	private static <T>
	long do_SplitMerge(T[] data, int start, int end, Comparator<T> cmp) {
		if (end - start < 1) {
			return 0;
		}
		int m = start + ((end - start)/2);
		long ops = do_SplitMerge(data, start, m, cmp);
		ops += do_SplitMerge(data, m+1, end, cmp);
		ops += do_inplaceMerge(data, start, m, end, cmp);
		return ops;
	}

	private static <T>
	long do_inplaceMerge(T[] data, int start, int m, int end, Comparator<T> cmp) {
		int ops = 0;
		for (int i = m+1; i <= end; i++) {
			for (int j = i; j >= start; j--) {
				ops++;
				if (cmp.compare(data[j],data[j-1]) < 0) {
					ops++;
					Functions.swap(data, j, j-1);
				} else {
					break;
				}
			}
		}
		return ops;
	}

	
	/**
	 * Sort the given data using a recursive merge sort.
	 * @param array The array to be sorted.
	 * @return The number of operations (comparisons and swaps)
	 * performed.
	 */
	public static <T extends Comparable<T>>
	long mergeSort(T[] array)
	{
		return mergeSort(array, FunctionObjects.less());
	}
	/**
	 * Sort the given data using a recursive merge sort and the
	 * given comparator. 
	 * @param array The array to be sorted.
	 * @param cmp The comparator to use.
	 * @return The number of operations (comparisons and swaps)
	 * performed.
	 */
	public static <T>
	long mergeSort(T[] array, Comparator<T> cmp)
	{
		return array.length + do_SplitMerge(array.clone(), array, 0, array.length-1, cmp);
	}
	
	
	private static <T>
	long do_SplitMerge(T[] b, T[] a, int start, int end, Comparator<T> cmp) {
		if (end - start < 1) {
			return 0;
		}
		int m = start + ((end - start)/2);
		long ops = do_SplitMerge(a, b, start, m, cmp);
		ops += do_SplitMerge(a, b, m+1, end, cmp);
		ops += do_merge(b, a, start, m, end, cmp);
		return ops;
	}

	private static <T>
	long do_merge(T[] a, T[] b, int start, int m, int end, Comparator<T> cmp) {
		int ops = 0;
		int i = start, j = m+1;
		for (int k = start; k <= end; k++) {
			ops += 2;
			if (i <= m && (j > end || cmp.compare(a[i],a[j]) <= 0)) {
				b[k] = a[i];
				i++;
			} else {
				b[k] = a[j];
				j++;
			}
		}
		return ops;
	}

	/**
	 * Sort the given data using iterative merge sort.
	 * @param array The array to be sorted.
	 * @return The number of operations (comparisons and swaps)
	 * performed.
	 */
	public static <T extends Comparable<T>>
	long msort(T[] array)
	{
		return msort(array, FunctionObjects.less());
	}

	/**
	 * Sort the given data using iterative merge sort and the
	 * given comparator. 
	 * @param array The array to be sorted.
	 * @param cmp The comparator to use.
	 * @return The number of operations (comparisons and swaps)
	 * performed.
	 */
	public static <T>
	long msort(T[] array, Comparator<T> cmp)
	{
		T[] b = array;
		int length = array.length;
		long ops = length;
		T[] temp = array.clone();
		boolean changed = false;
		for (int sz = 1; sz < length; sz *= 2) {
			for (int i = 0; i < length; i += 2*sz) {
				int iLeft = i;
				int iRight = Math.min(i + sz, b.length);
				int lim = iRight;
				int iEnd = Math.min(i + 2*sz, b.length);
				for (int j = iLeft; j < iEnd; j++) {
					ops += 2;
					if (iLeft < lim && (iRight >= iEnd ||
						cmp.compare(b[iLeft],b[iRight]) < 0))
					{
						temp[j] = b[iLeft++];
					} else {
						temp[j] = b[iRight++];
					}
				}
			}
			T[] x = temp;
			temp = b;
			b = x;
			changed = !changed;
		}
		
		ops += length;
		for (int i = 0; i < length; i++)
			array[i] = b[i];
		return ops;
	}
	/**
	 * Sort the given data using introsort.
	 * @param array The array to be sorted.
	 * @return The number of operations (comparisons and swaps)
	 * performed.
	 */
	public static <T extends Comparable<T>>
	long introSort(T[] array)
	{
		return introSort(array, FunctionObjects.less());
	}

	/**
	 * Sort the given data using heap sort and the given comparator.
	 * @param array The array to be sorted.
	 * @param cmp The comparator to use.
	 * @return The number of operations (comparisons and swaps)
	 * performed.
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
