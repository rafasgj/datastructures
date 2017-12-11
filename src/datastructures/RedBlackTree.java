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
	
        public RedBlackNode<T> delete(T value){
            int cmp = value.compareTo(this.value);
            if (cmp > 0) {
                return deleteRight(value);
            } else {
                if (cmp < 0) {
                    return deleteLeft(value);
                } else {
                    System.out.println("Achou!");
                    return this;
                }
            }
        }
        
        public RedBlackNode<T> deleteLeft(T data){
            return this.left.delete(data);
        }
        
        public RedBlackNode<T> deleteRight(T data){
            return this.right.delete(data);
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
        
        public RedBlackNode<T> getSibling() {
            if (parent == null) { return null;}
            
            RedBlackNode<T> S = parent;
            
            if (S == null) { return null; }
            if (S.left == parent) { return S.right; }
            
            return S.left;
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
        
        public RedBlackNode<T> getRight(){
            return this.right;
        }
        
        public RedBlackNode<T> getLeft(){
            return this.left;
        }

	public void setLeft(RedBlackNode<T> node) {
		left = node;
	}
	
}

public class RedBlackTree<T extends Comparable<T>>
{
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
        
        public void delete(T data) throws DuplicateKeyException {
            RedBlackNode<T> node;
            node = root.delete(data);
            delete_case1(node);
        }
        
        private void delete_case1(RedBlackNode<T> node){
            RedBlackNode<T> parent = node.getParent();
            if (parent != null) {
                delete_case2(node);
            }
            node = null;
        }
        
        private void delete_case2(RedBlackNode<T> node){
            RedBlackNode<T> S = node.getSibling();
            RedBlackNode<T> P = node.getParent();
            
            if (S.isRed()) {
                P.setRed();
                S.setBlack();
                
                if (P.isLeftSon()) {
                    P.rotateLeft();
                } else {
                    P.rotateRight();
                }
            }
            delete_case3(node);
        }
        
        private void delete_case3(RedBlackNode<T> node){
            RedBlackNode<T> S = node.getSibling();
            RedBlackNode<T> P = node.getParent();
            
            if (!P.isRed() && !S.isRed() && !S.getLeft().isRed() && !S.getRight().isRed()) {
                S.setRed();
                delete_case1(P);
            } else {
                delete_case4(node);
            }
        }
        
        private void delete_case4(RedBlackNode<T> node){
            RedBlackNode<T> S = node.getSibling();
            RedBlackNode<T> P = node.getParent();
            
            if (P.isRed() && !S.isRed() && !S.getLeft().isRed() && !S.getRight().isRed()) {
                S.setRed();
                P.setBlack();
            } else {
                delete_case5(node);
            }
        }
        
        private void delete_case5(RedBlackNode<T> node) {
            RedBlackNode<T> S = node.getSibling();
            RedBlackNode<T> P = node.getParent();
            
            if (!S.isRed()) {
                if (node == P.getLeft() && !S.getRight().isRed() && S.getLeft().isRed()) {
                    S.setRed();
                    S.getLeft().setBlack();
                    S.rotateRight();
                } else if (node == P.getRight() && !S.getLeft().isRed() && S.getRight().isRed()) {
                    S.setRed();
                    S.getRight().setBlack();
                    S.rotateLeft();
                }
            }
            delete_case6(node);
        }
        
        private void delete_case6(RedBlackNode<T> node){
            RedBlackNode<T> S = node.getSibling();
            RedBlackNode<T> P = node.getParent();
            
            if (P.isRed()) {
                S.setRed();
            } else {
                S.setBlack();
            }
            
            P.setBlack();
            
            if (node == P.getLeft()) {
                S.getLeft().getRight().setBlack();
                S.getParent().rotateLeft();
            } else {
                S.getLeft().setBlack();
                P.rotateRight();
            }
        }
        
        public void print() {
		if (root == null)
			System.out.println("Empty tree.");
		else
			root.print();
	}
}
