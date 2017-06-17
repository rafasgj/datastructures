package util;

/**
 * This class provides general functions.
 */
public final class Functions {
	/**
	 * Swap two elements inside an array.
	 * @param array The data array.
	 * @param a The index of the first element.
	 * @param b The index of the second element.
	 */
	public static <T> void swap(T[] array, int a, int b) {
		T aux = array[a];
		array[a] = array[b];
		array[b] = aux;
	}

}
