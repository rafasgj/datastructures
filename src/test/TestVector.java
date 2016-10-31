/*
 * Data Structures and Algorithms.
 * Copyright (C) 2016 Rafael Guterres Jeffman
 *
 * See the LICENSE file accompanying this source code, for
 * licensing restrictions that might apply.
 *
 */

package test;

import datastructures.Vector;

public class TestVector {
	public static void main(String...strings) {
		Vector<Integer> vec = new Vector<>();
		for (int i = 0; i < 10; i++)
			vec.append(i);
		for (int i : vec)
			System.out.print(i + " ");
		System.out.println();
		vec.remove(5);
		for (int i : vec)
			System.out.print(i + " ");
		System.out.println();
		vec.insert(5, 5);
		for (int i : vec)
			System.out.print(i + " ");
		System.out.println();
		interfaces.Iterator<Integer> iter = vec.iterator();
		iter.next();
		iter.insert(-1);
		iter.next();
		iter.remove();
		for (int i : vec)
			System.out.print(i + " ");
	}
}
