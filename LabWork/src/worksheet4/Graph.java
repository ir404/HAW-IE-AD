package worksheet4;

public class Graph {
	private AdjacencyMatrix neighboursMatrix;
	private AdjacencyLists neighboursLists;
	
	public Graph(int[][]  adjMatrix) {
		this.neighboursMatrix=new AdjacencyMatrix(adjMatrix);
		this.neighboursLists=new AdjacencyLists(neighboursMatrix);
	}
	public AdjacencyList getNeighboursFor(int v) {
		return neighboursLists.getNeighboursFor(v);
	}
	
	public int numOfVertices() {
		// TODO Auto-generated method stub
		return neighboursMatrix.numOfVertices();
	}

	public int numOfEdges() {
		// TODO Auto-generated method stub
		int edges=0;
		for(int i=0;i<neighboursMatrix.numOfVertices();i++) {
			for(int j=0;j<neighboursMatrix.numOfVertices();j++) {
				if (neighboursMatrix.get(i, j)!=0) {
					edges++;
				}
			}
		}
		return edges/2;
	}
	
	public int getWeight(int u, int v) {
		// TODO Auto-generated method stub
		return neighboursMatrix.get(u, v);
	}

	public AdjacencyList somePath(int i, int j) {
		// TODO Auto-generated method stub
		return null;
	}

}
