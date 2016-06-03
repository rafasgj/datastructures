package test;

import util.AVLTree;
import util.DuplicateKeyException;

public class TestAVLTree {

	public static void main(String[] args) throws DuplicateKeyException {
		AVLTree<String, Integer> arvore = new AVLTree<>();
		
		String[] nomes = { "Andre", "Ivonei", "Lossurdo", "Lucia", "Rafael"};
		
		for (int i = 0; i < nomes.length; i++)
			arvore.insert(nomes[i], i);
		
		arvore.print();
	}

}
