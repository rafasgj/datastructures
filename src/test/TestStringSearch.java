package test;

import algorithms.StringSearch;

interface SearchAlgorithm {
	int search(String haystack, String needle);
}

public class TestStringSearch {

	private static void testSearch(SearchAlgorithm string, String haystack, String needle, int expected) {
		StringSearch.ops = 0;
		System.out.println("Searching for " + needle + " on " + haystack);
		int observed = string.search(haystack, needle);
		System.out.println("Expected: " + expected + "\tObserved: " + observed);
		System.out.println("Number of operations: " + StringSearch.ops);
	}
	
	public static void main(String[] args) {
		String[] texts = {
			"aabbabaaaaabbabaaaabaaaabbaaaaaaabababbabaaabbaaaaaaaaaab",
			"here is a simple example",
			"accgtcacttgaaccggttctag",
			"accgtcacttgaaccggttctag",
			"accgtcacttgaaccggttctag"
		};
		String[] needles = {
			"aaaaaaaab",
			"example",
			"tcta",
			"zzz",
			"z"
		};
		int [] expected = {
			48,
			17,
			18,
			-1,
			-1
		};
		SearchAlgorithm[] algorithms = {
				(a,b)->{return StringSearch.boyer_moore(a, b);},
				(a,b)->{return StringSearch.kmp(a, b);},
		};
		for (SearchAlgorithm algorithm : algorithms) {
			for (int i = 0; i < texts.length; i++) {
				String haystack = texts[i];
				String needle = needles[i];
				int index = expected[i];
				testSearch(algorithm, haystack, needle, index);
			}
		}
	}
}
