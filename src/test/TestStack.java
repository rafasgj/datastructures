package test;

import util.Stack;

public class TestStack {

	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < 10; i++)
			stack.push(i);
		while (!stack.isEmpty())
			System.out.print(stack.pop()+ " ");
	}

}
