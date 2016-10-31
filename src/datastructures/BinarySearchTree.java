/*
 * Data Structures and Algorithms.
 * Copyright (C) 2016 Rafael Guterres Jeffman
 *
 * See the LICENSE file accompanying this source code, for
 * licensing restrictions that might apply.
 *
 */

package datastructures;

class BSTNode<T extends Comparable<T>> {

	private T value;
	private BSTNode<T> left;
	private BSTNode<T> right;

	public BSTNode(T value) {
		this.value = value;
	}

	public void insert(T value) throws DuplicateKeyException {
		int cmp = value.compareTo(this.value);
		if (cmp < 0)
			insertLeft(value);
		else if (cmp > 0)
			insertRight(value);
		else
			throw new DuplicateKeyException("Already inserted: "+value);
	}
	
	
	private void insertLeft(T value) throws DuplicateKeyException {
		if (left == null)
			left = new BSTNode<>(value);
		else
			left.insert(value);
	}

	private void insertRight(T value) throws DuplicateKeyException {
		if (right == null)
			right = new BSTNode<>(value);
		else
			right.insert(value);
	}

	public void print() {
		System.out.print("(" + value + " ");
		if (left != null)
			left.print();
		else
			System.out.print("_");
		if (right != null)
			right.print();
		else
			System.out.print(" _");
		System.out.print(")");
	}
}

public class BinarySearchTree<T extends Comparable<T>> {

	private BSTNode<T> raiz;

	public void insert(T value) throws DuplicateKeyException {
		if (raiz == null) {
			raiz = new BSTNode<>(value);
		} else
			raiz.insert(value);
	}

	public void print() {
		if (raiz != null)
			raiz.print();
	}
}
