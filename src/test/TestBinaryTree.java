/*
 * Data Structures and Algorithms.
 * Copyright (C) 2016 Rafael Guterres Jeffman
 *
 * See the LICENSE file accompanying this source code, for
 * licensing restrictions that might apply.
 *
 */

package test;

import datastructures.BinarySearchTree;
import datastructures.DuplicateKeyException;

public class TestBinaryTree {

	public static void main(String[] args) throws DuplicateKeyException {
		BinarySearchTree<Integer> arvore = new BinarySearchTree<>();
		
		arvore.insert(2);
		arvore.insert(4);
		arvore.insert(3);
		arvore.insert(1);
		arvore.insert(5);
		
		arvore.print();
	}

}
