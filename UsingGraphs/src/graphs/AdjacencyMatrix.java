package graphs;

// HW add string nodes instead. Will be done using a Node or Vertex class with one String property

public class AdjacencyMatrix {
	private Edge[][] matrix;


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
			// fill new last row and column
			for (int x = 0; x < currentLength + 1; x++) {
				temp[currentLength][x] = new Edge();
				temp[x][currentLength] = new Edge();
			}
			// update the new matrix
			matrix = temp;
		}
	}
	
	public void addEdge(int v1, int v2, int distance) {
		matrix[v1][v2].connect(distance);
	}
	
	public void removeEdge(int v1, int v2) {
		matrix[v1][v2].disconnect();
	}
	
	// returns all weights (distances) summed up
	public int sumOfWeights() {
		int sum = 0;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				Edge e = matrix[i][j];
				if (e.exists()) {
					sum += e.getDistance();
				}
			}
		}
		return sum;
	}
	
	// returns the smallest weight in the matrix
	public int findSmallest() {
		int smallest = Integer.MAX_VALUE;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				Edge temp = matrix[i][j];
				if (temp.exists() && temp.getDistance() < smallest) {
					smallest = temp.getDistance();
				}
			}
		}
		return smallest;
	}
	
	// returns the largest weight in the matrix
	public int findLargest() {
		int largest = Integer.MIN_VALUE;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				Edge temp = matrix[i][j];
				if (temp.exists() && temp.getDistance() > largest) {
					largest = temp.getDistance();
				}
			}
		}	
		return largest;
	}
	
	public void print() {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				System.out.printf("%3d ", matrix[i][j].getDistance());
			}
			System.out.println();
		}
	}

}
