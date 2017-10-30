/*
 * Data Structures and Algorithms.
 * Copyright (C) 2016 Rafael Guterres Jeffman
 *
 * See the LICENSE file accompanying this source code, for
 * licensing restrictions that might apply.
 *
 */

package test;

import datastructures.DuplicateKeyException;
import datastructures.RedBlackTree;

public class TestRedBlackTree {

	public static void main(String[] args) throws DuplicateKeyException {
		RedBlackTree<String> arvore = new RedBlackTree<>();
		
		//String[] nomes = { "N", "F", "J", "P", "R",
			//			   "D", "B", "S"};
		String[] nomes = { "N", "F", "J", "P", "L" };
		
		for (int i = 0; i < nomes.length; i++) {
			arvore.insert(nomes[i]);
			arvore.print();
			System.out.println();
		}
		System.out.println("\n-------------------------------------------------------");
		arvore.delete_tree("F");
		arvore.print();
		
		
	}

}
