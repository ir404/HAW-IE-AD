package datastructures;

public class PriorityQueue<E> extends List<PriorityQueue<E>.PriorityItem> {

    protected class PriorityItem {
        private E data;
        private int priority;

        public PriorityItem(E data, int priority) {
            this.data = data;
            this.priority = priority;
        }

        @Override
        public String toString() {
            return data + " [" + priority + "]";
        }
    }

    public PriorityQueue() {
        super();
    }

    /**
     * Adds an element with a specific priority.
     * Lower integer values = Higher priority (moves to the front).
     */
    public void enqueue(E data, int priority) throws NullPointerException {
        if (data == null) {
            throw new NullPointerException();
        }

        PriorityItem item = new PriorityItem(data, priority);
        if (isEmpty()) {
            append(item);
        } else {            // find the correct position
            int insertAt = -1;
            int i = 0;
            while (insertAt == -1 && i < length()) {
                if (priority < get(i).priority) {
                    insertAt = i;
                }
                i++;
            }
            if (insertAt != -1) {
                addAt(insertAt, item);
            } else {                // lowest priority so far, so append
                append(item);
            }
        }
    }

    public E dequeue() {
        E result = null;
        if (!isEmpty()) {
            PriorityItem front = get(0);
            result = front.data;
            remove(front);
        }
        return result;
    }

    public E watch() {
        E result = null;
        if (!isEmpty()) {
            PriorityItem front = get(0);
            result = front.data;
        }
        return result;
    }
}
