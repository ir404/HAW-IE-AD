package test;

import sortedBinaryTree.BinarySearchTree;
import sortedBinaryTree.Node;

public class Main {
	public static void main(String[] args) {
		testBinaryTree();
	}

	private static void testBinaryTree() {
		Node thing = new Node(10);
		BinarySearchTree tree = new BinarySearchTree();
		tree.add(new Node(9));
		tree.add(new Node(2));
		tree.add(thing);
		tree.add(new Node(3));
		tree.add(new Node(15));
		tree.add(new Node(19));
		tree.add(new Node(18));
		tree.add(new Node(12));
		tree.add(new Node(13));
		tree.add(new Node(8));
		tree.add(new Node(15));

		System.out.println("Nodes in-order: " + tree);
		System.out.println("Node count: " + tree.getNodeCount());
		System.out.println("Maximum: " + tree.maximum().getValue());
		System.out.println("Minimum: " + tree.minium().getValue());
		System.out.println("Height: " + tree.getHeight());
		System.out.println("Is balanced: " + tree.isBalanced());


		int k = 10;
		boolean found = tree.findValue(k)  != null;
		System.out.println("\nSearching for value " + k + ": " + found);

		System.out.println("Ordered successor of " + k + " is " + tree.orderedSuccessor(thing).getValue());

		BinarySearchTree newTree = new BinarySearchTree();
		newTree.add(new Node(20));
		newTree.add(new Node(5));
		newTree.add(new Node(7));
		newTree.add(new Node(32));
		newTree.add(new Node(22));
		newTree.add(new Node(17));
		newTree.add(new Node(1));

		System.out.println("\n2nd tree nodes in-order: " + newTree);
		tree.merge(newTree);
		System.out.println("1st tree after merging 2nd: " + tree);

		System.out.println("\n\n");
		tree.display();
	}
}
