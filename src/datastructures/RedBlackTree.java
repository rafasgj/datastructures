/*
 * Data Structures and Algorithms.
 * Copyright (C) 2016 Rafael Guterres Jeffman
 *
 * See the LICENSE file accompanying this source code, for
 * licensing restrictions that might apply.
 *
 */

package datastructures;

class RedBlackNode<T extends Comparable<T>> {

	private T value;
	private RedBlackNode<T> left;
	private RedBlackNode<T> right;
	private RedBlackNode<T> parent;
	private boolean red;

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

	public void procuraNumero(T value, RedBlackNode<T> N) {// procura o numero para deletar

		int cmp = value.compareTo(N.value); // calcula a difença entre o numero digitado e o valor do nó
		try {
			if (cmp == 0) { // Se a diferença for zero deleta ele mesmo.
				deleteCase_um(N);
			} else if (cmp > 0) { // se a diferença for maior que zero
				N = this.right; // então ele tem que procurar a direita até encontrar
				N.procuraNumero(value, N); // repete o processo até encontrar
			} else if (cmp < 0) { // se for for menor que zero
				N = this.left; // então ele vai a esquerda
				N.procuraNumero(value, N); // repete o processo ate ecnontrar
			}
		} catch (NullPointerException e) {
			System.out.println("Valor passado não existe na árvore: " + value); // caso não encontre o valor digitado
																				// dispara mensagem
		}

	}

	public RedBlackNode<T> sibling(RedBlackNode<T> node) { // verifica se tem irmao

		RedBlackNode<T> brother = node;
		brother = node.parent;
		if (brother == null) {
			return null; // sem parente sem irmão
		}
		if (node == brother.left)
			return brother.right;
		else {
			return brother.left;
		}

	}

	public void replace_node(RedBlackNode<T> node) { // substitui o no

	}

	public void delete_um_filho(RedBlackNode<T> node) {

		RedBlackNode<T> child = node;

		child = node.right.isLeftSon() ? node.left : node.right;

		replace_node(node);

		if (node.isRed() == false) {
			if (node.isRed() == true)
				child.setBlack();
		} else
			deleteCase_um(child);

	}

	public void deleteCase_um(RedBlackNode<T> node) { // deletar a raiz
		if (node.parent != null) {
			deleteCase_dois(node);
		}
	}

	public void deleteCase_dois(RedBlackNode<T> node) {

		RedBlackNode<T> s = sibling(node);

		if (s.red == true) {
			node.parent.red = true;
			s.setBlack();
			if (node == node.parent.left) {
				node.parent.rotateLeft();
			} else {
				node.parent.rotateRight();
			}
		}
		deleteCase_tres(node);

	}

	public void deleteCase_tres(RedBlackNode<T> node) {

		RedBlackNode<T> s = sibling(node);

		if (node.parent.red == false && s.red == false && s.left.red == false && s.right.red == false) {
			s.red = true;
			deleteCase_um(node.parent);
		} else {
			deleteCase_quatro(node);
		}

	}

	public void deleteCase_quatro(RedBlackNode<T> node) {

		RedBlackNode<T> s = sibling(node);

		if (node.parent.red == true && s.red == false && s.left.red == false && s.right.red == false) {
			s.red = true;
			node.parent.red = false;
		} else {
			deleteCase_cinco(node);
		}

	}

	public void deleteCase_cinco(RedBlackNode<T> node) {

		RedBlackNode<T> s = sibling(node);

		if (s.red == false) {
			if (node == node.parent.left && s.right.red == false && s.left.red == true) {
				s.red = true;
				s.left.red = false;
				s.rotateRight();
			} else if (node == node.parent.right && s.left.red == false && s.right.red == true) {
				s.red = true;
				s.right.red = false;
				s.rotateLeft();

			} else if (node == node.parent.right && s.left.red == false && s.right.red == true) {
				s.red = true;
				s.right.red = false;
				s.rotateLeft();
			}
		}
		deleteCase_seis(node);

	}

	public void deleteCase_seis(RedBlackNode<T> node) {

		RedBlackNode<T> s = sibling(node);

		s.red = node.parent.red;
		node.parent.red = false;

		if (node == node.parent.left) {
			s.left.right.red = false;
			s.parent.rotateLeft();
		} else {
			s.left.red = false;
			node.parent.rotateRight();
		}
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

	public void delete_tree(T value) { // recebe o valor digitado
		root.procuraNumero(value, root); // recebe a o valor do console e o valor da raiz
	}

}
