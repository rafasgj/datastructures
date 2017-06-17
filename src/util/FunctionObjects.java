/*
 * Data Structures and Algorithms.
 * Copyright (C) 2016 Rafael Guterres Jeffman
 *
 * See the LICENSE file accompanying this source code, for
 * licensing restrictions that might apply.
 *
 */

package util;

import java.util.Comparator;

/**
 * <p>This class provides several function objects that can be
 * used for several operations.</p>
 */
public class FunctionObjects {
	/**
	 * <p>Creates a function object that compares two values,
	 * and return the usual Java values for ordering (less
	 * than zero if the first is smaller than the second).</p>
	 * @return
	 */
	public static <T extends Comparable<T>> Comparator<T> less() {
		return new Comparator<T>() {
			@Override
			public int compare(T o1, T o2) {
				return o1.compareTo(o2);
			}
		};
	}

	/**
	 * <p>Creates a function object that compares two values,
	 * and reverses the usual Java values for ordering, that is,
	 * it returns less than zero if the first object is greater
	 * than the second one.</p>
	 * @return
	 */
	public static <T extends Comparable<T>> Comparator<T> greater() {
		return new Comparator<T>() {
			@Override
			public int compare(T o1, T o2) {
				return o2.compareTo(o1);
			}
		};
	}
	
}
