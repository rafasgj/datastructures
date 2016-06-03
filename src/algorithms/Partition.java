package algorithms;

import java.util.Comparator;

/**
 * Provides algorithms to partition a data set.
 */
public class Partition {
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
		return partition(values, pivot, new Comparator<T>() {
			@Override
			public int compare(T o1, T o2) {
				return o1.compareTo(o2);
			}
		});
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
		int s = 0;
		int e = values.length - 1;
		while (s < e) {
			if (cmp.compare(values[s],pivot) < 0)
				s++;
			else if (cmp.compare(values[e],pivot) > 0)
				e--;
			else {
				T t = values[s];
				values[s] = values[e];
				values[e] = t;	 
			}
		}
		return s;
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
		nth_element(values, n, new Comparator<T>() {
			@Override
			public int compare(T o1, T o2) {
				return o1.compareTo(o2);
			}
		});
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
		T pivot;
		int min = 0;
		int max = values.length - 1;
		while (min != max) {
			int med = (min+max)/2;
			pivot=median_of_three(values[min],values[med],values[max],cmp);
			int index = partition(values, pivot, cmp);
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
	 * @param cmp The comparator to use.
	 * @return The value which is the median of the values given.
	 */
	public static <T> T median_of_three(T v1, T v2, T v3, Comparator<T> cmp) {
		int a = cmp.compare(v1, v2);
		int b = cmp.compare(v1, v3);
		int c = cmp.compare(v2, v3);
		if (a <= 0 && b <= 0) return v1;
		if (b >= 0 && c <= 0) return v2;
		return v3;
	}
}
