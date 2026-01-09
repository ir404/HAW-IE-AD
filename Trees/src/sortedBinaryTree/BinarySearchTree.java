package sortedBinaryTree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree implements Iterable<Node> {
    private Node root;
    private int nodeCount;

    public BinarySearchTree() {
        clear();
    }

    public int getNodeCount() {
        return nodeCount;
    }

    public int getHeight() {
        return height(root);
    }

    public void clear() {
        root = null;
        nodeCount = 0;
    }

    public boolean isEmpty() {
        return nodeCount == 0;
    }

    public boolean isBalanced() {
        return checkBalance(root);
    }

    // returns the node with the specified value k
    public Node findValue(int k) {
        Node result = null;
        if (!isEmpty()) {
            result = search(root, k);
        }
        return result;
    }

    public void add(Node k) {
        if (isEmpty()) {
            root = k;
        } else {
            insert(root, k, root.getParent());
        }
        nodeCount++;
    }

    // returns the node with the smallest value
    public Node minium() {
        Node result = null;
        if (!isEmpty()) {
            result = min(root);
        }
        return result;
    }

    // returns the node with the largest value
    public Node maximum() {
        Node result = null;
        if (!isEmpty()) {
            result = max(root);
        }
        return result;
    }

    // returns the successor of k in the order of values
    public Node orderedSuccessor(Node k) {
        Node successor;
        if (k.getRight() != null) {
            successor = min(k.getRight());
        } else {
            Node predecessor = k.getParent();
            while (predecessor != null && predecessor.getValue() <= k.getValue()) {
                predecessor = predecessor.getParent();
            }
            successor = predecessor;
        }
        return successor;
    }

    public void merge(BinarySearchTree tree) {
        for (Node k : tree) {
            add(k);
        }
    }

    public void printPreOrder() {
        preOrder(root);
        System.out.println();
    }

    public void printPostOrder() {
        postOrder(root);
        System.out.println();
    }

    public void printLevelOrder() {
        Queue<Node> queue = new LinkedList<>();         // java.util.Queue is an interface
        Node k;

        queue.add(root);
        while (!queue.isEmpty()) {
            k = queue.poll();                           // equivalent to pop
            System.out.print(k.getValue() + ", ");

            if (k.getLeft() != null) {
                queue.add(k.getLeft());
            }
            if (k.getRight() != null) {
                queue.add(k.getRight());
            }
        }
        System.out.println();
    }

    // recursive insert
    private void insert(Node current, Node k, Node predecessor) {
        if (current == null) {
            k.setParent(predecessor);
            k.setRight(null);
            k.setLeft(null);
            if (k.getValue() < predecessor.getValue()) {
                predecessor.setLeft(k);
            } else {
                predecessor.setRight(k);
            }
        } else {
            if (k.getValue() < current.getValue()) {
                insert(current.getLeft(), k, current);
            } else {
                insert(current.getRight(), k, current);
            }
        }
    }

    // returns the height of a (sub-)tree that  starts at parent k
    private int height(Node k) {
        int height  = -1;
        if  (k != null) {
            int leftHeight = height(k.getLeft());
            int rightHeight = height(k.getRight());
            height = Math.max(leftHeight, rightHeight) + 1;
        }
        return height;
    }

    // searches ths tree for a specific value k
    private Node search(Node current, int k) {
        Node result = null;
        if (current != null) {
            if (current.getValue() == k) {
                result = current;
            } else if (k < current.getValue()) {
                result = search(current.getLeft(), k);
            } else {
                result = search(current.getRight(), k);
            }
        }
        return result;
    }

    // returns the left-most (leaf) node
    private Node min(Node current) {
        while (current.getLeft() != null) {
            current = current.getLeft();
        }
        return current;
    }

    // returns the right-most (leaf) node
    private Node max(Node current) {
        while (current.getRight() != null) {
            current = current.getRight();
        }
        return current;
    }

    // checks if a (sub-)tree is balanced by comparing the height of the left and right subtrees at all nodes
    // the difference in height should be at most 1 for the tree
    private boolean checkBalance(Node k) {
        boolean balanced = true;
        if (k != null) {
            int leftHeight = height(k.getLeft());
            int rightHeight = height(k.getRight());
            balanced = Math.abs(leftHeight - rightHeight) <= 1;

            if (balanced) {
                balanced = checkBalance(k.getLeft()) && checkBalance(k.getRight());
            }
        }
        return balanced;
    }

    private void preOrder(Node current) {
        if (current != null) {
            System.out.print(current.getValue() + ", ");
            preOrder(current.getLeft());
            preOrder(current.getRight());
        }
    }

    private void postOrder(Node current) {
        if (current != null) {
            postOrder(current.getLeft());
            postOrder(current.getRight());
            System.out.print(current.getValue() + ", ");
        }
    }

    // returns a one-line string of node values in-order separated by commas
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Node node: this) {
            result.append(node.getValue()).append(", ");
        }
        return result.toString();
    }

    @Override
    public Iterator<Node> iterator() {
        // this iterator enables in-order traversal of this binary tree
        return new Iterator<>() {
            Node current = minium();

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Node next() {
                Node result = current;
                current = orderedSuccessor(current);
                return result;
            }
        };
    }

    /**
     * Public method to start the visual print.
     */
    public void display() {
        System.out.println("Tree Structure (Rotated 90°):");
        System.out.println("-----------------------------");
        printTree(root, 0);
        System.out.println("-----------------------------");
    }

    /**
     * Recursive helper that prints the tree structure.
     * Uses a Reverse In-Order Traversal.
     */
    private void printTree(Node current, int level) {
        if (current == null) {
            return;
        }

        // 1. Process Right child first (appears at the top of console)
        printTree(current.getRight(), level + 1);

        // 2. Print current node with indentation based on level
        if (level != 0) {
            for (int i = 0; i < level - 1; i++) {
                System.out.print("|\t"); // Vertical bars for alignment
            }
            System.out.println("|-------" + "(" + current.getValue() + ")");
        } else {
            System.out.println("(" + current.getValue() + ")");
        }

        // 3. Process Left child (appears at the bottom of console)
        printTree(current.getLeft(), level + 1);
    }
}
