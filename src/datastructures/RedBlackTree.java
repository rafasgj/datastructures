/*
 * Data Structures and Algorithms.
 * Copyright (C) 2016 Rafael Guterres Jeffman
 *
 * See the LICENSE file accompanying this source code, for
 * licensing restrictions that might apply.
 *
 */

package datastructures;

import java.util.HashMap;

//import java.util.Comparator;

class RedBlackNode<T extends Comparable<T>> {

	public T value;
	public RedBlackNode<T> left;
	public RedBlackNode<T> right;
	public RedBlackNode<T> parent;
	public boolean red = true;
	
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
	
}

public class RedBlackTree<T extends Comparable<T>>
{
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
	
	/*public RedBlackNode<T> search(T value){
		
		return null;
		
	}
	
	public void remove(T value) throws DuplicateKeyException {
		
		RedBlackNode<T> n = new RedBlackNode<T>(value);
			
		n = search(value);
		
		delete_one_child(n);
		
	}*/
	
	private void replace_node(RedBlackNode<T> n, RedBlackNode<T> child) {
        
		if (child != null) {
			
            child.parent = n.parent;
            
        }
		
        if (n.parent != null) {
        	
            if (n.parent.left == n) {
            	
                n.parent.left = child;
                
            } else {
            	
                n.parent.right = child;
                
            }
            
        }
        
        n.left = null;
        n.right = null;
        n.parent = null;
        
        if (root == n) {
        	
            root = child;
            
        }
        
	}

	private RedBlackNode<T> sibling(RedBlackNode<T> n, RedBlackNode<T> nparent) {
        
		if (n == nparent.left) {
			
            return nparent.right;
            
        } else {
        	
            return nparent.left;
            
        }
		
    }
	
	private void delete_one_child(RedBlackNode<T> n) {
        
		RedBlackNode<T> child = n.right == null ? n.left : n.right;

		RedBlackNode<T> nparent = n.getParent();
        
        replace_node(n, child);

        if (n.red == false) {
        	
            if (child != null && child.red) {
            	
                child.red = false;
                
            } else {
            	
                delete_case1(child, nparent);
                
            }
            
        }
        
    }
	
	private void delete_case1(RedBlackNode<T> n, RedBlackNode<T> nparent) {
		
		if(nparent != null) {
			
			delete_case2(n, nparent);
			
		}
		
	}
	
	private void delete_case2(RedBlackNode<T> n, RedBlackNode<T> nparent) {
		
		RedBlackNode<T> s = sibling(n, nparent);
		
		if(s.red) {
			
			nparent.red = true;
			s.red = false;
			
			if(n == nparent.left) {
				
				nparent.rotateLeft();
				
			} else {
				
				nparent.rotateRight();
				
			}
			
		}
		
		delete_case3(n, nparent);
		
	}
	
	private void delete_case3(RedBlackNode<T> n, RedBlackNode<T> nparent) {
		
		RedBlackNode<T> s = sibling(n, nparent);
		
		if((nparent.red == false)
				&& (s.red)
				&& (s.left == null || s.left.red == false)
				&& (s.right == null || s.right.red == false)) {
			
			s.red = true;
			
			delete_case1(nparent, nparent.parent);
			
		} else {
			
			delete_case4(n, nparent);
			
		}
		
	}
	
	private void delete_case4(RedBlackNode<T> n, RedBlackNode<T> nparent) {
		
		RedBlackNode<T> s = sibling(n, nparent);
		
		if((nparent.red)
				&& (s.red == false)
				&& (s.left == null || s.left.red == false)
				&& (s.right == null || s.right.red == false)) {
			
			s.red = true;
			nparent.red = false;
			
		} else {
			
			delete_case5(n, nparent);
			
		} 
		
	}
	
	private void delete_case5(RedBlackNode<T> n, RedBlackNode<T> nparent) {
		
		RedBlackNode<T> s = sibling(n, nparent);
		
		if(s != null & s.red == false) {
			
			if((n == nparent.left)
					&& (s.right.red == false)
					&& (s.left.red)) {
				
				s.red = true;
				s.left.red = false;
				
				s.rotateRight();
				
			} else if ((n == nparent.right)
					&& (s.left.red = false)
					&& (s.right.red)) {
				
				s.red = true;
				s.right.red = false;
				s.rotateLeft();
				
			}
			
		}
		
		delete_case6(n, nparent);
		
	}
	
	private void delete_case6(RedBlackNode<T> n, RedBlackNode<T> nparent) {
		
		RedBlackNode<T> s = sibling(n, nparent);
		
		s.red = nparent.red;
		nparent.red = false;
		
		if(n == nparent.left) {
			
			s.right.red = false;
			nparent.rotateLeft();
			
		} else {
			
			s.left.red = false;
			nparent.rotateRight();
			
		}
		
	} 
	
}
