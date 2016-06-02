package test;

import static java.lang.System.out;

import util.Hashtable;

public class TestHashtable {
	public static void main(String...strings) throws Exception {
		Hashtable<String, Integer> hash = new Hashtable<>();
		
		String[] nomes = { "Rafael", "Ivonei", "Lossurdo", "Marcela", "Lucia",
				"Gustavo", "Guilherme", "Marcia", "Fabio", "Elmario", "Luciano",
				"Antonio", "Aline", "Marcelo" };
		
		for (int i = 0; i < nomes.length; i++) {
			hash.put(nomes[i],i);
			out.println("Itens: "+hash.size()+"\tLoad: "+hash.loadFactor());
		}
		
		out.println(hash.get("Rafael"));
		out.println(hash.get("Ivonei"));
	}
}