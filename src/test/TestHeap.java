package test;

import java.util.Comparator;

import util.BinaryHeap;

public class TestHeap {
	public static void main(String...strings) {
		BinaryHeap<Integer> heap = new BinaryHeap<>(new Comparator<Integer>(){
			@Override
			public int compare(Integer o1, Integer o2) {
				//return o1 - o2; // min heap
				return o2 - o1; // max heap
			}
		});
		
		for (int i = 0; i < 20; i++)
			heap.push(i);
		
		while (!heap.isEmpty())
			System.out.println(heap.pop()+" ");
		System.out.println();
	}
}
