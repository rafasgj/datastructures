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
			throw new DuplicateKeyException("Already inserted: "+value);
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
		System.out.print("(" + value + r +" ");
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
		if (right == null) return;
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
		if (left == null) return;
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
	//metodo procura valor para deletar dentro dos nós da arvore
	public void delete(T value, RedBlackNode<T> N) {
		
		int cmp = value.compareTo(N.value);
		
		if (cmp == 0) {
			delete_case1(N);//caso encontre entra nos casos de deleção
		}
		else if (cmp > 0) {
			N = this.right;
			N.delete(value, N);//caso não ache ele segue para o próximo nó se o valor for maior que o do proprio nó
		}
		else if(cmp < 0) {
			N = this.left;
			N.delete(value, N); //caso não ache ele segue para o próximo nó se o valor for menor que o do proprio nó
		}
		
		
		
	}
	
	public void delete_case1(RedBlackNode<T> node) {//se o que queremos deletar está na raiz
		T valueleaf;
		if(node.parent == null) {// ve se o "pai" é nulo se for é a raiz e busca o sucessor.
			node = node.right;
			if(node.left == null) {// caso não tenha filho mais a esquerda
				valueleaf = node.value;
				node.parent.value = valueleaf;
				node.right.parent = node.parent; 
				node.parent.right = node.right;
				if(node.right.isRed() == true ) {
					node.right.setBlack();
				}
			}else {// se tiver filho mais a esquerda então
				node = findSucessor(node);
				valueleaf = node.value;
				node.parent.left = null;
				node = findRoot(node);
				node.value = valueleaf;
				if(node.right.isRed() == true && node.right.left == null && node.right.right !=null) {
					node.right.setBlack();
					node.right.right.setRed();
				}else if(node.right.isRed() == true && node.right.left == null && node.right.right ==null) {
					node.right.setBlack();
				}
			}
		}else{
			delete_case2(node);
		}
	}
	
	public void delete_case2(RedBlackNode<T> node) {//deletar folha vermelha ou nó preto com apenas um filho vermelho.
		if(node.isRed() == true && node.left == null && node.right == null) {
			if(node.isLeftSon() == true) {
				node.parent.setLeft(null);	
			}
			else if(node.isRightSon() == true) {
				node.parent.setRight(null);
			}
		}
		else if(node.isRed() == false && node.left != null && node.right == null && node.left.isRed() == true || node.isRed() == false && node.left == null && node.right != null && node.right.isRed() == true) {
			if(node.isLeftSon() == true) {
				if(node.left != null && node.right == null && node.left.isRed() == true){// se o nó for filho a esquerda e tiver um filho a esquerda
					node.left.setBlack();
					node.left.parent = node.parent; 
					node.parent.setLeft(node.left);
				}else if(node.left == null && node.right != null && node.right.isRed() == true){ // se o nó for filho a esquerda e tiver um filho a direita
					node.right.setBlack();
					node.right.parent = node.parent;
					node.parent.setLeft(node.right);
				}
			}else if(node.isRightSon() == true) {
				if(node.left != null && node.right == null && node.left.isRed() == true){// se o nó for filho a esquerda e tiver um filho a esquerda
					node.left.setBlack();
					node.left.parent = node.parent; 
					node.parent.setRight(node.left);
				}else if(node.left == null && node.right != null && node.right.isRed() == true){ // se o nó for filho a esquerda e tiver um filho a direita
					node.right.setBlack();
					node.right.parent = node.parent;
					node.parent.setRight(node.right);
				}
			}
		}else{
			delete_case3(node);
		}
	}
	
	public void delete_case3(RedBlackNode<T> node) {														//&& node.parent.left.right != null && node.parent.left.right != null
		if(node.isRed() == false && node.right == null && node.left == null && node.parent.right.isRed() == true) {
																												//&& node.parent.left.right != null || node.isRed() == false && node.right == null && node.left == null && node.parent.right.isRed() == false && node.parent.left.right != null
		}else if(node.isRed() == false && node.right == null && node.left == null && node.parent.right.isRed() == false ) {
			System.out.println("TESTEEEEEEE");
			node = node.parent;
			node.left = null;
			node.right.parent = node.parent;
			node.parent = node.right;
			node.right = node.parent.left;
			node.parent.left = node;	// continuar
		}else {
			
			delete_case4(node);
		}
	}
	
	public void delete_case4(RedBlackNode<T> node) {	
	}
	
	public void delete_case5(RedBlackNode<T> node) {
		
	}
	
	public void delete_case6(RedBlackNode<T> node) {
		
	}
	
	public RedBlackNode<T> findRoot(RedBlackNode<T> node) {// metodo que acha a raiz

		if(node.parent != null) {
			while(node.parent != null) {
				node = node.parent;			
			}
			
		}
		return node;
		
	}
	
	public RedBlackNode<T> findSucessor(RedBlackNode<T> node){//segue até o filho mais a esquerda do lado direito
		while(node.left != null) {
			node = node.left;
		}
		return node;
	}
}

public class RedBlackTree<T extends Comparable<T>>{
	
	private RedBlackNode<T> root;
	
	public void insert(T data) throws DuplicateKeyException
	{
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
		if (!P.isRed()) return;
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
		}
		else if (P.isLeftSon() && !node.isLeftSon()) {
			P.rotateLeft();
			G.setLeft(node);
			N = P;
		}
			
		insert_case5(N);
	}
	
	private void insert_case5(RedBlackNode<T> node) { // P is R, U is B 
		RedBlackNode<T> P = (RedBlackNode<T>)(node.getParent());
		RedBlackNode<T> G = (RedBlackNode<T>)P.getParent();
		RedBlackNode<T> GG = null;
		boolean gl = G.isLeftSon(); 
		if (G.getParent() != null)
			GG = (RedBlackNode<T>)(G.getParent());
		
		P.setBlack();
		G.setRed();
		if (P.isRightSon()) {
			G.rotateLeft();
		} else {
			G.rotateRight();
		}
		if (GG != null)
			if (gl) GG.setLeft(P);
			else GG.setRight(P);
		else
			root = P;
	}
	
	public void print() {

		if (root == null)
			System.out.println("Empty tree.");
		else
			root.print();
	}
	
	public void delete_tree(T value){
		root.delete(value, root);
		}
		
	}
