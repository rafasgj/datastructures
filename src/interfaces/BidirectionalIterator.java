package interfaces;

/**
 * Interface for bidirectional iterators.
 */
public interface BidirectionalIterator<T> extends Iterator<T> {
	/**
	 * Returns True if there is an element before the current one.
	 */
	boolean hasPrevious();
	/**
	 * Return to the previous element.
	 */
	T previous();
	/**
	 * Add a new element to the underlying container, before the
	 * current element.
	 */
	void append(T data);
}
