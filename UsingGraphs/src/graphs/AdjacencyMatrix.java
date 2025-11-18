package graphs;

// HW add string nodes instead. Will be done using a Node or Vertex class with one String property

public class AdjacencyMatrix {
	private Edge[][] matrix;
	
	public AdjacencyMatrix(int nodeCount) {
		matrix = new Edge[nodeCount][nodeCount];
		for (int i = 0; i < nodeCount; i++) {
			for (int j = 0; j < nodeCount; j++) {
				matrix[i][j] = new Edge();
			} 
		}
	}
	
	public AdjacencyMatrix() {
		matrix = null;
	}
	
	public void addVertex() {
		if (matrix == null) {
			matrix = new Edge[1][1];
			matrix[0][0] = new Edge();
		} else {
			int currentLength = matrix.length;
			// copy old entries
			Edge[][] temp = new Edge[currentLength + 1][currentLength + 1];
	
			for (int i = 0; i < currentLength; i++) {
				for (int j = 0; j < currentLength; j++) {
					temp[i][j] = matrix[i][j];
				}
			}
			// fill new last row
			for (int j = 0; j < currentLength + 1; j++) {
				temp[currentLength][j] = new Edge();
			}
			// fill new last column
			for (int i = 0; i < currentLength; i++) {
				temp[i][currentLength] = new Edge();
			}

			matrix = temp;
		}
	}
	
	public void addEdge(int v1, int v2, int distance) {
		matrix[v1][v2].connect(distance);
	}
	
	public void removeEdge(int v1, int v2) {
		matrix[v1][v2].disconnect();
	}
	
	public void print() {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				int exists = matrix[i][j].exists() ? 1 : 0;
				System.out.print(exists + " ");
			}
			System.out.println();
		}
	}

}
