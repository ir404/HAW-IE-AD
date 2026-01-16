package worksheet4;

public class AdjacencyMatrix {
    private int[][] adjMatrix;

    public AdjacencyMatrix(int[][] adjMatrix) {
        int rows =  adjMatrix.length;
        int cols = adjMatrix[0].length;

        if (rows != cols) {
            throw new IllegalArgumentException("AdjacencyMatrix must be a square matrix!");
        } else {
            this.adjMatrix = adjMatrix;
        }
    }

    public int get(int u, int v) {
        return adjMatrix[u][v];
    }

    public int numOfVertices() {
        return adjMatrix.length;
    }
}
