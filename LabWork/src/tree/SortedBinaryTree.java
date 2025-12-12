package tree;

import java.util.Iterator;

public class SortedBinaryTree<E> implements Iterable<Node> {
    private Node root;

    public SortedBinaryTree() {
        this.root = null;
    }
    
    public int getHeight() {
    	return height(root);
    }
    
    public boolean isBalanced() {
    	return balanced(root);
    }

    public void insert(Node k) {
        if (root == null) {
            root = k;
            k.setParent(null);
        } else {
            recursiveInsert(root, k, null);
        }
    }
    
    public Node find(int key) {
    	Node result = null;
    	Node temp;
    	boolean found = false;
    	Iterator<Node> iterator = iterator();
    	while (!found && iterator.hasNext()) {
    		temp = iterator.next();
    		if (temp.getKey() == key) {
    			found = true;
    			result = temp;
    		}
    	}
    	return result;
    }

    // returns the node with the smallest key in the (sub-)tree that starts at k
    public Node min(Node k) {
        Node temp = k;
        if (temp != null) {
            while (temp.getLeft() != null) {
                temp = temp.getLeft();
            }
        }
        return temp;
    }

    // returns the ordered successor of node k
    public Node succ(Node k) {
        Node successor;
        if (k.getRight() != null) {
            successor = min(k.getRight());
        }
        else {
            Node predecessor = k.getParent();
            while (predecessor != null && predecessor.getKey() <= k.getKey()) {
                predecessor = predecessor.getParent();
            }
            successor = predecessor;
        }
        return successor;
    }

    // enables in-order traversal of the tree
    @Override
    public Iterator<Node> iterator() {
        return new Iterator<Node>() {
            private Node current = min(root);       // start at the smallest value in the entire tree

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Node next() {
                Node nodeToReturn = current;    // store the current  node to return
                current = succ(current);        // move to the next node (ordered successor)
                return nodeToReturn;
            }
        };
    }

    public void merge(SortedBinaryTree<Node> treeToMerge) {
        for (Node n : treeToMerge) {
            System.out.println(n.toString());
            StudentItem item = new StudentItem(((StudentItem) n).getData());
            this.insert(item);
        }
    }
    
    private boolean balanced(Node k) {
    	boolean check = true;
    	if (k != null) {
    		int leftHeight = height(k.getLeft());
    		int rightHeight = height(k.getRight());
    		check = Math.abs(leftHeight - rightHeight) <= 1;
    		
    		if (check) check = balanced(k.getLeft()) && balanced(k.getRight());
    	}
    	return check;
    }
    
    private int height(Node k) {
    	int height = -1;
    	if (k != null) {
    		int leftHeight = height(k.getLeft());
    		int rightHeight = height(k.getRight());
    		
    		height = 1 + Math.max(leftHeight, rightHeight);
    	}
    	return height; 
    }

    // private helper method
    private void recursiveInsert(Node current, Node k, Node predecessor) {
        if (current == null) {                  // BASE CASE: empty spot is found
            // insert the new k node and connect it the correct side of the predecessor accordingly
            k.setParent(predecessor);
            if (k.getKey() < predecessor.getKey()) {
                predecessor.setLeft(k);
            } else {
                predecessor.setRight(k);
            }
        }
        else {                                  // RECURSIVE STEP: Keep searching down on both sides
            if (k.getKey() < current.getKey()) {
                recursiveInsert(current.getLeft(), k, current);
            } else {
                recursiveInsert(current.getRight(), k, current);
            }
        }
    }
}
