package worksheet4;

import java.util.ArrayList;
import java.util.Random;

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

	public AdjacencyList somePathBasedOnWeights(int startVertex, int length) {
		return null;
	}


	/**
	 * Gets all possible paths starting from u and returns a random one from them that meets
	 * the passed length
	 * @param startVertex a vertex in the graph
	 * @param length how many successive vertices it should go to
	 * @return a path starting from u that's length vertices long or an empty path if none found
	 */
    public AdjacencyList somePath(int startVertex, int length) {
		ArrayList<AdjacencyList> allPaths = new ArrayList<>();
    	AdjacencyList neighbours = getNeighboursFor(startVertex);
		int neighbourCount = neighbours.size();
		
		int index = 0;
    	while (index < neighbourCount) {
    		AdjacencyList path = new AdjacencyList(startVertex);
    		path.add(startVertex);
    		mapPath(neighbours.get(index), path, allPaths);
    		allPaths.add(path);
    		index += 1;
    	}
		displayAllPaths(allPaths);
    	
    	ArrayList<AdjacencyList> filteredPaths = new ArrayList<>();
    	for(AdjacencyList ad: allPaths) {
    		if (ad.size() == length + 1) {
    			filteredPaths.add(ad);
    		}
    	}
    	
    	AdjacencyList result = new AdjacencyList(startVertex);
    	result.add(startVertex);
    	if (!filteredPaths.isEmpty()) {
	    	Random rand = new Random();
	    	int randInt = rand.nextInt(filteredPaths.size());
	    	result = filteredPaths.get(randInt);
    	}
    	
        return result;
    }

	// helper method to recursively find all paths from v
    private void mapPath(int v, AdjacencyList path, ArrayList<AdjacencyList> allPaths) {
    	AdjacencyList neighbours = getNeighboursFor(v);
    	int neighbourCount = neighbours.size();
    	
    	path.add(v);
    	
    	int index = 0;
    	while (index < neighbourCount) {
    		int neighbour = neighbours.get(index);
    		// check if neighbour does not exist in path travelled so far
    		if (!path.contains(neighbour)) {
	    		AdjacencyList nextPath = new AdjacencyList(neighbour, path);
	    		mapPath(neighbour, nextPath, allPaths);
	    		allPaths.add(nextPath);
    		}
    		index += 1;
    	}
    }
    
    private void displayAllPaths(ArrayList<AdjacencyList> possiblePaths) {
    	int index = 1;
    	for (AdjacencyList path: possiblePaths) {
    		System.out.print("Path " + index + ": ");
    		for (int v: path) {
    			System.out.print(v + ", ");
    		}
    		System.out.println();
    		index++;
    	}
    }
}
