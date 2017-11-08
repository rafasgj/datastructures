
class RBN<T extends Comparable<T>> {

    T value;
    RBN<T> left;
    RBN<T> right;
    RBN<T> parent;
    boolean red;
    
    public RBN(T value) {
        this.value = value;
        this.red = true;
    }

    public RBN<T> getSibling(RBN<T> node) {
        RBN<T> sibling = null;
        
        if (node.getParent().left == node) {
            sibling = node.getParent().right;
        } else if (node.getParent().right == node) {
            sibling = node.getParent().left;
        }
        return sibling;
    }
    
    public RBN<T> insert(T value) throws DuplicateKeyException {
        int cmp = value.compareTo(this.value);
        if (cmp < 0)
            return insertLeft(value);
        else if (cmp > 0)
            return insertRight(value);
        else
            throw new DuplicateKeyException("Already inserted: "+value);
    }
        
    private RBN<T> insertLeft(T value) throws DuplicateKeyException {
        if (left == null) {
            left = new RBN<>(value);
            left.parent = this;
            return left;
        } else
            return left.insert(value);
    }

    private RBN<T> insertRight(T value) throws DuplicateKeyException {
        if (right == null) {
            right = new RBN<>(value);
            right.parent = this;
            return right;
        } else
            return right.insert(value);
    }
    
        public RBN<T> delete(T value){
            int cmp = value.compareTo(this.value);
            System.out.println("Cmp "+cmp);
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
        
        public RBN<T> deleteLeft(T data){
            return this.left.delete(data);
        }
        
        public RBN<T> deleteRight(T data){
            return this.right.delete(data);
        }
        
    public RBN<T> getParent() {
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

    public RBN<T> getUncle() {
        if (parent == null)
            return null;
        RBN<T> G = parent.parent;
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
        RBN<T> N = this;
        RBN<T> P = this.parent;
        RBN<T> R = this.right;
        RBN<T> S = R.left;
        //
        RBN<T> B = S;
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
        RBN<T> N = this;
        RBN<T> P = this.parent;
        RBN<T> L = this.left;
        RBN<T> S = L.right;
        //
        RBN<T> B = S;
        L.right = N;
        N.left = B;
        // parents
        N.parent = L;
        L.parent = P;
        if (B != null)
            B.parent = N;
    }

    public void setRight(RBN<T> node) {
        right = node;
    }

    public void setLeft(RBN<T> node) {
        left = node;
    }
    
}

public class RBT<T extends Comparable<T>>
{
    private RBN<T> root;
    
    public void insert(T data) throws DuplicateKeyException {
            RBN<T> node;
            if (root == null)
        node = root = new RBN<>(data);
            else
        node = root.insert(data);
            insert_case1(node);
    }

    private void insert_case1(RBN<T> node) {
        if (node.getParent() == null) {
            node.setBlack();
            return;
        }
        insert_case2(node);
    }

    private void insert_case2(RBN<T> node) {
        RBN<T> P = node.getParent();
        if (!P.isRed()) return;
        insert_case3(node);
    }

    private void insert_case3(RBN<T> node) {
        RBN<T> P = node.getParent();
        RBN<T> U = node.getUncle();
        RBN<T> G = P.getParent();
        if (P.isRed() && (U != null && U.isRed())) {
            P.setBlack();
            U.setBlack();
            G.setRed();
            insert_case1(G);
        } else
            insert_case4(node);
    }

    private void insert_case4(RBN<T> node) { // P is R, U is B 
        RBN<T> P = node.getParent();
        RBN<T> G = P.getParent();
        RBN<T> N = node;

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

    private void insert_case5(RBN<T> node) { // P is R, U is B 
        RBN<T> P = (RBN<T>)(node.getParent());
        RBN<T> G = (RBN<T>)P.getParent();
        RBN<T> GG = null;
        boolean gl = G.isLeftSon(); 
        if (G.getParent() != null)
            GG = (RBN<T>)(G.getParent());
        
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
            RBN<T> node;
            node = root.delete(data);
            delete_case1(node);
        }
        
        private void delete_case1(RBN<T> node){
            if (node.getParent() != null ) {
                delete_case2(node);
            }
        }
        
        private void delete_case2(RBN<T> node){

            RBN<T> parent = node.getParent();
            RBN<T> sibling = node.getSibling(node);
            
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
        
        public void delete_case3(RBN<T> node) {
            RBN<T> parent = node.getParent();
            RBN<T> sibling = node.getSibling(node);
            
            if ((parent.red == false) && (sibling.red == false) && (sibling.left.red == false) && (sibling.right.red == false)) {
                sibling.red = true;
                delete_case1(parent);
            } else {
                delete_case4(node);
            }
            
        }

        public void delete_case4(RBN<T> node) {
            RBN<T> parent = node.getParent();
            RBN<T> sibling = node.getSibling(node);
            
            if ((parent.red == true) && (sibling.red == false) && (sibling.left.red == false) && (sibling.right.red == false)) {
                sibling.red = true;
                parent.red = false;
            } else {
                delete_case5(node);
            }
            
        }

        public void delete_case5(RBN<T> node) {
            RBN<T> parent = node.getParent();
            RBN<T> sibling = node.getSibling(node);
            
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
        
        public void delete_case6(RBN<T> node) {
            RBN<T> parent = node.getParent();
            RBN<T> sibling = node.getSibling(node);
            
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
        
        private void removeOneChild(RBN<T> node) {
        RBN<T> child = node.right.red ? node.left : node.right;
            
            replace_node(node, child);
            
            if (node.red == false) {
                if(child.red == true) {
                    child.red = false;
                } else {
                    remove_case1(child);
                }
            }
         free(node);
    }
    public void free(RBN<T> node) {
        node.left = null;
        node.right = null;
        node.parent = null;
        node.value = null;
    }

public void replace_node(RBN<T> node, RBN<T> child) {
        
        
    }
            


        public void print() {
        if (root == null)
            System.out.println("Empty tree.");
        else
            root.print();
    }
}


