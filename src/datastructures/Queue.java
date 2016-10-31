/*
 * Data Structures and Algorithms.
 * Copyright (C) 2016 Rafael Guterres Jeffman
 *
 * See the LICENSE file accompanying this source code, for
 * licensing restrictions that might apply.
 *
 */

package datastructures;

import java.util.NoSuchElementException;

import datastructures.LinkedList;
import interfaces.Iterator;

/**
 * Implements a FIFO protocol for data.
 */
public class Queue<T> {

	LinkedList<T> container = new LinkedList<>();
	
	/**
	 * Add a new value to the end of the queue.
	 * @param value The value to be at the end of the queue.
	 */
	public void push(T value) {
		container.append(value);
	}

	/**
	 * Remove the element at the front of the queue, returning it.
	 * @return The element at the front of the queue.
	 */
	public T pop() {
		if (isEmpty())
			throw new NoSuchElementException("Queue is empty.");
		Iterator<T> iter = container.iterator();
		T data = iter.next();
		iter.remove();
		return data;
	}

	/**
	 * Retrieve the element at the front of the queue, without removing it.
	 * @return The element at the front of the queue, or null if it's empty.
	 */
	public T peek() {
		if (isEmpty())
			return null;
		return container.iterator().next();
	}

	/**
	 * Check if the stack is empty.
	 * @return True if there's no element on the stack, false otherwise.
	 */
	public boolean isEmpty() {
		return container.isEmpty();
	}
}
