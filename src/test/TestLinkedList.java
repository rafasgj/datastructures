package test;

import java.util.Comparator;

import util.LinkedList;

public class TestLinkedList {
	public static void main(String...strings) {
		LinkedList<Integer> lista = new LinkedList<>();
		lista.append(4);
		lista.append(2);
		lista.append(3);
		lista.append(1);
		for (Integer n : lista)
			System.out.print(n+" ");
		System.out.println();
		lista.sort(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1-o2;
			}
		});
		for (Integer n : lista)
			System.out.println(n+" ");
	}
}

