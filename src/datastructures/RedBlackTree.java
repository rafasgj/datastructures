class RedBlackNode<T extends Comparable<T>> {

	private T value;
	private RedBlackNode<T> left;
	private RedBlackNode<T> right;
	private RedBlackNode<T> parent;
	private boolean red;
	
	public RedBlackNode<T> getLeft() {
		return left;
	}

	public RedBlackNode<T> getRight() {
		return right;
	}

	public RedBlackNode(T value) {
		this.value = value;
		this.red = true;
	}
	
	public void setParent(RedBlackNode<T> parent)
	{
		this.parent = parent;
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

	public T getValue() {
		return this.value;
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
	
	//////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////
	
	  public void delete(RedBlackNode<T> z) {
	        RedBlackNode<T> y = z,x;
	        boolean yOrigin = y.isRed();
	        if (z.getLeft() == null) {
	            x=z.getRight();
	            transplant(z, z.getRight());
	        } else if (z.getRight() == null) {
	            x=z.getLeft();
	            transplant(z, z.getLeft());
	        } else {
	            y = minChild(z.getRight());
	            yOrigin=y.isRed();
	            x=y.getRight();
	            if (y.getParent() == z) {
	                x.setParent(y);
	            }else
	            {
	                transplant(y, y.getRight());
	                y.setRight(z.getRight());
	                y.getRight().setParent(y);
	            }
	            transplant(z, y);
	            y.setLeft(z.getLeft());
	            y.getLeft().setParent(y);
	            if(z.isRed())
	            	y.setRed();
	            else
	            	y.setBlack();
	        }
	        if(!yOrigin)
	        {
	            deleteFixup(x);
	        }
	    }
	 /////////////////////////////////////////////////////////////////////////////////////////////
	  
	  void transplant(RedBlackNode<T> u, RedBlackNode<T> v) {
	        if (u.getParent() == null) {
	            this.root = v;
	        } else if (u == u.getParent().getLeft()) {
	            u.getParent().setLeft(v);
	        } else {
	            u.getParent().setRight(v);
	        }
	        
	            v.setParent(u.getParent());
	    }
	  /////////////////////////////////////////////////////////////////////////////////////////////
	
	  void deleteFixup(RedBlackNode<T> x)
	    {
	     
	        while(x!=root && !x.isRed())
	        {
	           RedBlackNode<T> w;
	           if(x.isLeftSon())
	           {
	               w=x.getParent().getRight();
	               if(w.isRed())
	               {
	                   w.setBlack();
	                   x.getParent().setRed();
	                   x.getParent().rotateLeft();
	                   w=x.getParent().getRight();
	               }
	               if(!w.getLeft().isRed() && !w.getRight().isRed())
	               {
	                   w.setRed();
	                   x=x.getParent();
	               }
	               else {
	                   if(!w.getRight().isRed())
	                   {
	                       w.getLeft().setBlack();
	                       w.setRed();
	                       w.rotateRight();
	                       w=x.getParent().getRight();
	                   }
	                   if(x.getParent().isRed())
	                	   w.setRed();
	                   else
	                	   w.setBlack();
	                   x.getParent().setBlack();
	                   w.getRight().setBlack();
	                   x.getParent().rotateLeft();
	                   x=this.root;
	                   }
	               
	           }
	           else
	           {
	                w=x.getParent().getLeft();
	               if(w.isRed())
	               {
	                   w.setBlack();
	                   x.getParent().setRed();
	                   x.getParent().rotateLeft();
	                   w=x.getParent().getLeft();
	               }
	               if(!w.getRight().isRed() && !w.getLeft().isRed())
	               {
	                   w.setRed();
	                   x=x.getParent();
	               }
	               else {
	                   if(!w.getLeft().isRed())
	                   {
	                       w.getLeft().setBlack();
	                       w.setRed();
	                       w.rotateLeft();
	                       w=x.getParent().getLeft();
	                   }
	                   if(x.getParent().isRed())
	                	   w.setRed();
	                   else
	                	   w.setBlack();
	                   x.getParent().setBlack();
	                   w.getLeft().setBlack();
	                   x.getParent().rotateRight();
	                   x = this.root;
	                   }
	           }
	        }
	        x.setBlack();
	    }
	  ////////////////////////////////////////////////////////////////////////////////////////
	  
	  RedBlackNode<T> minChild(RedBlackNode<T> x) 
	  {
	        while (x.getLeft() != null) 
	        {
	            x = x.getLeft();
	        }
	        return x;
	  }
	  ////////////////////////////////////////////////////////////////////////////////////////
	  
	  public RedBlackNode<T> buscar(T valor, RedBlackNode<T> node)
		{
			if(node != null)
			{
				if(node.getValue().equals(valor) )
				{
					return node;
				}
				else if(valor.compareTo(node.getValue()) == 1)
				{
					if(valor.compareTo(node.getRight().getValue()) == 0)
						return node.getRight();
					else
						return buscar(valor, node.getRight());
				}
				else
				{
					if(node.getLeft().getValue() == node.getValue())
						return node.getLeft();
					else
						return buscar(valor, node.getLeft());
				}
			}
			return null;
		}
		
		public RedBlackNode<T> buscar(T valor)
		{
			if(valor.compareTo(this.root.getValue()) == 0)
			{
				return this.root;
			}
			else
			{
				RedBlackNode<T> noRetorno = buscar(valor, this.root);
				return noRetorno;
			}
		}
	  
}
