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

import static util.Functions.swap;

/**
 * Provides algorithms to partition a data set.
 */
public class Partition {
	/**
	 * Number of operations executed in the last 'partition'.
	 */
	public static long partitionOperations = 0;

	/**
	 * Given a pivot, partitions data in less than the pivot and greater
	 * than the pivot. The data type used must be able to compare against
	 * itself. If there's a value that is equal to the given pivot, if the
	 * whole data is sorted, that value would already be on its final
	 * position.
	 * Returns the index where the list was partitioned.
	 * @param values The data set.
	 * @param pivot The value used as comparator.
	 * @return The index where the list was partitioned.
	 */
	public static <T extends Comparable<T>>
	int partition(T[] values, T pivot)
	{
		return partition(values, pivot, FunctionObjects.less());
	}
	
	/**
	 * Given a pivot, partitions data with respect to the comparator given.
	 * If there's a value that is equal to the given pivot, if the
	 * whole data is sorted, using the same comparator, that value would
	 * already be on its final position.
	 * Returns the index where the list was partitioned.
	 * @param values The data set.
	 * @param pivot The value used as comparator.
	 * @param cmp The comparator to be used.
	 * @return The index where the list was partitioned.
	 */
	public static <T> int partition(T[] values, T pivot, Comparator<T> cmp) {
		return partition(values, 0, values.length-1, pivot, cmp);
	}

	/**
	 * Given a pivot, partitions a portion of the given data, starting at
	 * index 's' end ending at index 'e' (both included), with respect to
	 * the comparator given.
	 * If there's a value that is equal to the given pivot, if the
	 * whole data is sorted, using the same comparator, that value would
	 * already be on its final position.
	 * Returns the index where the list was partitioned.
	 * @param values The data set.
	 * @param s The initial index of the data.
	 * @param e The final index of the data.
	 * @param pivot The value used as comparator.
	 * @param cmp The comparator to be used.
	 * @return The index where the list was partitioned.
	 */
	public static <T>
	int partition(T[] values, int s, int e, T pivot, Comparator<T> cmp)
	{
		partitionOperations = 0;
		int i = s-1, j = e+1;
		while (true) {
			int cmp_i = 0, cmp_j = 0;
			do {
				i++;
				partitionOperations++;
				cmp_i = compare(cmp, values[i], pivot);
			} while (i < e && cmp_i < 0);
			do {
				j--;
				partitionOperations++;
				cmp_j = compare(cmp, values[j], pivot);
			} while (j > s && cmp_j > 0);
			if (i >= j) return j;
			partitionOperations++;
			swap(values, i, j);
		}
	}
	private static <T> int compare(Comparator<T> cmp, T a , T b) {
		partitionOperations++;
		return cmp.compare(a, b);
	}

	/**
	 * Partially sort the given data, so that the n<sup>th</sup> element is
	 * in its final position, if the whole data was sorted.
	 * @param values The data set.
	 * @param n The position of the element.
	 */
	public static <T extends Comparable<T>>
	void nth_element(T[] values, int n)
	{
		nth_element(values, n, FunctionObjects.less());
	}

	/**
	 * Partially sort the given data, so that the n<sup>th</sup> element is
	 * in its final position, if the whole data was sorted, with the given
	 * comparator.
	 * @param values The data set.
	 * @param n The position of the element.
	 * @param cmp The comparator to be used.
	 */
	public static <T> void nth_element(T[] values, int n, Comparator<T> cmp) {
		nth_element(values, 0, values.length-1, n, cmp);
	}

	
	/**
	 * Partially sort the given data, so that the n<sup>th</sup> element is
	 * in its final position, if the whole data was sorted, with the given
	 * comparator.
	 * @param values The data set.
	 * @param s The index of the first value.
	 * @param e The index of the first value.
	 * @param n The position of the element.
	 */
	public static <T extends Comparable<T>>
	void nth_element(T[] values, int s, int e, int n) {
		nth_element(values, s, e, n, FunctionObjects.less());
	}
	
	/**
	 * Partially sort the given data, so that the n<sup>th</sup> element is
	 * in its final position, if the whole data was sorted, with the given
	 * comparator.
	 * @param values The data set.
	 * @param s The index of the first value.
	 * @param e The index of the first value.
	 * @param n The position of the element.
	 * @param cmp The comparator to be used.
	 */
	public static <T>
	void nth_element(T[] values, int s, int e, int n, Comparator<T> cmp) {
		T pivot;
		int min = s;
		int max = e;
		while (min != max) {
			int med = (min+max)/2;
			pivot=median_of_three(values[min],values[med],values[max],cmp);
			int index = partition(values, min, max, pivot, cmp);
			if (index == n)
				return;
			if (index > n)
				max = index-1;
			else
				min = index+1;
		}
	}

	/**
	 * Computes the median value of three values.
	 * @param v1 The first  value.
	 * @param v2 The second value.
	 * @param v3 The third value.
	 * @return The value which is the median of the values given.
	 */
	public static <T extends Comparable<T>>
	T median_of_three(T v1, T v2, T v3) {
		return median_of_three(v1, v2, v3, FunctionObjects.less());
	}
	
	/**
	 * Computes the median value of three values.
	 * @param v1 The first  value.
	 * @param v2 The second value.
	 * @param v3 The third value.
	 * @param cmp The comparator to use.
	 * @return The value which is the median of the values given.
	 */
	public static <T> T median_of_three(T v1, T v2, T v3, Comparator<T> cmp) {
		int a = cmp.compare(v1, v2);
		int b = cmp.compare(v1, v3);
		int c = cmp.compare(v2, v3);
		if ((a >= 0 && b <= 0) || (a <= 0 && b >=0)) return v1;
		if ((a <= 0 && c <= 0) || (a >= 0 && c >=0)) return v2;
		return v3;
	}

}
