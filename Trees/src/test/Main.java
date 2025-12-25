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
		tree.add(new Node(1));
		tree.add(new Node(12));
		tree.add(new Node(13));
		tree.add(new Node(8));
		tree.add(new Node(6));

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
		System.out.println("\n\n");

		tree.display();
	}
}
