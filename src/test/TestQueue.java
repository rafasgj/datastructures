/*
 * Data Structures and Algorithms.
 * Copyright (C) 2016 Rafael Guterres Jeffman
 *
 * See the LICENSE file accompanying this source code, for
 * licensing restrictions that might apply.
 *
 */

package test;

import datastructures.Queue;

public class TestQueue {

	public static void main(String[] args) {
		Queue<Integer> queue = new Queue<>();
		for (int i = 0; i < 10; i++)
			queue.push(i);
		while (!queue.isEmpty())
			System.out.print(queue.pop()+ " ");
	}

}
