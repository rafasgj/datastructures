package test;

import algorithms.Sorting;

public class TestSorting {
	public static void main(String...args) {
		Integer[] ordered = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
		Integer[] reversed = {16,15,14,13,12,11,10,9,8,7,6,5,4,3,2,1};
		Integer[] random = {14,8,7,2,3,16,15,10,1,9,12,6,13,4,5,11};
		Integer[] array;

		System.out.println(new String(new char[20]).replace("\0", "-"));
		
		// Insertion sort.
		System.out.println("Insertion Sort");
		System.out.print("Sorting ordered: "); System.out.flush();
		array = ordered.clone();
		System.out.println(Sorting.insertionSort(array));
		printArray(array);
		System.out.print("Sorting reversed: "); System.out.flush();
		array = reversed.clone();
		System.out.println(Sorting.insertionSort(array));
		printArray(array);
		System.out.print("Sorting random: "); System.out.flush();
		array = random.clone();
		System.out.println(Sorting.insertionSort(array));
		printArray(array);
		System.out.println(new String(new char[20]).replace("\0", "-"));
		
		// Selection sort.
		System.out.println("Selection Sort");
		System.out.print("Sorting ordered: "); System.out.flush();
		array = ordered.clone();
		System.out.println(Sorting.selectionSort(array));
		printArray(array);
		System.out.print("Sorting reversed: "); System.out.flush();
		array = reversed.clone();
		System.out.println(Sorting.selectionSort(array));
		printArray(array);
		System.out.print("Sorting random: "); System.out.flush();
		array = random.clone();
		System.out.println(Sorting.selectionSort(array));
		printArray(array);
		System.out.println(new String(new char[20]).replace("\0", "-"));
		
		// Bubble sort.
		System.out.println("Bubble Sort");
		System.out.print("Sorting ordered: "); System.out.flush();
		array = ordered.clone();
		System.out.println(Sorting.bubbleSort(array));
		printArray(array);
		System.out.print("Sorting reversed: "); System.out.flush();
		array = reversed.clone();
		System.out.println(Sorting.bubbleSort(array));
		printArray(array);
		System.out.print("Sorting random: "); System.out.flush();
		array = random.clone();
		System.out.println(Sorting.bubbleSort(array));
		printArray(array);
		System.out.println(new String(new char[20]).replace("\0", "-"));
		
		// Quick sort.
		System.out.println("Quick Sort");
		System.out.print("Sorting ordered: "); System.out.flush();
		array = ordered.clone();
		System.out.println(Sorting.quickSort(array));
		printArray(array);
		System.out.print("Sorting reversed: "); System.out.flush();
		array = reversed.clone();
		System.out.println(Sorting.quickSort(array));
		printArray(array);
		System.out.print("Sorting random: "); System.out.flush();
		array = random.clone();
		System.out.println(Sorting.quickSort(array));
		printArray(array);
		System.out.println(new String(new char[20]).replace("\0", "-"));
	}

	private static <T> void printArray(T[] array) {
		for (T i : array)
			System.out.print(i + " ");
		System.out.println();
	}
}
