package algorithms;

import java.util.Comparator;

import util.BinaryHeap;

public class Sorting {

	private static <T extends Comparable<T>> Comparator<T> less() {
		return new Comparator<T>() {
			@Override
			public int compare(T o1, T o2) {
				return o1.compareTo(o2);
			}
		};
	}
	
	/**
	 * Sort the given data using insertion sort.
	 * @param array The array to be sorted.
	 * @return The number of operations (comparisons and swaps) performed.
	 */
	public static <T extends Comparable<T>> int insertionSort(T[] array) {
		return insertionSort(array, less());
	}

	/**
	 * Sort the given data using insertion sort and the given comparator.
	 * @param array The array to be sorted.
	 * @param cmp The comparator to use.
	 * @return The number of operations (comparisons and swaps) performed.
	 */
	public static <T> int insertionSort(T[] array, Comparator<T> cmp) {
		int i,j,ops = 0;
		for (i = 0; i < array.length-1; i++) {
			for (j = i+1; j > 0; j--) {
				ops++;
				if (cmp.compare(array[j],array[j-1]) < 0) {
					ops++;
					T t = array[j]; array[j] = array[j-1]; array[j-1] = t;
				} else break;
			}
		}
		return ops;
	}

	/**
	 * Sort the given data using selection sort.
	 * @param array The array to be sorted.
	 * @return The number of operations (comparisons and swaps) performed.
	 */
	public static <T extends Comparable<T>> int selectionSort(T[] array) {
		return selectionSort(array, less());
	}

	/**
	 * Sort the given data using selection sort and the given comparator.
	 * @param array The array to be sorted.
	 * @param cmp The comparator to use.
	 * @return The number of operations (comparisons and swaps) performed.
	 */
	public static <T> int selectionSort(T[] array, Comparator<T> cmp) {
		int i,j,ops = 0;
		for (i = 0; i < array.length-1; i++) {
			T min = array[i];
			int n = i;
			for (j = i+1; j < array.length; j++) {
				ops++;
				if (cmp.compare(array[j],min) < 0) {
					min = array[j];
					n = j;
				}
			}
			ops++;
			T t = array[i]; array[i] = array[n]; array[n] = t;
		}
		return ops;
	}

	/**
	 * Sort the given data using bubble sort.
	 * @param array The array to be sorted.
	 * @return The number of operations (comparisons and swaps) performed.
	 */
	public static <T extends Comparable<T>> int bubbleSort(T[] array) {
		return bubbleSort(array, less());
	}

	/**
	 * Sort the given data using bubble sort and the given comparator.
	 * @param array The array to be sorted.
	 * @param cmp The comparator to use.
	 * @return The number of operations (comparisons and swaps) performed.
	 */
	public static <T> int bubbleSort(T[] array, Comparator<T> cmp) {
		int i,j,ops = 0;
		boolean swap = false;
		for (i = 0; i < array.length-1; i++) {
			for (j = i+1; j < array.length; j++) {
				ops++;
				if (cmp.compare(array[i],array[j]) > 0) {
					ops++;
					T t = array[i]; array[i] = array[j]; array[j] = t;
					swap = true;
				}
			}
			if (!swap) break;
		}
		return ops;
	}

	/**
	 * Sort the given data using quick sort.
	 * @param array The array to be sorted.
	 * @return The number of operations (comparisons and swaps) performed.
	 */
	public static <T extends Comparable<T>> int quickSort(T[] array) {
		return quickSort(array, less());
	}

	/**
	 * Sort the given data using quick sort and the given comparator.
	 * @param array The array to be sorted.
	 * @param cmp The comparator to use.
	 * @return The number of operations (comparisons and swaps) performed.
	 */
	public static <T> int quickSort(T[] array, Comparator<T> cmp) {
		return do_quick_sort(array, 0, array.length-1, cmp);
	}

	private static <T>
	int do_quick_sort(T[] array, int s, int e, Comparator<T> cmp)
	{
		int ops = 0;
		int med = (s + e)/2;
		T pivot = Partition.median_of_three(array[s],array[med],array[e],cmp);
		int n = Partition.partition(array, s, e, pivot, cmp);
		ops += Partition.partitionOperations;
		if (n > s+1)
			ops += do_quick_sort(array, s, n-1, cmp);
		if (n < e-1)
			ops += do_quick_sort(array, n+1, e, cmp);
		return ops;
	}
	

	/**
	 * Sort the given data using heap sort.
	 * @param array The array to be sorted.
	 * @return The number of operations (comparisons and swaps) performed.
	 */
	public static <T extends Comparable<T>> int heapSort(T[] array) {
		return heapSort(array, less());
	}

	/**
	 * Sort the given data using heap sort and the given comparator. The
	 * comparator 
	 * @param array The array to be sorted.
	 * @param cmp The comparator to use.
	 * @return The number of operations (comparisons and swaps) performed.
	 */
	public static <T> int heapSort(T[] array, Comparator<T> cmp) {
		int ops = 0;
		cmp = cmp.reversed(); // for a max heap, reverse the comparator.
		BinaryHeap<T> heap = BinaryHeap.heapify(array, cmp);
		ops = heap.heapifyOperations;
		while (!heap.isEmpty()) {
			array[heap.size()-1] = heap.pop();
			ops += heap.popOperations;
		}
		return ops;
	}


	/**
	 * Sort the given data using heap sort.
	 * @param array The array to be sorted.
	 * @return The number of operations (comparisons and swaps) performed.
	 */
	public static <T extends Comparable<T>> int mergeSort(T[] array) {
		return mergeSort(array, less());
	}

	/**
	 * Sort the given data using heap sort and the given comparator. The
	 * comparator 
	 * @param array The array to be sorted.
	 * @param cmp The comparator to use.
	 * @return The number of operations (comparisons and swaps) performed.
	 */
	public static <T> int mergeSort(T[] array, Comparator<T> cmp) {
		boolean swap = false;
		int ops = 0;
		int length = array.length;
		@SuppressWarnings("unchecked")
		T[] temp = (T[]) new Object[length];
		for (int sz = 1; sz < length; sz *= 2) {
			for (int i = 0; i < length; i += 2*sz) {
				int iLeft = i;
				int iRight = Math.min(i + sz, array.length);
				int lim = iRight;
				int iEnd = Math.min(i + 2*sz, array.length);
				for (int j = iLeft; j < iEnd; j++) {
					ops++;
					if (iLeft < lim && (iRight >= iEnd ||
						cmp.compare(array[iLeft],array[iRight])<0))
					{
						temp[j] = array[iLeft++];
					} else {
						temp[j] = array[iRight++];
					}
				}
			}
			ops += length;
			System.arraycopy(temp, 0, array, 0, length);
		}
		return ops;
	}
}
