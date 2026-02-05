package myGraph;

public class Node {
    private int destinationId;
    private int weight;

    public Node(int destinationId, int weight) {
        this.destinationId = destinationId;
        this.weight = weight;
    }

    public int getDestination() {
        return destinationId;
    }

    public int getWeight() {
        return weight;
    }
}
