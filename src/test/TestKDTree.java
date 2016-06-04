package test;

import util.KDData;
import util.KDTree;

class UserKDData extends KDData {
	private String data;

	public UserKDData(double x, double y, String userData) {
		super(x,y);
		this.data = userData;
	}
	
	public String getData() {
		return data;
	}
	
	@Override
	public String toString() {
		return super.toString()+":"+data+" ";
	}
}

public class TestKDTree {

	public static void main(String[] args) {
		int[] x = {10,15, 5, 6, 2, 12, 7};
		int[] y = {1, 5, 5, 2, 12, 4, 8};
		String[] data = {"RS", "SC", "PR", "RJ", "SP", "MG", "ES"};
		KDData[] p = new UserKDData[x.length]; 
		for (int i = 0; i < x.length; i++) {
			p[i] = new UserKDData(x[i],y[i],data[i]);
		}
		
		KDTree kd = new KDTree(p);
		System.out.println(kd);
		
		KDData point;

		System.out.println("\nSearching for 2 points near (3.3)");
		point = new KDData(3,3);
		p = new KDData[2];
		int stats  =  kd.findKNearestPoints(point, p);
		for (KDData n : p) {
			System.out.print(n + " ");
		}
		System.out.println("\nNodes evaluated: " + stats);
		System.out.println("Closest: " + ((UserKDData)p[p.length-1]).getData());
		System.out.println("Distance: " + p[p.length-1].distance(point));
		
		System.out.println("\nSearching for 4 points near (12,4)");
		point = new KDData(12,4);
		p = new KDData[4];		
		stats = kd.findKNearestPoints(point,p);
		for (KDData n : p) {
			System.out.print(n + " ");
		}
		System.out.println("\nNodes evaluated: " + stats);
		System.out.println("Closest: " + ((UserKDData)p[p.length-1]).getData());
		System.out.println("Distance: " + p[p.length-1].distance(point));
	}

}
