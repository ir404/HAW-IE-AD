package tree;

import java.util.ArrayList;
import java.util.Iterator;

public class SortedBinaryTree<E> {
	private Node root;
	
	public SortedBinaryTree(){
		this.root=null;
	}
	
	public Node getRoot() {
		return root;
	}
	
	// 1. The Public Method (Entry point)
	public void insert(Node k) {
	    if (this.root == null) {
	        this.root = k;
	        k.setParent(null);
	    } else {
	        // Start recursion: current is root, pre (parent) is null
	        insertMethod(this.root, k, null); 
	    }
	}

	// 2. The Private Helper
	private void insertMethod(Node current, Node k, Node pre) {
	    // BASE CASE: We found the empty spot (current is null)
	    if (current == null) {
	        k.setParent(pre); // Link Up: k points to parent
	        k.setLeft(null); 
            k.setRight(null);
	        // Link Down: parent points to k
	        if (k.getKey() < pre.getKey()) {
	            pre.setLeft(k);
	        } else {
	            pre.setRight(k);
	        }
	        return; // Done
	    }

	    // RECURSIVE STEP: Keep searching down
	    if (k.getKey() < current.getKey()) {
	        // Go Left: current becomes left child, 'pre' becomes current node
	        insertMethod(current.getLeft(), k, current); 
	    } else {
	        // Go Right: current becomes right child, 'pre' becomes current node
	        insertMethod(current.getRight(), k, current);
	    }
	}
	
	public Node min(Node k) {
		if (k == null) {
	        return null; // Handle edge case if a null node is passed
	    }
		
		Node temp=k;
		while(temp.getLeft()!=null) {
			temp=temp.getLeft();	
		}
		return temp; 
	}
	
	public Node succ(Node k) {
		if (k.getRight() != null) {
	        return min(k.getRight());
	    }
		
		Node p = k.getParent();
	    Node child = k;
	    while (p != null && child == p.getRight()) {
	        child = p;       // Move up
	        p = p.getParent(); // Move parent up
	    }
	    return p;
	}
	
	public Iterator<Node> iterator() {
	    return new Iterator<Node>() {
	        
	        // Start at the smallest value in the entire tree
	        private Node current = min(root);

	        @Override
	        public boolean hasNext() {
	            // If current is not null, more nodes to visit
	            return current != null;
	        }

	        @Override
	        public Node next() {
	            // 1. Remember the node we are on right now so we can return it
	            Node nodeToReturn = current;
	            
	            // 2. Move the pointer to the next sorted node (successor)
	            current = succ(current);
	        
	            return nodeToReturn;
	        }
	    };
	}
	
	
	public void printTrees(SortedBinaryTree<E> tree) {
		Iterator<Node> it = tree.iterator();
        while (it.hasNext()) {
            System.out.println(it.next()); 
        }
	}

	public void merge(SortedBinaryTree<Node> tree) {
		ArrayList<Node> nodesToMove = new ArrayList<>();
		Iterator<Node> it = tree.iterator();
		while (it.hasNext()) {
		    nodesToMove.add(it.next());
		}

		for (Node n : nodesToMove) {
		    // Clean the node so it doesn't bring old Tree B connections with it
		    n.setParent(null); 
		    n.setLeft(null); 
		    n.setRight(null);
		    this.insert(n);
		}
	}
}
