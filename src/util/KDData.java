package util;

public class KDData {
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

	public double squaredDistance(KDData point) {
		double sum = 0.0;
		for (int i = 0; i < _data.length; i++) {
			double x = _data[i] - point._data[i];
			sum += x*x;
		}	
		return sum;
	}

	public double distance(KDData point) {
		return Math.sqrt(squaredDistance(point));
	}

	@Override
	public String toString() {
		String res = "[" + _data[0];
		for (int i = 1; i < _data.length; i++)
			res += ", " + _data[i];
		return res + "]";
	}
}
