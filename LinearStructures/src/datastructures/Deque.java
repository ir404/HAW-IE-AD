package datastructures;

// short for Double-Ended Queue - integrates both a Stack and a Queue
public class Deque<E> extends List<E> {
    public Deque() {
        super();
    }

    public void addBottom(E data) {
        append(data);
    }

    public void addTop(E data) {
        if (isEmpty())
            append(data);
        else
            addAt(0, data);
    }

    public E removeBottom() {
        E bottom = watchBottom();
        remove(bottom);
        return bottom;
    }

    public E removeTop() {
        E top = watchTop();
        remove(get(0));
        return top;
    }

    public E watchBottom() {
        return get(length() - 1);
    }

    public E watchTop() {
        return get(0);
    }
}
