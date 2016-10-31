/*
 * Data Structures and Algorithms.
 * Copyright (C) 2016 Rafael Guterres Jeffman
 *
 * See the LICENSE file accompanying this source code, for
 * licensing restrictions that might apply.
 *
 */

package test;

import datastructures.AVLTree;
import datastructures.DuplicateKeyException;

public class TestAVLTree {

	public static void main(String[] args) throws DuplicateKeyException {
		AVLTree<String, Integer> arvore = new AVLTree<>();
		
		String[] nomes = { "Andre", "Ivonei", "Lossurdo", "Lucia", "Rafael"};
		
		for (int i = 0; i < nomes.length; i++)
			arvore.insert(nomes[i], i);
		
		arvore.print();
	}

}
