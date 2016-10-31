package test;

import datastructures.RadixTree;

public class TestRadix {

	public static void main(String[] args) {
		RadixTree tree = new RadixTree();

		System.out.println("corona");
		tree.insert("corona");
		tree.print();
		System.out.println(new String(new char[10]).replace('\0', '-'));
		System.out.println("coroa");
		tree.insert("coroa");
		tree.print();
		System.out.println(new String(new char[10]).replace('\0', '-'));
		System.out.println("coroação");
		tree.insert("coroação");
		tree.print();
		System.out.println(new String(new char[10]).replace('\0', '-'));
		System.out.println("corante");
		tree.insert("corante");
		tree.print();
		System.out.println(new String(new char[10]).replace('\0', '-'));
		System.out.println("cor");
		tree.insert("cor");
		tree.print();
		System.out.println(new String(new char[10]).replace('\0', '-'));
		System.out.println("coroa");
		tree.insert("coroa");
		tree.print();
		System.out.println(new String(new char[10]).replace('\0', '-'));
		System.out.println("coroação");
		tree.insert("coroação");
		tree.print();
		System.out.println(new String(new char[10]).replace('\0', '-'));
		System.out.println("rafael");
		tree.insert("rafael");
		tree.print();

		System.out.println("coro: " + tree.contains("coro"));
		System.out.println("coroa: " + tree.contains("coroa"));
		System.out.println("coroação: " + tree.contains("coroação"));
		System.out.println("corona: " + tree.contains("corona"));
		System.out.println("cor: " + tree.contains("cor"));
		System.out.println("Corona: " + tree.contains("Corona"));
		System.out.println("andre: " + tree.contains("andre"));
		System.out.println("rafael: " + tree.contains("rafael"));

	}

}
