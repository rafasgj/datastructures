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

import algorithms.Partition;

class HeapStorage {
	public KDNode node;
	public double distance;
	private HeapStorage() {}
	public static HeapStorage instance(KDNode n, double d) {
		HeapStorage data = new HeapStorage();
		data.node = n;
		data.distance = d;
		return data;
	}
}

class KDNode {
	KDData data;
	KDNode left;
	KDNode right;
	KDNode parent;
	int dimension;

	public static int nodesEvaluated; 
	
	private final Comparator<KDData> datacmp =
			new Comparator<KDData>() {
				@Override
				public int compare(KDData o1, KDData o2) {
					double x = o1.get(dimension) - (o2.get(dimension));
					if (x < 0) return +1;
					if (x > 0) return -1;		
					return 0;
				}
			};
	
	private final Comparator<HeapStorage> distcmp =
			new Comparator<HeapStorage>() {
				@Override
				public int compare(HeapStorage o1, HeapStorage o2) {
					double x = o1.distance - o2.distance;
					if (x < 0) return +1;
					if (x > 0) return -1;		
					return 0;
				}
			};

	@Override
	public String toString() {
		String res = "(" + data;
		if (left == null)
			res += " _";
		else
			res += left;
		if (right == null)
			res += " _";
		else
			res += right;
		return res + ")";
	}
	

	KDNode(KDData data) {
		this.data = data;
	}
	
	KDNode(KDData[] points, int s, int e, int d) {
		this.dimension = d;
		int m = (s+e)/2;
		Partition.nth_element(points, s, e, m, datacmp);
		this.data = points[m];
		if (m-s > 1)
			left = new KDNode(points, s, m-1, d+1 % this.data.length());
		else if (m-s > 0)
			left = new KDNode(points[s]);
		if (e-m > 1)
			right = new KDNode(points, m+1, e, d+1 % this.data.length());
		else if (m-s > 0)
			right = new KDNode(points[e]);
		
		if (left != null) left.parent = this;
		if (right != null) right.parent = this;
	}

	/**
	 * Search for the K nearest points to a given point.
	 * @param point The point which is the center of the search radius.
	 * @param k The number of points to search for.
	 * @return At most, K KDData objects with the point data set.
	 */
	public void findKNearestPoints(KDData point, KDData[] result)
	{
		BinaryHeap<HeapStorage> heap = new BinaryHeap<>(distcmp);		
		neighbor_search(point, heap, result.length);
		int last = 0;
		while (!heap.isEmpty()) {
			HeapStorage st = heap.pop();
			result[last++] = st.node.data;
		}
	}
	
	private void
	neighbor_search(KDData point, BinaryHeap<HeapStorage> found, int k)
	{
		KDNode next = null;
		if (data.get(dimension) < point.get(dimension)) {
			if (left != null)
				left.neighbor_search(point, found, k);
			next = right;
		} else if (right != null) {
			right.neighbor_search(point, found, k);
			next = left;
		}

		double dist = data.squaredDistance(point);
		nodesEvaluated++;
		/* //DEBUG
		System.out.print("Evaluating: "); data.print();
		System.out.print("\tDistance: " + dist);
		System.out.println();
		*/
		if (found.size() < k) {
			HeapStorage elem = HeapStorage.instance(this, dist);
			found.push(elem);
		} else {
			if (dist < found.peek().distance) {
				found.pop();
				HeapStorage elem = HeapStorage.instance(this, dist);
				found.push(elem);
			}
		}

		double best = found.peek().distance;
		double h = point.get(dimension) - data.get(dimension);
		h *= h;
		
		//System.out.println("Hyper: " + h + "\tBest: " + best);
		if ((found.size() < k || h < best) && next != null)
			next.neighbor_search(point, found, k);
	}
}

/**
 * Implements a KD Tree, with nearest neighbor search.
 * @param <T>
 */
public class KDTree {
	private KDNode root;
	
	/**
	 * Initializes a new KD tree with the given data.
	 * @param points The point data.
	 */
	public KDTree(KDData[] points) {
		root = new KDNode(points, 0, points.length-1, 0);
	}

	/**
	 * Searches for the K closest nearest points.
	 * @param point The base point.
	 * @param result An array with K slots to hold the result.
	 * @return The number of nodes evaluated. (For statistics.)
	 */
	public int findKNearestPoints(KDData point, KDData[] result) {
		if (root == null) return 0;
		KDNode.nodesEvaluated = 0;
		root.findKNearestPoints(point, result);
		return KDNode.nodesEvaluated;
	}

	@Override
	public String toString() {
		if (root == null) return null;
		return root.toString();
	}
	
}
