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
import algorithms.Partition;
import static util.Functions.swap;

/**
 * <p>Implements a KD Tree, with nearest neighbor search.</p>
 * @param <T>
 */
public class KDTree {

	private static class HeapStorage {
		public KDNode node;
		public double distance;
		private HeapStorage() {}
		public static HeapStorage instance(KDNode n, double d) {
			HeapStorage data = new HeapStorage();
			data.node = n;
			data.distance = d;
			return data;
		}
		public static final Comparator<HeapStorage> distcmp =
				new Comparator<HeapStorage>() {
					@Override
					public int compare(HeapStorage o1, HeapStorage o2) {
						return (int)Math.signum(o2.distance - o1.distance);
					}
				};
	}

	
	/**
	 * <p>Implements a node of a KDTree.</p>
	 */
	private static class KDNode {

		/**
		 * <p>Create a KDTree node and its subtree, given a
		 * list of points.</p>
		 * <p>Only a part of the data given can be used, and is
		 * defined by the start (s) and ending (e) points.</p>
		 * @param points The list of data points.
		 * @param s The first point to be used to create the tree.
		 * @param e The last point to be used to create the tree.
		 * @param d The dimension of the data to be compared for
		 * the node being created.
		 * @return The node that is the root of the KDTree created.
		 */
		public static KDNode createNode(KDData[] points, int s, int e, int d)
		{
			if (s > e)
				return null;
			int k = points[0].length();
			int dim = d % k;
			int m = (s+e)/2;
			KDData.DimensionComparator cmp = new KDData.DimensionComparator(dim);
			Partition.nth_element(points,s,e, m, cmp);
			for (int i = m+1; i <= e; i++) {
				if (cmp.compare(points[i],points[m]) == 0) {
					swap(points,m+1,i);
					m++;
				}
			}
			KDNode node = new KDNode(points[m], d);
			node.left = createNode(points, s, m-1, (d+1) % k);
			node.right = createNode(points, m+1, e, (d+1) % k);
			return node;
		}
		
		KDData data;
		KDNode left;
		KDNode right;
		int dimension;

		public static int nodesEvaluated; 
		
		@Override
		public String toString() {
			String res = "(" + data.toString();
			if (left == null)
				res += " _";
			else
				res += left.toString();
			if (right == null)
				res += " _";
			else
				res += right.toString();
			return res + ")";
		}
		
		private KDNode(KDData data, int dimension) {
			this.data = data;
			this.dimension = dimension;
		}
		
		/**
		 * <p>Search for the K nearest points to a given point.</p>
		 * @param point The point which is the center of the search radius.
		 * @param k The number of points to search for.
		 * @return At most, K KDData objects with the point data set.
		 */
		public void findKNearestPoints(KDData point, KDData[] result)
		{
			BinaryHeap<HeapStorage> heap = new BinaryHeap<>(HeapStorage.distcmp);		
			neighbor_search(point, heap, result.length);
			int last = result.length-1;
			while (!heap.isEmpty()) {
				result[last--] = heap.pop().node.data;
			}
		}
		
		/**
		 * <p>Add this data point to the k-nearest neighbor heap if
		 * it is a better answer.</p>
		 * @param found The answer heap.
		 * @param dist The current point distance.
		 * @param k The number of neighbors to search.
		 */
		private void addToHeap(BinaryHeap<HeapStorage> found, double dist, int k)
		{
			HeapStorage elem = HeapStorage.instance(this, dist);
			if (found.size() < k) {
				found.push(elem);
				return;
			}
			HeapStorage hs = found.peek();
			if (hs.distance > dist) {
				found.pop();
				found.push(elem);
			}
		}
		
		/**
		 * <p>Recursively search for the k-nearest neighbors in the subtree.</p>
		 * @param point The reference point.
		 * @param found A max binary heap with the current closest point, at
		 * most, k points.
		 * @param k The number of near points to find.
		 */
		private void
		neighbor_search(KDData point, BinaryHeap<HeapStorage> found, int k)
		{
			nodesEvaluated++;
			int axis = dimension;
			double v = point.get(axis);
			boolean left_first = true;

			double dist = data.distance(point);

			if (!(left == null && right == null)) {
				if (v <= data.get(axis) && left != null)
				{
					left.neighbor_search(point, found, k);
				} else if (right != null) {
					right.neighbor_search(point, found, k);
					left_first = false;
				}
			} else {
				addToHeap(found, dist, k);
				return;
			}
			
			double dt = Math.abs(v - point.get(axis));
			if (dt <= found.peek().distance) {
				if (left_first && right != null) {
					right.neighbor_search(point, found, k);
				} else if (left != null) {
					left.neighbor_search(point, found, k);
				}
			}
			// add point to heap, if it is a better answer.
			addToHeap(found, dist, k);
		}
	}

	
	// Hold the root of the tree.
	private KDNode root;
	
	/**
	 * <p>Initializes a new KD tree with the given data.</p>
	 * @param points The point data.
	 */
	public KDTree(KDData[] points) {
		root = KDNode.createNode(points, 0, points.length-1, 0);
	}

	/**
	 * <p>Searches for the K closest nearest points.</p>
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

	// Implemented for debug/demonstration purposes.
	@Override
	public String toString() {
		if (root == null) return null;
		return root.toString();
	}
	
}
