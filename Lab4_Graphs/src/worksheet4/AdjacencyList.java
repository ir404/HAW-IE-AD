package worksheet4;

import java.util.Iterator;
import java.util.LinkedList;

public class AdjacencyList implements Iterable<Integer> {
    private LinkedList<Integer> adjacencies;
    private int id;

    public AdjacencyList(int id) {
        adjacencies = new LinkedList<>();
        this.id = id;
    }

    public AdjacencyList(int id, AdjacencyList copy) {
    	this(id);
    	for (int v: copy) {
    		add(v);
    	}
    }

    public int getId() {
        return id;
    }

    public void add(int neighbourVertex) {
        adjacencies.add(neighbourVertex);
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

    public int get(int index) {
        return adjacencies.get(index);
    }

    public int size() {
        return adjacencies.size();
    }


    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<>() {
            private int current = 0;

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
