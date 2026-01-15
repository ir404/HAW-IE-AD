package worksheet4;

import java.util.Iterator;
import java.util.LinkedList;

public class AdjacencyList implements Iterable<Integer> {
    private LinkedList<Integer> adjacencies;

    public AdjacencyList(int id) {
        adjacencies = new LinkedList<>();
        adjacencies.add(id);
    }

    public int getId() {
        return adjacencies.getFirst();
    }

    public void add(int neighbour) {
        adjacencies.add(neighbour);
    }

    public boolean contains(int vertex) {
        int index = 0;
        int size =  adjacencies.size();
        boolean found = false;
        while (index < size && !found) {
            if (adjacencies.get(index) == vertex) {
                found = true;
            }
            index++;
        }
        return found;
    }

    // was missing
    public int get(int index) {
        return adjacencies.get(index);
    }

    // was missing
    public int size() {
        return adjacencies.size();
    }


    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            private int current = 1;        // start from the 2nd element as the first represents just the id

            @Override
            public boolean hasNext() {
                boolean next = true;
                try {
                    get(current);
                } catch (IndexOutOfBoundsException e) {
                    next = false;
                }
                return next;
            }

            @Override
            public Integer next() {
                int toReturn = get(current);
                current = current + 1;
                return toReturn;
            }
        };
    }
}
