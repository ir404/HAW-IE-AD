package graphs;

public class Test {

	public static void main(String[] args) {
		AdjacencyMatrix m = new AdjacencyMatrix();
		m.addVertex();
		m.addVertex();
		m.addVertex();
		m.addEdge(0, 2, 9);
		m.addEdge(2, 1, 6);
		m.print();
		
		System.out.println("Sum of weights: " + m.sumOfWeights());
	}

}
