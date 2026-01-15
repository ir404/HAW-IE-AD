package worksheet4;

import java.util.ArrayList;

public class AdjacencyLists {
    private ArrayList<AdjacencyList> neighbourList;

    public AdjacencyLists(AdjacencyMatrix neighboursMatrix) {
        int rows = neighboursMatrix.numOfVertices();
        int cols = rows;

        neighbourList = new ArrayList<>();

        for  (int i = 0; i < rows; i++) {
            AdjacencyList adjList = new AdjacencyList(i);
            for (int j = 0; j < cols; j++) {
                if (neighboursMatrix.get(i, j) != 0) {
                    adjList.add(j);
                }
            }
            neighbourList.add(adjList);
        }
    }

    public AdjacencyList getNeighboursFor(int vertex) {
        return neighbourList.get(vertex);
    }
}
