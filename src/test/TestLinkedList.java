/*
 * Data Structures and Algorithms.
 * Copyright (C) 2016 Rafael Guterres Jeffman
 *
 * See the LICENSE file accompanying this source code, for
 * licensing restrictions that might apply.
 *
 */

package test;

import java.util.Comparator;

import datastructures.LinkedList;
import interfaces.BidirectionalIterator;

public class TestLinkedList {
	public static void main(String...strings) {
		LinkedList<Integer> lista = new LinkedList<>();
		BidirectionalIterator<Integer> iter;

		lista.append(10);
		iter = (BidirectionalIterator<Integer>)lista.iterator();
		iter.next();
		iter.remove();
		if (lista.isEmpty())
			System.out.println("Lista está vazia.");
		else
			System.out.println("Lista DEVERIA estar vazia.");
			
		lista.append(4);
		iter = (BidirectionalIterator<Integer>)lista.iterator();
		iter.next();
		iter.append(2);
		iter.next();
		iter.insert(3);
		iter.append(1);
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
			System.out.print(n+" ");
		System.out.println();

		iter = (BidirectionalIterator<Integer>)lista.iterator();
		iter.next();
		iter.next();
		iter.next();
		iter.remove();
		for (Integer n : lista)
			System.out.print(n+" ");
		System.out.println();
	}
}

