package test;

import util.BinarySearchTree;
import util.DuplicateKeyException;

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
