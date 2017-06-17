package algorithms;

public class StringSearch {

	public static int ops = 0;
	
	/**
	 * Searches for a substring in a string using Knuth-Morris-Pratt
	 * string search algorithm.
	 * @param haystack The string to be searched.
	 * @param needle The substring to search.
	 * @return The index where the substring is found, or -1 if it is
	 * not found in the string.
	 */
	public static int kmp(String haystack, String needle) {
		// Ensure that input has proper data.
		if (needle.length() < 1)
			return -1;
		// KMP must not be used with needles of length 1.
		// Fallback to linear search.
		if (needle.length() == 1) {
			char c = needle.charAt(0);
			char[] letters = haystack.toCharArray();
			for (int i = 0; i < letters.length; i++) {
				ops++;
				if (letters[i] == c) return i;
			}
			return -1;
		}
		
		// Executes the KMP algorithm.
		int m = 0, i = 0;
		int[] t = kmp_table(needle);
		
		while (m + i < haystack.length()) {
			ops++;
			if (needle.charAt(i) == haystack.charAt(m+i)) {
				if (i == needle.length() - 1) {
					return m;
				}
				i++;
			} else if (t[i] > -1) {
				ops++;
				m += i - t[i];
				i = t[i];
			} else {
				ops++;
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
			ops++;
			if (needle.charAt(pos-1) == needle.charAt(candidate)) {
				candidate++;
				table[pos] = candidate;
				pos++;
			} else if (candidate > 0) {
				ops++;
				candidate = table[candidate];
			} else {
				ops++;
				table[pos] = 0;
				pos++;
			}
		}
		
		return table;
	}
	
	/**
	 * Searches for a substring in a string using Boyer-Moore
	 * string search algorithm.
	 * @param haystack The string to be searched.
	 * @param needle The substring to search.
	 * @return The index where the substring is found, or -1 if it is
	 * not found in the string.
	 */
	public static int boyer_moore(String haystack, String needle) {
		// Ensure that input has proper data.
		if (needle.length() < 1)
			return -1;
		// Boyer_moore must not be used with needles of length 1.
		// Fallback to linear search.
		if (needle.length() == 1) {
			char c = needle.charAt(0);
			for (int i = 0; i < haystack.length(); i++) {
				ops++;
				if (haystack.charAt(i) == c) return i;
			}
			return -1;
		}

		int[] delta1 = boyer_moore_delta1(needle);
		int[] delta2 = boyer_moore_delta2(needle);
		
		int stringlen = haystack.length();
		int patlen = needle.length();

		int j = 0, i;
		
		while (j < stringlen - patlen) {
			for (i = patlen-1; i >= 0 && haystack.charAt(i+j) == needle.charAt(i); i++) {}
			if (i < 0) {
				return i;
				// j += delta1[(int)needle.charAt(0)];
			} else {
				j += Math.max(delta1[(int)needle.charAt(i)], delta2[j]);
			}
		}
		/*
		
		int i = needle.length()-1;
		
		while (i < stringlen) {
			int j = patlen - 1;
			ops++;
			char c = haystack.charAt(i);
			char b = needle.charAt(j);
			while (c == b) {
				if (j == 0) {
					return i;
					// i += delta1[(int)c];
				}
				j--;
				i--;
			}
			ops++;
			i += Math.max(delta1[(int)c], delta2[j]);
		}
		*/
		return -1;
	}

	/*
	 * Pre-compute the Delta_1 table for boyer-moore. This table
	 * has enough storage for each possible character (in Java,
	 * 2^16 characters, using UTF-16).
	 * @param The needle used.
	 * @return The shift table Delta_1.
	 */
	private static int[] boyer_moore_delta2(String needle) {
		int patlen = needle.length();
		int[] table = new int[patlen];
		for (int i = 0; i < table.length; i++) {
			table[i] = 0;
		}
		return table;
	}

	/*
	 * Pre-compute the Delta_1 table for boyer-moore. This table
	 * has the same size as the needle used..
	 * @param The needle used.
	 * @return The shift table Delta_1.
	 */
	private static int[] boyer_moore_delta1(String needle) {
		int patlen = needle.length();
		int[] table = new int[Character.MAX_VALUE + 1];
		for (int i = 0; i < patlen; i++) {
			table[i] = patlen;
		}
		int i = 0;
		for (char c : needle.toCharArray()) {
			ops++;
			table[(int)c] = patlen - i - 1;
			System.out.println(c + ": " + table[(int)c]);
			i++;
		}
		return table;
	}
}
