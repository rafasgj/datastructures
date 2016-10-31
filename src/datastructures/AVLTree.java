/*
 * Data Structures and Algorithms.
 * Copyright (C) 2016 Rafael Guterres Jeffman
 *
 * See the LICENSE file accompanying this source code, for
 * licensing restrictions that might apply.
 *
 */

package datastructures;

class AVLNode<K extends Comparable<K>,V> {
	public final K key;
	public final V value;
	private AVLNode<K,V> right;
	private AVLNode<K,V> left;
	private AVLNode<K,V> parent;
	private int height;
	
	public AVLNode(K key, V value) {
		this.key = key;
		this.value = value;
		this.parent = null;
		this.height = 1;
	}
	
	private void setParent(AVLNode<K,V> parent) {
		this.parent = parent;
	}

	public AVLNode<K,V> insert(K key, V value)
			throws DuplicateKeyException
	{
		int cmp = key.compareTo(this.key);
		if (cmp < 0) {
			return insertLeft(key, value);
		} else if (cmp > 0){
			return insertRight(key, value);
		} else
			throw new DuplicateKeyException(key.toString());
	}

	private AVLNode<K,V> insertRight(K key, V value)
			throws DuplicateKeyException
	{
		if (right == null) {
			right = new AVLNode<>(key, value);
			right.setParent(this);
			return right;
		} else
			return right.insert(key, value);
	}
	
	private AVLNode<K,V> insertLeft(K key, V value)
			throws DuplicateKeyException
	{
		if (left == null) {
			left = new AVLNode<>(key, value);
			left.setParent(this);
			return left;
		} else
			return left.insert(key, value);
	}
	
	public int getBalance() {
		int hLeft = (left == null) ? 0 : left.getHeight();
		int hRight = (right == null) ? 0 : right.getHeight();
		return hLeft - hRight;
	}

	private int getHeight() {
		return height;
	}

	private int newHeight() {		
		int hLeft = (left == null) ? 0 : left.getHeight();
		int hRight = (right == null) ? 0 : right.getHeight();
		int h = 1 + Math.max(hLeft, hRight);
		return h;
	}
	
	public void updateHeight() {
		if (left != null)
			left.updateHeight();
		if (right != null)
			right.updateHeight();
		setHeight();
	}

	public boolean setHeight() {
		int h = newHeight();
		boolean result = h != height;
		height = h;
		return result;
	}
	
	public AVLNode<K,V> getRight() {
		return right;
	}

	public AVLNode<K, V> getLeft() {
		return left;
	}

	public AVLNode<K,V> rotateLeft() {
		if (right == null)
			return this;
		AVLNode<K,V> N = this;
		AVLNode<K,V> P = this.parent;
		AVLNode<K,V> R = this.right;
		AVLNode<K,V> S = R.left;
		//
		AVLNode<K,V> B = S;
		R.left = N;
		N.right = B;
		// parents
		N.parent = R;
		R.parent = P;
		if (B != null)
			B.parent = N;
		return R;
	}

	public AVLNode<K,V> rotateRight() {
		if (this.left == null)
			return this;
		AVLNode<K,V> N = this;
		AVLNode<K,V> P = this.parent;
		AVLNode<K,V> L = this.left;
		AVLNode<K,V> S = L.right;
		//
		AVLNode<K,V> B = S;
		L.right = N;
		N.left = B;
		// parents
		N.parent = L;
		L.parent = P;
		if (B != null)
			B.parent = N;
		return L;
	}

	public AVLNode<K, V> getParent() {
		return parent;
	}

	public void set(AVLNode<K, V> node) {
		int cmp = node.key.compareTo(this.key);
		if (cmp < 0) {
			left = node;
		} else if (cmp > 0) {
			right = node;
		}
		node.parent = this;
	}

	public V search(K key) {
		int cmp = key.compareTo(this.key);
		if (cmp < 0) {
			if (left == null)
				return null;
			else
				return left.search(key);
		} else if (cmp > 0){
			if (right == null)
				return null;
			else
				return right.search(key);
		} else
			return value;
	}

	public void print() {
		System.out.print("("+key);
		if (left == null) System.out.print(" _");
		else left.print();
		if (right == null) System.out.print(" _");
		else right.print();
		System.out.print(")");
	}
}

public class AVLTree<K extends Comparable<K>,V> {

	private AVLNode<K,V> root;

	public void insert(K key, V value) throws DuplicateKeyException {
		if (root == null)
			root = new AVLNode<>(key,value);
		else {
			AVLNode<K,V> novo = root.insert(key,value);
			avlCheck(novo.getParent());
		}
	}

	private void avlCheck(AVLNode<K, V> node) {
		if (node == null)
			return;
		AVLNode<K,V> P = node.getParent();
		AVLNode<K,V> R = node, C;
		int fb = node.getBalance();
		if (fb == -2) {
			C = node.getRight();
			if (C.getBalance() == +1) {
				node.set(C.rotateRight());
			}
			R = node.rotateLeft();
		}
		if (fb == +2) {
			C = node.getLeft();
			if (C.getBalance() == -1) {
				node.set(C.rotateLeft());
			}
			R = node.rotateRight();
		}
		if (R != node)
			R.updateHeight();
		else
			node.setHeight();
		if (P == null)
			root = R;
		else {
			P.set(R);
			avlCheck(P);
		}
	}

	public V search(K key) {
		if (root == null)
			return null;
		return root.search(key);
	}
	
	public void print() {
		if (root != null)
			root.print();
	}

}
