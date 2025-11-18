package graphs;

public class Test {

	public static void main(String[] args) {
		AdjacencyMatrix m = new AdjacencyMatrix();
		m.addVertex();
		m.addVertex();
		m.addVertex();
		m.addEdge(0, 2, 10);
		m.addEdge(2, 1, 12);
		m.print();

	}

}
