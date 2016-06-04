/*
 * Data Structures and Algorithms.
 * Copyright (C) 2016 Rafael Guterres Jeffman
 *
 * See the LICENSE file accompanying this source code, for
 * licensing restrictions that might apply.
 *
 */

package util;

import java.util.Comparator;

public class BinaryHeap<T> {
	
	private T[] data;
	private int count;
	private Comparator<T> cmp;
	
	/**
	 * Store the number of operations on the last push
	 */
	public long pushOperations;
	/**
	 * Store the number of operations on the last pop
	 */
	public long popOperations;
	/**
	 * Store the number of operations on the last heapify
	 */
	public long heapifyOperations;
	/**
	 * Creates a new heap, given the comparator to use.
	 */
	@SuppressWarnings("unchecked")
	public BinaryHeap(Comparator<T> cmp) {
		this.data = (T[])new Object[16];
		this.count = 0;
		this.cmp = cmp;
	}

	// Used by the static heapify methods.
	private BinaryHeap() {
		this.data = null;
		this.count = 0;
		this.cmp = null;
	}
	
	/**
	 * Creates a binary heap from existing data. The data should be, after
	 * this call, be owned only by the heap.
	 * @param data The data to be used for the heap.
	 * @return The created BinaryHeap.
	 */
	public static <T extends Comparable<T>> BinaryHeap<T> heapify(T[] data) {
		return heapify(data, new Comparator<T>() {
			@Override
			public int compare(T o1, T o2) {
				return o1.compareTo(o2);
			}
		});
	}
	/**
	 * Creates a binary heap from existing data. The data should be, after
	 * this call, be owned only by the heap.
	 * @param data The data to be used for the heap.
	 * @param cmp The comparator to use to create the heap.
	 * @return The created BinaryHeap.
	 */
	public static <T> BinaryHeap<T> heapify(T[] data, Comparator<T> cmp) {
		BinaryHeap<T> heap = new BinaryHeap<>();
		heap.heapifyOperations = 0;
		heap.data = data;
		heap.count = data.length;
		heap.cmp = cmp;
		// heapify
		int sz = 1;
		while (sz < heap.data.length) {
			int index = sz;
			while (index > 0) {
				int parent = (index-1) / 2;
				heap.heapifyOperations++;
				if (heap.cmp.compare(heap.data[index],heap.data[parent]) < 0) {
					heap.heapifyOperations++;
					T t = heap.data[parent];
					heap.data[parent]= heap.data[index];
					heap.data[index] = t;
				}
				index = parent;
			}
			sz++;
		}
		return heap;
	}

	/**
	 * Query if there are elements on the heap.
	 * @return True if there are elements, false otherwise.
	 */
	public boolean isEmpty() {
		return count == 0;
	}
	
	/**
	 * Return the element at the top of the heap, without removing it.
	 * @return The element at the top of the heap.
	 */
	public T peek() {
		return data[0];
	}
	
	/**
	 * Add a new element to the heap.
	 * @param elem The element to be added to the heap.
	 */
	public void push(T elem) {
		pushOperations = 0;
		ensureEnoughSpace();
		data[count] = elem;
		int e = count;
		count++;
		while (e > 0) {
			int r = (e-1)/2;
			pushOperations++;
			if (cmp.compare(data[e], data[r]) < 0) {
				pushOperations++;
				T t = data[e]; data[e] = data[r]; data[r] = t;
			}
			e = r;
		}
	}
	
	@SuppressWarnings("unchecked")
	private void ensureEnoughSpace() {
		if (count < data.length)
			return;
		int sz = data.length > 1024 ? data.length+1024 : data.length*2;
		Object[] store = new Object[sz];
		System.arraycopy(data,0, store, 0, count);
		data = (T[])store;
	}
	
	/**
	 * Remove the top element of the heap and return it.
	 * @return The top element of the heap, or null, if the heap is empty.
	 */
	public T pop() {
		popOperations = 0;
		if (count == 0)
			return null;
		int r = 0;
		T res = data[r];
		count--;
		data[r] = data[count];
		data[count] = null;
		while (r < count) {
			int e = (r+1)*2-1;
			int d = (r+1)*2;
			if (e >= count) break;
			if (d >= count) d = e;
			popOperations++;
			int s = (cmp.compare(data[e], data[d]) < 0) ? e : d;
			popOperations++;
			if (cmp.compare(data[r], data[s]) > 0) {
				popOperations++;
				T t = data[r]; data[r] = data[s]; data[s] = t;
			}
			r = s;
		}
		return res;
	}

	public int size() {
		return count;
	}
}
