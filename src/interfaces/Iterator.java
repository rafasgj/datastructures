/*
 * Data Structures and Algorithms.
 * Copyright (C) 2016 Rafael Guterres Jeffman
 *
 * See the LICENSE file accompanying this source code, for
 * licensing restrictions that might apply.
 *
 */

package interfaces;

/**
 * Interface for iterators over data structure. It extends Java's Iterator
 * adding an 'insert' method, and requiring the 'remove' method.
 */
public interface Iterator<T> extends java.util.Iterator<T>{
	/**
	 * Insert a new element on the underlying container, using the current
	 * iterator as a base mark. Must be called after the first call to next(),
	 * and tries to insert the value before the current element.
	 */
	void insert(T value);
	/**
	 * Remove the current element from the underlying container.
	 */
	void remove();
	/**
	 * Return the next element.
	 */
	T next();
	/**
	 * Queries if there are any elements left in the container.
	 * @return True if next() will succeed, false otherwise.
	 */
	boolean hasNext();
}
