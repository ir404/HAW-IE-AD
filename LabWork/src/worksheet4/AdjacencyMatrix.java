package worksheet4;

public class AdjacencyMatrix {
	private int [][] adjMatrix;
	
	public AdjacencyMatrix(int[][] adjMatrix) {
		this.adjMatrix=adjMatrix;
	}
	
	public int get(int v, int u) {
		return adjMatrix[v][u];
	}
	
	public int numOfVertices() {
		// TODO Auto-generated method stub
		return adjMatrix.length;
	}
}
