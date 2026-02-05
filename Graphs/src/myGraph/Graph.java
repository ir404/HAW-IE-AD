package myGraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Graph<T> {
    private List<AdjacencyList> adjacencyLists;
    private HashMap<T, Integer> vertexToIndex;
    private List<T> indexToVertex;
    private int vertexCount;

    public Graph() {
        vertexToIndex = new HashMap<>();
        indexToVertex = new ArrayList<>();
        adjacencyLists = new ArrayList<>();
    }

    public void addVertex(T vertex) {
        if (!vertexToIndex.containsKey(vertex)) {
            int newId = vertexCount;
            vertexToIndex.put(vertex, newId);
            indexToVertex.add(vertex);
            adjacencyLists.add(new AdjacencyList(newId));
            vertexCount++;
        }
    }

    public void addEdge(T from, T to, int weight) throws IllegalArgumentException {
        Integer fromId = vertexToIndex.get(from);
        Integer toId = vertexToIndex.get(to);

        if (fromId == null || toId == null) {
            throw new IllegalArgumentException("Vertex not found! Call addVertex() first.");
        } else {
            adjacencyLists.get(fromId).add(toId, weight);
        }
    }

    public int getDistance(T from, T to) {
        int distance = 0;
        Integer fromId = vertexToIndex.get(from);
        Integer toId = vertexToIndex.get(to);

        if (fromId == null || toId == null) {
            distance = -1;
        } else {
            ArrayList<Integer> visited = new ArrayList<>();
            visited.add(fromId);
            distance = findPath(fromId, toId, distance, visited);
        }
        return distance;
    }

    public void printAdjacencyLists() {
        for (AdjacencyList adjList : adjacencyLists) {
            T vertex = indexToVertex.get(adjList.getVertexId());
            System.out.println(vertex.toString());
            for (Node neighbour : adjList) {
                int weight = neighbour.getWeight();
                T destinationVertex = indexToVertex.get(neighbour.getDestination());
                System.out.println("|--(" + weight + ")-→ " + destinationVertex.toString());
            }
            System.out.println();
        }
    }

    // depth-first-search
    private int findPath(int currentId, int targetId, int currentDist, ArrayList<Integer> visited) {
        boolean found = (currentId == targetId);
        int finalDist = -1;
        AdjacencyList neighbours = adjacencyLists.get(currentId);

        if (found) {
            finalDist = currentDist;
        } else if (!neighbours.isEmpty()) {
            Iterator<Node> it = neighbours.iterator();
            while (!found && it.hasNext()) {
                Node neighbour = it.next();
                int nextStepDist = currentDist + neighbour.getWeight();
                if (neighbour.getDestination() == targetId) {
                    found = true;
                    finalDist = nextStepDist;
                } else if (!visited.contains(neighbour.getDestination())) {
                    visited.add(neighbour.getDestination());
                    int result = findPath(neighbour.getDestination(), targetId, nextStepDist, visited);
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
