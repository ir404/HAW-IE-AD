package datastructures;

public class Queue<E> extends List<E> {
    public Queue() {
        super();
    }

    public void enqueue(E data) {
        append(data);
    }

    public E dequeue() {
        E front = watch();
        remove(front);
        return front;
    }

    public E watch() {
        return get(0);
    }
}
