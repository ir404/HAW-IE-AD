package tree;

import java.util.ArrayList;
import java.util.Iterator;

public class SortedBinaryTree<E> {
    private Node root;

    public SortedBinaryTree() {
        this.root = null;
    }

    public void insert(Node k) {
        if (root == null) {
            root = k;
            k.setParent(null);
        } else {
            recursiveInsert(root, k, null);
        }
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
        ArrayList<Node> nodesToAdd = new ArrayList<>();
        Iterator<Node> it = treeToMerge.iterator();
        while (it.hasNext()) {
            nodesToAdd.add(it.next());
        }

        for (Node n : nodesToAdd) {
            // Clean the node so it doesn't bring old Tree B connections with it
            n.setParent(null);
            n.setLeft(null);
            n.setRight(null);
            this.insert(n);
        }
    }

    // private helper method
    private void recursiveInsert(Node current, Node k, Node predecessor) {
        if (current == null) {                  // BASE CASE: empty spot is found
            // insert the new k node and connect it the correct side of the predecessor accordingly
            k.setParent(predecessor);
            if (k.getKey() < predecessor.getKey()) {
                predecessor.setLeft(k);
                k.setRight(null);
            } else {
                predecessor.setRight(k);
                k.setLeft(null);
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
