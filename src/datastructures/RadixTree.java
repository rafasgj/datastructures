package datastructures;

import java.util.Locale;

import datastructures.LinkedList;

class RadixNode {
	private String data;
	private LinkedList<RadixNode> children;
	private Locale locale;
	
	public RadixNode(String data, Locale locale) {
		if (locale == null)
			locale = Locale.getDefault();
		this.locale = locale;
		this.data = data.toLowerCase(locale);
		this.children = new LinkedList<>();
	}
	
	public RadixNode(String data) {
		this(data,Locale.getDefault());
	}
	
	public int size() {
		return data.length();
	}
	
	public int indexDiffer(RadixNode cmp) {
		int min = Math.min(cmp.data.length(), data.length());
		for (int i = 0 ; i < min; i++)
			if (cmp.data.charAt(i) != data.charAt(i))
				return i;
		return min;
	}

	private void addEmpty(RadixNode node) {
		if (node.data.isEmpty() && !children.isEmpty()) {
			for (RadixNode n : children)
				if (n.data.isEmpty()) return;
			children.append(new RadixNode("",locale));
		}
	}
	
	private void addNode(RadixNode node, int i) {
		if (i < data.length()) {
			RadixNode n = new RadixNode(data.substring(i),locale);
			n.children = children;
			setData(data.substring(0, i));
			children = new LinkedList<>();
			children.append(n);
		} else
			addEmpty(node);
	}
	
	private void appendChild(RadixNode node) {
		if (node.data.isEmpty())
			return;
		if (children.isEmpty() && !data.isEmpty())
			children.append(new RadixNode("",locale));
		else
			for (RadixNode n : children) {
				int i = n.indexDiffer(node);
				if (i > 0) {
					n.insert(node);
					return;
				}
			}
		children.append(node);
	}
	
	public void insert(RadixNode node) {
		// consume this node.
		int i = indexDiffer(node);
		node.setData(node.data.substring(i));
		// test if node need to be splited...
		if (i > 0 && i <= data.length()) {
			// split node
			addNode(node, i);
		}
		// append remaining data
		appendChild(node);
	}
	
	private void setData(String data) {
		this.data = data.toLowerCase(locale);
	}
	
	public String getData() {
		return data;
	}
	
	void print(int l) {
		if (l > 0) {
			System.out.print(new String(new char[l-1]).replace("\0","  "));
			System.out.println("["+data+"]");
		}
		for (RadixNode n : children)
			n.print(l+1);
	}

	public boolean contains(RadixNode needle) {
		// consume this node.
		int i = indexDiffer(needle);
		needle.setData(needle.data.substring(i));
		if (needle.data.isEmpty())
			return needleConsumed();
		for (RadixNode n : children) {
			i = n.indexDiffer(needle);
			if (i > 0)
				return n.contains(needle);
		}
		return false;
	}

	private boolean needleConsumed() {
		if (children.isEmpty())
			return true;
		for (RadixNode n : children)
			if (n.data.isEmpty())
				return true;
		return false;
	}
}

public class RadixTree {
	private Locale locale;
	private RadixNode root;
	
	public RadixTree() {
		root = new RadixNode("");
	}
	
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	
	public void insert(String needle) {
		root.insert(new RadixNode(needle,locale));
	}
	
	public void print() {
		root.print(0);
	}

	public boolean contains(String needle) {
		return root.contains(new RadixNode(needle));
	}
}
