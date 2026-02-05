package myGraph;

import java.util.Iterator;
import java.util.LinkedList;

public class AdjacencyList implements Iterable<Node> {
    private int vertexId;
    private LinkedList<Node> neighbours;

    public  AdjacencyList(int vertexId) {
        this.vertexId = vertexId;
        neighbours = new LinkedList<>();
    }

    public void add(int destination, int distance) {
        neighbours.add(new Node(destination, distance));
    }

    public boolean isEmpty() {
        return neighbours.isEmpty();
    }

    @Override
    public Iterator<Node> iterator() {
        return neighbours.iterator();
    }

    public int getVertexId() {
        return vertexId;
    }
}
