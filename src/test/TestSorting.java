package test;

import algorithms.Sorting;

public class TestSorting {
	public static void main(String...args) {
		Integer[] ordered = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
		Integer[] reversed = {16,15,14,13,12,11,10,9,8,7,6,5,4,3,2,1};
		Integer[] random = {14,14,8,7,2,3,16,15,10,1,9,12,6,13,4,5,11,17,17,14};
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
		
		// Heap sort.
		System.out.println("Heap Sort");
		System.out.print("Sorting ordered: "); System.out.flush();
		array = ordered.clone();
		System.out.println(Sorting.heapSort(array));
		printArray(array);
		System.out.print("Sorting reversed: "); System.out.flush();
		array = reversed.clone();
		System.out.println(Sorting.heapSort(array));
		printArray(array);
		System.out.print("Sorting random: "); System.out.flush();
		array = random.clone();
		System.out.println(Sorting.heapSort(array));
		printArray(array);
		System.out.println(new String(new char[20]).replace("\0", "-"));
		
		// Merge sort.
		System.out.println("Merge Sort");
		System.out.print("Sorting ordered: "); System.out.flush();
		array = ordered.clone();
		System.out.println(Sorting.mergeSort(array));
		printArray(array);
		System.out.print("Sorting reversed: "); System.out.flush();
		array = reversed.clone();
		System.out.println(Sorting.mergeSort(array));
		printArray(array);
		System.out.print("Sorting random: "); System.out.flush();
		array = random.clone();
		System.out.println(Sorting.mergeSort(array));
		printArray(array);
		System.out.println(new String(new char[20]).replace("\0", "-"));
		
		// Intro sort.
		System.out.println("IntroSort");
		System.out.print("Sorting ordered: "); System.out.flush();
		array = ordered.clone();
		System.out.println(Sorting.introSort(array));
		printArray(array);
		System.out.print("Sorting reversed: "); System.out.flush();
		array = reversed.clone();
		System.out.println(Sorting.introSort(array));
		printArray(array);
		System.out.print("Sorting random: "); System.out.flush();
		array = random.clone();
		System.out.println(Sorting.introSort(array));
		printArray(array);
		System.out.println(new String(new char[20]).replace("\0", "-"));
		
		// Large test.
		long s, e;
		int elements = 64; // K elements.

		System.out.println("Large, "+elements+"K elements, random sort.");
		System.out.println("Creating array.");
		random = new Integer[elements*1024]; 
		for (int i = 0; i < elements*1024; i++)
			random[i] = (int)(Math.random()*(elements*1024));
		
		System.out.println("\nInsertion Sort");
		System.out.println("Cloning array.");
		array = random.clone();
		System.out.println("Sorting...");
		s = System.currentTimeMillis();
		Sorting.insertionSort(array);
		e = System.currentTimeMillis();
		System.out.println("Time: " + ((e-s)/1000) + "s.");
		
		System.out.println("\nBubble Sort");
		System.out.println("Cloning array.");
		array = random.clone();
		System.out.println("Sorting...");
		s = System.currentTimeMillis();
		Sorting.bubbleSort(array);
		e = System.currentTimeMillis();
		System.out.println("Time: " + ((e-s)/1000) + "s.");
		
		System.out.println("\nSelection Sort");
		System.out.println("Cloning array.");
		array = random.clone();
		System.out.println("Sorting...");
		s = System.currentTimeMillis();
		Sorting.selectionSort(array);
		e = System.currentTimeMillis();
		System.out.println("Time: " + ((e-s)/1000) + "s.");
		
		System.out.println("\nMerge Sort");
		System.out.println("Cloning array.");
		array = random.clone();
		System.out.println("Sorting...");
		s = System.currentTimeMillis();
		Sorting.mergeSort(array);
		e = System.currentTimeMillis();
		System.out.println("Time: " + ((e-s)/1000) + "s.");
		
		System.out.println("\nHeap Sort");
		System.out.println("Cloning array.");
		array = random.clone();
		System.out.println("Sorting...");
		s = System.currentTimeMillis();
		Sorting.heapSort(array);
		e = System.currentTimeMillis();
		System.out.println("Time: " + ((e-s)/1000) + "s.");
		
		System.out.println("\nQuick Sort");
		System.out.println("Cloning array.");
		array = random.clone();
		System.out.println("Sorting...");
		s = System.currentTimeMillis();
		Sorting.quickSort(array);
		e = System.currentTimeMillis();
		System.out.println("Time: " + ((e-s)/1000) + "s.");
		
		System.out.println("\nIntro Sort");
		System.out.println("Cloning array.");
		array = random.clone();
		System.out.println("Sorting...");
		s = System.currentTimeMillis();
		Sorting.introSort(array);
		e = System.currentTimeMillis();
		System.out.println("Time: " + ((e-s)/1000) + "s.");

		System.out.println(new String(new char[20]).replace("\0", "-"));

		// Really Large test.
		elements = 8; // M elements.
		System.out.println("Huge, "+elements+"M elements, random sort.");
		System.out.println("Creating array.");
		random = new Integer[elements*1024*1024]; 
		for (int i = 0; i < elements*1024*1024; i++)
			random[i] = (int)(Math.random()*(elements*1024*1024));

		System.out.println("\nMerge Sort");
		System.out.println("Cloning array.");
		array = random.clone();
		System.out.println("Sorting...");
		s = System.currentTimeMillis();
		Sorting.mergeSort(array);
		e = System.currentTimeMillis();
		System.out.println("Time: " + ((e-s)/1000) + "s.");
		
		System.out.println("\nHeap Sort");
		System.out.println("Cloning array.");
		array = random.clone();
		System.out.println("Sorting...");
		s = System.currentTimeMillis();
		Sorting.heapSort(array);
		e = System.currentTimeMillis();
		System.out.println("Time: " + ((e-s)/1000) + "s.");
		
		System.out.println("\nQuick Sort");
		System.out.println("Cloning array.");
		array = random.clone();
		System.out.println("Sorting...");
		s = System.currentTimeMillis();
		Sorting.quickSort(array);
		e = System.currentTimeMillis();
		System.out.println("Time: " + ((e-s)/1000) + "s.");
		
		System.out.println("\nIntro Sort");
		System.out.println("Cloning array.");
		array = random.clone();
		System.out.println("Sorting...");
		s = System.currentTimeMillis();
		Sorting.introSort(array);
		e = System.currentTimeMillis();
		System.out.println("Time: " + ((e-s)/1000) + "s.");
	}

	private static <T> void printArray(T[] array) {
		for (T i : array)
			System.out.print(i + " ");
		System.out.println();
	}
}
