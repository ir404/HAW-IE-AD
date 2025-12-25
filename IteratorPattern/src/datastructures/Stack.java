package datastructures;

public class Stack<E> extends List<E>{
    public Stack() {
        super();
    }

    public void push(E data){
        append(data);
    }

    public E pop() {
        E top = top();
        remove(top);
        return top;
    }

    public E top() {
        return get(length() - 1);
    }
}
