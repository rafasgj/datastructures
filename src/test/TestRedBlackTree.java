/*
 * Data Structures and Algorithms.
 * Copyright (C) 2016 Rafael Guterres Jeffman
 *
 * See the LICENSE file accompanying this source code, for
 * licensing restrictions that might apply.
 *
 */

package test;

import util.RedBlackTree;
import util.DuplicateKeyException;

public class TestRedBlackTree {

	public static void main(String[] args) throws DuplicateKeyException {
		RedBlackTree<String> arvore = new RedBlackTree<>();
		
		String[] nomes = { "Andre", "Ivonei", "Lossurdo", "Lucia", "Rafael",
						   "Amarildo", "Ivan" };
		
		for (int i = 0; i < nomes.length; i++) {
			arvore.insert(nomes[i]);
			arvore.print();
			System.out.println();
		}
	}

}
