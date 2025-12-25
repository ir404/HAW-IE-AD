package datastructures;

import java.util.Iterator;

public class List<E> implements Iterable<E>{
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

    public void add(E value) throws NullPointerException {
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

    public boolean remove(E value) throws NullPointerException {
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

    public E get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        E result;
        if (index == 0) {
            result = head.value;
        } else if (index == size - 1) {
            result = tail.value;
        } else {
            Node current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            result = current.value;
        }

        return result;
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

        if  (prev != null) {
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
