/*
 * Data Structures and Algorithms.
 * Copyright (C) 2016 Rafael Guterres Jeffman
 *
 * See the LICENSE file accompanying this source code, for
 * licensing restrictions that might apply.
 *
 */

package test;

import datastructures.KDData;
import datastructures.KDTree;

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
		int stats = 0;
		
		int[] x = {  5,  2,  5,  9,  4,  5,  5,  8,  7 };
		int[] y = {  8,  3,  4,  6,  7,  3,  9,  1,  2 };
		//int[] x = { 10, 15,  5,  6,  2, 12,  7,  8,  9 };
		//int[] y = {  1,  5,  5,  2, 12,  4,  8,  1,  7 };
		
		String[] data = {"RS", "SC", "PR", "RJ", "SP", "MG", "ES", "CE", "DF"};
		
		KDData[] p = new UserKDData[x.length]; 
		for (int i = 0; i < x.length; i++) {
			p[i] = new UserKDData(x[i],y[i],data[i]);
		}
		
		for (KDData k : p) {
			System.out.println(k);
		}
		System.out.println("------");
		KDTree kd = new KDTree(p);
		System.out.println(kd);
		
		KDData point;

		System.out.println("\nSearching for point near (8,1)");
		point = new KDData(8,1);
		p = new KDData[1];
		stats  =  kd.findKNearestPoints(point, p);
		for (KDData n : p) {
			System.out.print(n + " ");
		}
		System.out.println("\nNodes evaluated: " + stats);
		System.out.println("Closest: " + ((UserKDData)p[0]).getData());
		System.out.println("Distance: " + p[0].distance(point));
		System.out.println("------");

		System.out.println("\nSearching for 2 points near (3,3)");
		point = new KDData(3,3);
		p = new KDData[2];
		stats  =  kd.findKNearestPoints(point, p);
		System.out.println("\nNodes evaluated: " + stats);
		System.out.println("Closest: " + ((UserKDData)p[0]).getData());
		System.out.println("Distance: " + p[0].distance(point));
		System.out.println("------");

		for (KDData n : p) {
			System.out.print(n + " ");
		}
		System.out.println("\nNodes evaluated: " + stats);
		System.out.println("Closest: " + ((UserKDData)p[0]).getData());
		System.out.println("Distance: " + p[0].distance(point));
		
		System.out.println("\nSearching for 4 points near (12,4)");
		point = new KDData(12,4);
		p = new KDData[4];		
		stats = kd.findKNearestPoints(point,p);
		for (KDData n : p) {
			System.out.print(n + " ");
		}
		System.out.println("\nNodes evaluated: " + stats);
		System.out.println("Closest: " + ((UserKDData)p[0]).getData());
		System.out.println("Distance: " + p[0].distance(point));
		System.out.println("------");
	}

}
