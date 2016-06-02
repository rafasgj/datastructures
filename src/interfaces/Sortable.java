package interfaces;

import java.util.Comparator;

/**
 * Define an interface for a data structure that provides a sorting
 * method.
 */
public interface Sortable<T> {
	/**
	 * Sorts the data stored in the container using the provided comparator.
	 * @param cmp The comparator used to compare the elements.
	 */
	void sort(Comparator<T> cmp);
}
