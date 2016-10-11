/*
 * Data Structures and Algorithms.
 * Copyright (C) 2016 Rafael Guterres Jeffman
 *
 * See the LICENSE file accompanying this source code, for
 * licensing restrictions that might apply.
 *
 */

package test;

import static java.lang.System.out;

import util.Hashtable;
import util.RobinHoodHash;

public class TestHashtable {
	public static void main(String...strings) throws Exception {
		String[] nomes = { "Rafael", "Ivonei", "Lossurdo", "Marcela", "Lucia",
				"Gustavo", "Guilherme", "Marcia", "Fabio", "Elmario", "Luciano",
				"Antonio", "Aline", "Marcelo" };
		String[] testNames = {"Rafael", "Ivonei", "Guilherme", "Antonio", "Aline",
				"Inexistente"};
		
		//Hashtable<String, Integer> hash = new Hashtable<>();
		
		RobinHoodHash<String, Integer> hash = new RobinHoodHash<>();
		
		
		for (int i = 0; i < nomes.length; i++) {
			hash.put(nomes[i],i);
			out.println("Itens: "+hash.size()+"\tLoad: "+hash.loadFactor());
			if (i == 9) {
				for (String tst : testNames) {
					out.println(tst + " retrieves " + hash.get(tst) );
				}
			}
			hash.print();
		}
		for (String tst : testNames) {
			out.println(tst + " retrieves " + hash.get(tst) );
		}
	}
}