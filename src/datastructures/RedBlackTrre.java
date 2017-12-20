

	public class RedBlackTrre<T extends Comparable<T>>
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
        	if (node.getParent() != null ) {
    			delete_case2(node);
    		}
        }
        
        private void delete_case2(RedBlackNode<T> node){

        	RedBlackNode<T> parent = node.getParent();
        	RedBlackNode<T> sibling = node.getSibling(node);
    		
    		if (sibling.red == true) {
    			parent.red = true;
    			sibling.red = false;
    			
    			if (node == parent.left) {
    				parent.rotateLeft();
    			} else {
    				parent.rotateRight();
    			}
    			delete_case3(node);
    		}
        }
        
        public void delete_case3(RedBlackNode<T> node) {
        	RedBlackNode<T> parent = node.getParent();
        	RedBlackNode<T> sibling = node.getSibling(node);
    		
    		if ((parent.red == false) && (sibling.red == false) && (sibling.left.red == false) && (sibling.right.red == false)) {
    			sibling.red = true;
    			delete_case1(parent);
    		} else {
    			delete_case4(node);
    		}
    		
    	}

    	public void delete_case4(RedBlackNode<T> node) {
    		RedBlackNode<T> parent = node.getParent();
    		RedBlackNode<T> sibling = node.getSibling(node);
    		
    		if ((parent.red == true) && (sibling.red == false) && (sibling.left.red == false) && (sibling.right.red == false)) {
    			sibling.red = true;
    			parent.red = false;
    		} else {
    			delete_case5(node);
    		}
    		
    	}

    	public void delete_case5(RedBlackNode<T> node) {
    		RedBlackNode<T> parent = node.getParent();
    		RedBlackNode<T> sibling = node.getSibling(node);
    		
    		if (sibling.red == false) {
    			if ((node == parent.left) && (sibling.right.red == false) && (sibling.left.red == true)) {
    				sibling.red = true;
    				sibling.left.red = false;
    				sibling.rotateRight();
    			} else if ((node == parent.right) && (sibling.left.red == false) && (sibling.right.red == true)) {
    				sibling.red = true;
    				sibling.right.red = false;
    				sibling.rotateLeft();
    			}
    		}
    		delete_case6(node);
    	}
    	
    	public void delete_case6(RedBlackNode<T> node) {
    		RedBlackNode<T> parent = node.getParent();
    		RedBlackNode<T> sibling = node.getSibling(node);
    		
    		sibling.red = parent.red;
    		parent.red = false;
    		
    		if (node == parent.left) {
    			sibling.right.red = false;
    			parent.rotateLeft();
    		} else {
    			sibling.left.red = false;
    			parent.rotateLeft();
    		}
    		removeOneChild(node);
    	}
        
        private void removeOneChild(RedBlackNode<T> node) {
		RedBlackNode<T> child = node.right.red ? node.left : node.right;
	        
	        replace_node(node, child);
	        
	        if (node.red == false) {
	            if(child.red == true) {
	                child.red = false;
	            } else {
	                delete_case1(child);
	            }
	        }
	     free(node);
	}
	public void free(RedBlackNode<T> node) {
		node.left = null;
		node.right = null;
		node.parent = null;
		node.value = null;
	}

	public void replace_node(RedBlackNode<T> node, RedBlackNode<T> child) {
		child.parent = root.parent;
        if(root.parent == null) {
            //rootReference.set(child);
        }
        else {
            if(root.parent.left == root) {
                root.parent.left = child;
            } else {
                root.parent.right = child;
            }

		
        }
			
	}

		public void print() {
		if (root == null)
			System.out.println("Empty tree.");
		else
			root.print();
	}

}
