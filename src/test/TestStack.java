/*
 * Data Structures and Algorithms.
 * Copyright (C) 2016 Rafael Guterres Jeffman
 *
 * See the LICENSE file accompanying this source code, for
 * licensing restrictions that might apply.
 *
 */

package test;

import datastructures.Stack;

public class TestStack {

	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < 10; i++)
			stack.push(i);
		while (!stack.isEmpty())
			System.out.print(stack.pop()+ " ");
	}

}
