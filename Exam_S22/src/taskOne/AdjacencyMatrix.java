package taskOne;

/**
 * Purpose: Represents a Graph using an Adjacency Matrix and provides algorithms to analyse edge weights.
 * <p>
 * Public Methods:
 * - AdjacencyMatrix(int[][]): Constructor to initialise the matrix.
 * - weightsOfNeighbours(int, int): Compares the sum of weights of two vertices.
 * - forAllVertices(): Iterates through all vertex pairs and prints comparison results.
 * - main(String[]): Driver method for testing.
 */
public class AdjacencyMatrix {
    private final int[][] adjMatrix;

    public AdjacencyMatrix(int[][] data) {
        adjMatrix = data;
    }

    public boolean weightsOfNeighbours(int v, int w) {
        int vWeights = 0;
        int wWeights = 0;
        int vertexCount = numOfVertices();
        for (int j = 0; j < vertexCount; j++) {
            int neighbourOfV = get(v, j);
            int neighbourOfW = get(w, j);
            if (neighbourOfV > 0) {
                vWeights += neighbourOfV;
            }
            if (neighbourOfW > 0) {
                wWeights += neighbourOfW;
            }
        }
        return (vWeights < wWeights);
    }

    public void forAllVertices() {
        int rows = numOfVertices();
        int cols = numOfVertices();
        for (int i = 0; i < rows; i++) {
            System.out.println("Compare vertex " + i + " with its neighbours");
            for (int j = 0; j < cols; j++) {
                if (get(i, j) > 0) {
                    System.out.println(" Vertex " + i +  " < " + "Vertex  " + j + " : " + weightsOfNeighbours(i, j));
                }
            }
        }
    }

    private int get(int v, int u) {
        return adjMatrix[v][u];
    }

    private int numOfVertices() {
        return adjMatrix.length;
    }

    public static void main(String[] args) {
        int[][] data = {
                {0, -1, 10, 35, -1},
                {-1, 0, 18, -1, 15},
                {-1, -1, 0, -1, 12},
                {-1, 25, -1, 0, -1},
                {20, 50, -1, -1, 0},
        };
        AdjacencyMatrix matrix = new AdjacencyMatrix(data);
        matrix.forAllVertices();
    }
}
