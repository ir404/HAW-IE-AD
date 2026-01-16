package worksheet4;

import java.util.ArrayList;

public class AdjacencyLists {
	private ArrayList<AdjacencyList> neighbourList=new ArrayList<AdjacencyList>();
	
	public AdjacencyLists(AdjacencyMatrix neighboursMatrix) {
		for(int i=0;i<neighboursMatrix.numOfVertices();i++) {
			AdjacencyList ad =new AdjacencyList(i);
			for (int j=0;j<neighboursMatrix.numOfVertices();j++){
				if(neighboursMatrix.get(i, j)!=0) {
					ad.add(j);
				}
			}
			neighbourList.add(ad);
		}
	}
	
	public AdjacencyList getNeighboursFor(int v) {
		return neighbourList.get(v);
		
	}
}
