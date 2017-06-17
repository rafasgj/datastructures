/*
 * Data Structures and Algorithms.
 * Copyright (C) 2016 Rafael Guterres Jeffman
 *
 * See the LICENSE file accompanying this source code, for
 * licensing restrictions that might apply.
 *
 */

package datastructures;

import java.util.Comparator;

/**
 * <p>This class is the base for all classes used as data
 * for a KDTree. You should extend this base class with
 * your own class.</p>
 * <p>This class uses the Euclidean Distance to compute
 * the distance between two objectes.</p>
 */
public class KDData {
	/**
	 * Provides a comparator for a given KDData object, based
	 * on the dimension it splits on a KDTree.
	 */
	public static class DimensionComparator implements Comparator<KDData>
	{
		int dimension;
		public DimensionComparator(int d) { dimension = d; }
		@Override
		public int compare(KDData o1, KDData o2) {
			return (int)Math.signum(o1.get(dimension) - o2.get(dimension));
		}
	};

	private double[] _data;

	@SafeVarargs
	public KDData(double... data) {
		this._data = data;
	}

	public double get(int dimension) {
		return _data[dimension];
	}

	public int length() {
		return _data.length;
	}

	public double distance(KDData point) {
		double sum = 0.0;
		for (int i = 0; i < _data.length; i++) {
			double x = _data[i] - point._data[i];
			sum += x*x;
		}	
		return Math.sqrt(sum);
	}

	@Override
	public String toString() {
		String res = "[" + _data[0];
		for (int i = 1; i < _data.length; i++)
			res += ", " + _data[i];
		return res + "]";
	}
}
