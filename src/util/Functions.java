package util;

public final class Functions {

	public static <T> void swap(T[] array, int a, int b) {
		T aux = array[a];
		array[a] = array[b];
		array[b] = aux;
	}

}
