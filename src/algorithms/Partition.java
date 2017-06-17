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

import util.FunctionObjects;
import util.Pair;

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
	 * <p>Given a pivot, partitions data in less than the pivot and greater
	 * than the pivot. The data type used must be able to compare against
	 * itself. If there's a value that is equal to the given pivot, if the
	 * whole data is sorted, that value would already be on its final
	 * position.</p>
	 * <p>Returns the indexes where the list was partitioned.</p>
	 * @param values The data set.
	 * @param pivot The index of the value used as pivot.
	 * @return The indexes where the list was partitioned, with the values
	 * between both indexes being the same.
	 */
	public static <T extends Comparable<T>>
	Pair<Integer,Integer> partition(T[] values, T pivot)
	{
		return partition(values, pivot, FunctionObjects.less());
	}
	
	/**
	 * <p>Given a pivot, partitions data with respect to the comparator
	 * given. If there's a value that is equal to the given pivot, if
	 * the whole data is sorted, using the same comparator, that value
	 * would already be on its final position.</p>
	 * <p>Returns the indexes where the list was partitioned.</p>
	 * @param values The data set.
	 * @param pivot The index of the value used as pivot.
	 * @param cmp The comparator to be used.
	 * @return The indexes where the list was partitioned, with the values
	 * between both indexes being the same.
	 */
	public static <T>
	Pair<Integer,Integer> partition(T[] values, T pivot, Comparator<T> cmp)
	{
		return partition(values, 0, values.length-1, pivot, cmp);
	}

	/**
	 * <p>Given a pivot, partitions a portion of the given data, starting
	 * at index 's' end ending at index 'e' (both included), with respect
	 * to the comparator given.</p>
	 * <p>If there's a value that is equal to the given pivot, if the
	 * whole data is sorted, using the same comparator, that value would
	 * already be on its final position.</p>
	 * <p>Returns the indexes where the list was partitioned.</p>
	 * <p>Implements Dijkstra's 3-way partitioning.</p>
	 * @param array The data set.
	 * @param s The initial index of the data.
	 * @param e The final index of the data.
	 * @param pivot The index of the value used as comparator.
	 * @param cmp The comparator to be used.
	 * @return The index where the list was partitioned.
	 */
	public static <T>
	Pair<Integer,Integer> partition(T[] array, int s, int e, T pivot, Comparator<T> cmp)
	{
			int i = s;
		    int j = s;
		    int n = e;

		    partitionOperations = 0;
		    while (j <= n) {
			    partitionOperations++;
		    	int tst = cmp.compare(array[j],pivot);
		        if (tst < 0) {
		            if (i != j) {
					    partitionOperations++;
		            	swap(array,i,j);
		            }
		            i++;
		            j++;
		        } else if (tst > 0) {
				    partitionOperations++;
		            swap(array,j,n);
		            n--;
		        } else {
		            j++;
		        }
		    }
		    return new Pair<>(i,n);
	}

	/**
	 * <p>Partially sort the given data, so that the n<sup>th</sup>
	 * element is in its final position, if the whole data was sorted.</p>
	 * @param values The data set.
	 * @param n The position of the element.
	 */
	public static <T extends Comparable<T>>
	void nth_element(T[] values, int n)
	{
		nth_element(values, n, FunctionObjects.less());
	}

	/**
	 * <p>Partially sort the given data, so that the n<sup>th</sup>
	 * element is in its final position, if the whole data was sorted,
	 * with the given comparator.</p>
	 * @param values The data set.
	 * @param n The position of the element.
	 * @param cmp The comparator to be used.
	 */
	public static <T> void nth_element(T[] values, int n, Comparator<T> cmp) {
		nth_element(values, 0, values.length-1, n, cmp);
	}

	
	/**
	 * <p>Partially sort the given data, so that the n<sup>th</sup>
	 * element is in its final position, if the whole data was sorted,
	 * with the given comparator.<p>
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
	 * <p>Partially sort the given data, so that the n<sup>th</sup>
	 * element is in its final position, if the whole data was sorted,
	 * with the given comparator.</p>
	 * <p>Implemented as the <i>Quickselect</i> algorithm.</p>
	 * @param values The data set.
	 * @param s The index of the first value.
	 * @param e The index of the first value.
	 * @param n The position of the element.
	 * @param cmp The comparator to be used.
	 */
	public static <T>
	void nth_element(T[] values, int s, int e, int n, Comparator<T> cmp) {
		int pivot;
		int min = s;
		int max = e;
		Pair<Integer, Integer> ndx;
		while (min != max) {
			// select pivot
			int med = (min+max)/2;
			pivot = median_of_three(values,min,med,max,cmp);
			// partition
			ndx = partition(values, min, max, values[pivot], cmp);
			// recursion
			if (n < ndx.first)
				max = ndx.first - 1;
			else if (n > ndx.second)
				min = ndx.second + 1;
			else return;
		}
	}

	/**
	 * <p>Computes the index of the median of three values.</p>
	 * @param values The data set.
	 * @param i1 The first  value.
	 * @param i2 The second value.
	 * @param i3 The third value.
	 * @return The index of the values which is the median of
	 * the values given.
	 */
	public static <T extends Comparable<T>>
	int median_of_three(T[] values, int i1, int i2, int i3)
	{
		return median_of_three(values,i1,i2,i3,FunctionObjects.less());
	}

	/**
	 * <p>Computes the index of the median of three values, given
	 * a comparator.</p>
	 * @param values The data set.
	 * @param i1 The first  value.
	 * @param i2 The second value.
	 * @param i3 The third value.
	 * @param cmp The comparator to use.
	 * @return The index of the values which is the median of
	 * the values given.
	 */
	public static <T>
	int median_of_three(T[] values, int i1, int i2, int i3, Comparator<T> cmp)
	{
		T v1 = values[i1];
		T v2 = values[i2];
		T v3 = values[i3];
		T v = median_of_three(v1, v2, v3, cmp);
		if (v.equals(v1)) return i1;
		if (v.equals(v2)) return i2;
		return i3;
	}
	
	/**
	 * <p>Computes the median value of three values.</p>
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
	 * <p>Computes the median value of three values.</p>
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
		if (a <= 0) {
			if (b >= 0) return v1;
			if (c <= 0) return v2;
			return v3;
		}
		if (b <= 0) return v1;
		if (c >= 0) return v2;
		return v3;
	}

}
