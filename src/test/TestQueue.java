package test;

import util.Queue;

public class TestQueue {

	public static void main(String[] args) {
		Queue<Integer> queue = new Queue<>();
		for (int i = 0; i < 10; i++)
			queue.push(i);
		while (!queue.isEmpty())
			System.out.print(queue.pop()+ " ");
	}

}
