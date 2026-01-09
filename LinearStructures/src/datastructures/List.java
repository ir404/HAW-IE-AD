package datastructures;

import java.util.Iterator;

public abstract class List<E> implements Iterable<E>{
    private Node head;
    private Node tail;
    private int size;

    private class Node {
        private E value;
        private Node next;
        private Node previous;

        protected Node(E value) {
            this.value = value;
            next = null;
            previous = null;
        }
    }

    public List() {
        clear();
    }

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    public int length() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // inserts to the end of the existing list
    protected void append(E value) throws NullPointerException {
        if (value == null) {
            throw new NullPointerException();
        }

        Node node = new Node(value);
        if (head == null) {
            head = node;
        } else {
            node.previous = tail;
            tail.next = node;
        }
        tail = node;
        size++;
    }

    protected void addAt(int index, E value) throws IndexOutOfBoundsException, NullPointerException {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (value == null) {
            throw new NullPointerException();
        }

        if (isEmpty() || index == size) {
            append(value);
        } else {
            Node node = new Node(value);
            if (index == 0) {
                node.next = head;
                head.previous = node;
                head = node;
            } else {
                Node nodeAt = getNodeAt(index);
                Node prevNode = nodeAt.previous;

                prevNode.next = node;
                node.previous = prevNode;

                nodeAt.previous = node;
                node.next = nodeAt;
            }
            size++;
        }
    }

    protected boolean remove(E value) throws NullPointerException {
        if (value == null) {
            throw new NullPointerException();
        }

        boolean removed = false;
        Node current = head;
        while (!removed && current != null) {
            if (current.value.equals(value)) {
                unlink(current);
                removed = true;
            }
            current = current.next;
        }
        return removed;
    }

    protected E get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return getNodeAt(index).value;
    }

    public void print() {
        for (E current: this) {
            System.out.print(current + ", ");
        }
        System.out.println();
    }

    private void unlink(Node node) {
        Node prev = node.previous;
        Node next = node.next;

        if (prev != null) {
            prev.next = next;
        } else {
            head = next;
        }

        if (next != null) {
            next.previous = prev;
        } else {
            tail = prev;
        }

        size--;
    }

    private Node getNodeAt(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node result;
        if (index == 0) {
            result = head;
        } else if (index == size - 1) {
            result = tail;
        }
        else {
            Node current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            result = current;
        }
        return result;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private Node current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                E result = null;
                if (hasNext()) {
                    result = current.value;
                    current = current.next;
                }
                return result;
            }
        };
    }
}
