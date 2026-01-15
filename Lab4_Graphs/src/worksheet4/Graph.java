package worksheet4;

public class Graph {
    private AdjacencyMatrix neighboursMatrix;
    private AdjacencyLists neighboursList;

    public Graph(int[][] adjMatrix) {
        neighboursMatrix = new AdjacencyMatrix(adjMatrix);
        neighboursList = new AdjacencyLists(neighboursMatrix);
    }

    public AdjacencyList getNeighboursFor(int vertex) {
        return neighboursList.getNeighboursFor(vertex);
    }

    public int numOfVertices() {
        return neighboursMatrix.numOfVertices();
    }

    public int numOfEdges() {
        int rows = numOfVertices();
        int cols = rows;
        int edgeCount = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = i; j < cols; j++) {
                if (neighboursMatrix.get(i, j) != 0) {
                    edgeCount++;
                }
            }
        }
        return edgeCount;
    }

    public int getWeight(int u, int v) {
        return neighboursMatrix.get(u, v);
    }

    public AdjacencyList somePath(int u, int length) {
        return null;
    }
}
