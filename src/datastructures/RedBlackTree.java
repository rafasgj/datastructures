/*
 * Data Structures and Algorithms.
 * Copyright (C) 2016 Rafael Guterres Jeffman
 *
 * See the LICENSE file accompanying this source code, for
 * licensing restrictions that might apply.
 *
 */

package datastructures;

import org.omg.CORBA.FREE_MEM;

class RedBlackNode<T extends Comparable<T>> {

	private T value;
	private RedBlackNode<T> left;
	private RedBlackNode<T> right;
	private RedBlackNode<T> parent;
	private boolean red;

	public RedBlackNode<T> getLeft() {
		return left;
	}

	public RedBlackNode<T> getRight(boolean mustSet, RedBlackNode<T> node) {
		if (mustSet) {
			this.setRight(node);
		}
		return right;
	}

	public RedBlackNode<T> getRight() {
		return right;
	}

	public void setParent(RedBlackNode<T> parent) {
		this.parent = parent;
	}

	public void setRed(boolean red) {
		this.red = red;
	}

	public RedBlackNode(T value) {
		this.value = value;
		this.red = true;
	}

	public RedBlackNode<T> insert(T value) throws DuplicateKeyException {
		int cmp = value.compareTo(this.value);
		if (cmp < 0)
			return insertLeft(value);
		else if (cmp > 0)
			return insertRight(value);
		else
			throw new DuplicateKeyException("Already inserted: " + value);
	}

	private RedBlackNode<T> insertLeft(T value) throws DuplicateKeyException {
		if (left == null) {
			left = new RedBlackNode<>(value);
			left.parent = this;
			return left;
		} else
			return left.insert(value);
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	private RedBlackNode<T> insertRight(T value) throws DuplicateKeyException {
		if (right == null) {
			right = new RedBlackNode<>(value);
			right.parent = this;
			return right;
		} else
			return right.insert(value);
	}

	public RedBlackNode<T> getParent() {
		return parent;
	}

	public void setBlack() {
		this.red = false;
	}

	public void setRed() {
		this.red = true;
	}

	public boolean isRed() {
		return red;
	}

	public void print() {
		String r = red ? "*" : "";
		System.out.print("(" + value + r + " ");
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

	public RedBlackNode<T> getUncle() {
		if (parent == null)
			return null;
		RedBlackNode<T> G = parent.parent;
		if (G == null)
			return null;
		if (G.left == parent)
			return G.right;
		return G.left;
	}

	public boolean isRightSon() {
		if (parent == null)
			return false;
		return parent.right == this;
	}

	public boolean isLeftSon() {
		if (parent == null)
			return false;
		return parent.left == this;
	}

	public void rotateLeft() {
		if (right == null)
			return;
		RedBlackNode<T> N = this;
		RedBlackNode<T> P = this.parent;
		RedBlackNode<T> R = this.right;
		RedBlackNode<T> S = R.left;
		//
		RedBlackNode<T> B = S;
		R.left = N;
		N.right = B;
		// parents
		N.parent = R;
		R.parent = P;
		if (B != null)
			B.parent = N;
	}


	public void rotateRight() {
		if (left == null)
			return;
		RedBlackNode<T> N = this;
		RedBlackNode<T> P = this.parent;
		RedBlackNode<T> L = this.left;
		RedBlackNode<T> S = L.right;
		//
		RedBlackNode<T> B = S;
		L.right = N;
		N.left = B;
		// parents
		N.parent = L;
		L.parent = P;
		if (B != null)
			B.parent = N;
	}

	public void setRight(RedBlackNode<T> node) {
		right = node;
	}

	public void setLeft(RedBlackNode<T> node) {
		left = node;
	}

}

public class RedBlackTree<T extends Comparable<T>> {
	private RedBlackNode<T> root;

	public void insert(T data) throws DuplicateKeyException {
		RedBlackNode<T> node;
		if (root == null)
			node = root = new RedBlackNode<>(data);
		else
			node = root.insert(data);
		insert_case1(node);
	}

	private void insert_case1(RedBlackNode<T> node) {
		if (node.getParent() == null) {
			node.setBlack();
			return;
		}
		insert_case2(node);
	}

	private void insert_case2(RedBlackNode<T> node) {
		RedBlackNode<T> P = node.getParent();
		if (!P.isRed())
			return;
		insert_case3(node);
	}

	private void insert_case3(RedBlackNode<T> node) {
		RedBlackNode<T> P = node.getParent();
		RedBlackNode<T> U = node.getUncle();
		RedBlackNode<T> G = P.getParent();
		if (P.isRed() && (U != null && U.isRed())) {
			P.setBlack();
			U.setBlack();
			G.setRed();
			insert_case1(G);
		} else
			insert_case4(node);
	}

	private void insert_case4(RedBlackNode<T> node) { // P is R, U is B
		RedBlackNode<T> P = node.getParent();
		RedBlackNode<T> G = P.getParent();
		RedBlackNode<T> N = node;

		if (P.isRightSon() && !node.isRightSon()) {
			P.rotateRight();
			G.setRight(node);
			N = P;
		} else if (P.isLeftSon() && !node.isLeftSon()) {
			P.rotateLeft();
			G.setLeft(node);
			N = P;
		}

		insert_case5(N);
	}

	private void insert_case5(RedBlackNode<T> node) { // P is R, U is B
		RedBlackNode<T> P = (RedBlackNode<T>) (node.getParent());
		RedBlackNode<T> G = (RedBlackNode<T>) P.getParent();
		RedBlackNode<T> GG = null;
		boolean gl = G.isLeftSon();
		if (G.getParent() != null)
			GG = (RedBlackNode<T>) (G.getParent());

		P.setBlack();
		G.setRed();
		if (P.isRightSon()) {
			G.rotateLeft();
		} else {
			G.rotateRight();
		}
		if (GG != null)
			if (gl)
				GG.setLeft(P);
			else
				GG.setRight(P);
		else
			root = P;
	}

	public void print() {
		if (root == null)
			System.out.println("Empty tree.");
		else
			root.print();
	}

	public boolean removeElementTree(T data) {
		if (SearchElementTree(data)) {
			RedBlackNode<T> node = this.root;
			RedBlackNode<T> NO = this.root.getLeft();
			this.root = RemoveNode(node, data);
			if (this.root != null) {
				this.root.setBlack();
				//this.root.getLeft().setLeft(NO);
				insert_case1(this.root);
			}
			return true;
		} else
			return false;
	}

	public void AlterColor(RedBlackNode<T> node) {
		if (node.isRed())
			node.setBlack();
		else
			node.setRed();

		if (node.getLeft() != null) {
			if (node.getLeft().isRed())
				node.getLeft().setBlack();
			else
				node.getLeft().setRed();
		}

		if (node.getRight() != null) {
			if (node.getRight().isRed())
				node.getRight().setBlack();
			else
				node.getRight().setRed();
		}
	}

	public RedBlackNode<T> moveLeftRed(RedBlackNode<T> node) {
		AlterColor(node);

		if (node.getRight().getLeft().isRed()) {
			RedBlackNode<T> nodeRight = node.getRight();
			nodeRight.rotateRight();
			node.setRight(nodeRight);

			RedBlackNode<T> nodeLeft = node.getLeft();
			nodeLeft.rotateLeft();
			node = nodeLeft;
			AlterColor(node);
		}

		return node;
	}

	public RedBlackNode<T> moveRightRed(RedBlackNode<T> node) {
		AlterColor(node);

		if (node.getLeft().getLeft().isRed()) {
			RedBlackNode<T> nodeRight = node;
			nodeRight.rotateRight();
			node = nodeRight;
			AlterColor(node);
		}

		return node;
	}

	public RedBlackNode<T> RemoveNode(RedBlackNode<T> node, T data) {
		
		if (data.compareTo(node.getValue()) < node.getValue().compareTo(data) && node != null) {
			if (!node.getLeft().isRed() && !node.getLeft().getLeft().isRed())
				node = moveLeftRed(node);

			node.setLeft(RemoveNode(node.getLeft(), data));
		} else {
			if (node.getLeft() != null) {
				if (node.getLeft().isRed()) {
					RedBlackNode<T> nodeRight = node;
					nodeRight.rotateRight(); 
					node = nodeRight;
					node.setParent(null);
				}
			}

			if (data == node.getValue() && node.getRight() == null) {
				// node = null;
				return null;
			}

			if (node.getRight() != null && node.getRight().getLeft() != null) {
				if (!node.getRight().isRed() && !node.getRight().getLeft().isRed())
					node = moveRightRed(node);
			}

			if (data == node.getValue()) {
				RedBlackNode<T> nodeValue = SearchMenor(node.getRight());
				node.setValue(nodeValue.getValue());
				node.setRight(removeMenorValor(node.getRight()));
			} else {
				node.setRight(RemoveNode(node.getRight(), data));
				return node;

			}
		}

		return node;

	}

	private RedBlackNode<T> removeMenorValor(RedBlackNode<T> node) {
		if (node.getLeft() == null) {
			node.setParent(node.getParent().getParent());
			return null;
		}

		if (!node.getLeft().isRed() && !node.getLeft().getLeft().isRed())
			node = moveLeftRed(node);
		return node;
	}

	private RedBlackNode<T> SearchMenor(RedBlackNode<T> atual) {
		RedBlackNode<T> node1 = atual;
		RedBlackNode<T> node2 = atual.getLeft();

		while (node2 != null) {
			node1 = node2;
			node2 = node2.getLeft();
		}
		return node1;
	}

	public boolean SearchElementTree(T data) {

		RedBlackNode<T> nodeRaiz = root;
		if (nodeRaiz == null)
			return false;
		RedBlackNode<T> nodeAtual = nodeRaiz;

		while (nodeAtual != null) {

			if (data == nodeAtual.getValue()) {
				return true;
			}
			if (data.compareTo(nodeAtual.getValue()) > nodeAtual.getValue().compareTo(data))
				nodeAtual = nodeAtual.getRight();
			else
				nodeAtual = nodeAtual.getLeft();
		}
		
		return false;
	}

	public static void main(String[] args) throws DuplicateKeyException {
		RedBlackTree<Integer> tree = new RedBlackTree<Integer>();

		tree.insert(13);
		tree.insert(8);
		tree.insert(11);
		tree.insert(1);
		tree.insert(6);
		tree.insert(17);
		tree.insert(15);
		tree.insert(25);
		tree.insert(22);
		tree.insert(27);

		// tree.print();

		boolean teste = tree.removeElementTree(25);
		tree.print();
	}
}
