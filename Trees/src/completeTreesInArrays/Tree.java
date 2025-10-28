package completeTreesInArrays;

public class Tree {
	public final int DOES_NOT_EXIST = -1;
	
	private Node[] nodes;
	private int length;
	
	public Tree(int length) {
		this.length = length;
		nodes = new Node[length];
		for (int i = 0; i < length; ++i) {
			nodes[i] = new Node(i);
		}
	}

	public int parent(int k) {
		int parentIndex = -1;
		if (k < length) {
			parentIndex = ((int) Math.ceil(k / 2.0)) - 1;
		}
		return parentIndex;
	}
	
	public int left(int k) {
		int leftIx = 2 * k + 1;
		if (leftIx >= nodes.length) leftIx = -1;
		return leftIx;
	}
	
	public int right(int k) {
		int rightIx = 2 * k + 2;
		if (rightIx >= nodes.length) rightIx = -1; 
		return rightIx;
	}
	
	public int getLength() {
		return length;
	}
	
	// this linear search traverses the tree by level-order 
	public boolean searchFor(int k) {
		boolean found = false;
		int i = 0;
		while (!found && i < nodes.length) {
			found = nodes[i].getValue() == k;
			i++;
		}
		return found;
	}
}
