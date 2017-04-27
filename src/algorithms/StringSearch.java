package algorithms;

public class StringSearch {

	/**
	 * Searches for a substring in a string using Knuth-Morris-Pratt
	 * string search algorithm.
	 * @param data The string to be searched.
	 * @param needle The substring to search.
	 * @return The index where the substring is found, or -1 if it is
	 * not found in the string.
	 */
	public static int kmp(String data, String needle) {
		// Ensure that input has proper data.
		if (needle.length() < 1)
			return -1;
		// KMP must not be used with needles of length 1.
		// Fallback to linear search.
		if (needle.length() == 1) {
			char c = needle.charAt(0);
			char[] letters = data.toCharArray();
			for (int i = 0; i < letters.length; i++)
				if (letters[i] == c) return i;
			return -1;
		}
		
		// Executes the KMP algorithm.
		int m = 0, i = 0;
		int[] t = kmp_table(needle);
		
		while (m + i < data.length()) {
			if (needle.charAt(i) == data.charAt(m+i)) {
				if (i == needle.length() - 1)
					return m;
				i++;
			} else if (t[i] > -1) {
				m += i - t[i];
				i = t[i];
			} else {
				m++;
				i = 0;
			}
		}
		return -1;
	}
	
	/*
	 * Build a fail table for the KMP algorithm.
	 */
	private static int[] kmp_table(String needle) {
		int[] table = new int[needle.length()];
		table[0] = -1;
		table[1] = 0;
		
		int pos = 2;
		int candidate = 0;
		
		while (pos < needle.length()) {
			if (needle.charAt(pos-1) == needle.charAt(candidate)) {
				candidate++;
				table[pos] = candidate;
				pos++;
			} else if (candidate > 0) {
				candidate = table[candidate];
			} else {
				table[pos] = 0;
				pos++;
			}
		}
		
		return table;
	}
}
