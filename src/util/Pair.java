package util;

/**
 * Hold a pair of two values. This class is immutable, but
 * the stored objects might be modified.
 *
 * @param <T> The type of the first value.
 * @param <U> The type of the second value.
 */
public class Pair<T, U> {
	/**
	 * Access the first value.
	 */
	public final T first;
	/**
	 * Access the second value.
	 */
	public final U second;
	/**
	 * Creates a new Pair object.
	 * @param first The first value to be stored.
	 * @param second The second value to be stored.
	 */
	public Pair(T first, U second) {
		this.first = first;
		this.second = second;
	}
	
	@Override
	public String toString() {
		return String.format("first: %s\nsecond: %s",first,second);
	}
}
