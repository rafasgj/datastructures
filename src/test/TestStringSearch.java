package test;

import algorithms.StringSearch;

public class TestStringSearch {
	public static void main(String[] args) {
		// Should return 18.
		System.out.println(StringSearch.kmp("accgtcacttgaaccggttctag", "tcta"));
		// Should return -1.
		System.out.println(StringSearch.kmp("accgtcacttgaaccggttctag", "z"));
	}
}
