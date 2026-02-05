package myGraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Graph {
    private ArrayList<AdjacencyList> adjacencyLists;
    private HashMap<Integer, String> vertexNames;
    private int vertexCount;

    public Graph(int vertexCount) {
        this.vertexCount = vertexCount;
        vertexNames = new HashMap<>(vertexCount);
        adjacencyLists = new ArrayList<>();
        for (int i = 0; i < vertexCount; i++) {
            adjacencyLists.add(new AdjacencyList(i));
        }
    }

    public void setVertexName(int vertexId, String name) throws IllegalArgumentException {
        if (vertexId >= 0 && vertexId < vertexCount) {
            vertexNames.put(vertexId, name);
        } else {
            throw new IllegalArgumentException("Vertex Id is invalid");
        }
    }

    public void addEdge(int from, int to, int distance) {
        adjacencyLists.get(from).add(to, distance);
    }

    public int getDistance(int from, int to) {
        int distance;
        ArrayList<Integer> visited = new ArrayList<>();
        visited.add(from);
        distance = findPath(from, to, 0, visited);
        return distance;
    }

    public void printAdjacencyLists() {
        for (AdjacencyList adjList : adjacencyLists) {
            System.out.println(getVertexName(adjList.getVertexId()));
            for (Node neighbour : adjList) {
                int weight = neighbour.getWeight();
                System.out.println("|--(" + weight + ")-→ " + getVertexName(neighbour.getDestination()));
            }
            System.out.println();
        }
    }

    public String getVertexName(int vertexId) {
        return vertexNames.getOrDefault(vertexId, Integer.toString(vertexId));
    }

    // depth-first-search
    private int findPath(int current, int target, int currentDist, ArrayList<Integer> visited) {
        boolean found = (current == target);
        int finalDist = -1;
        AdjacencyList neighbours = adjacencyLists.get(current);

        if (found) {
            finalDist = currentDist;
        } else if (!neighbours.isEmpty()) {
            Iterator<Node> it = neighbours.iterator();
            while (!found && it.hasNext()) {
                Node neighbour = it.next();
                int nextStepDist = currentDist + neighbour.getWeight();
                if (neighbour.getDestination() == target) {
                    found = true;
                    finalDist = nextStepDist;
                } else if (!visited.contains(neighbour.getDestination())) {
                    visited.add(neighbour.getDestination());
                    int result = findPath(neighbour.getDestination(), target, nextStepDist, visited);
                    if (result != -1) {
                        finalDist = result;
                        found = true;
                    }
                }
            }
        }

        return finalDist;
    }
}
