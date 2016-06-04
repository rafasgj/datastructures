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
 * Common interface for all iterable data structures. Extends Java's
 * Iterable interface so that it can be used in the 'for-each' statement.
 */
public interface Iterable<T> extends java.lang.Iterable<T> {
	/**
	 * Returns an interface.Iterator object for the data structure.
	 */
	Iterator<T> iterator();
}
