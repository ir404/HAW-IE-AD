package worksheet4;

import java.util.Iterator;
import java.util.LinkedList;

public class AdjacencyList implements Iterable<Integer> {
	private LinkedList<Integer> adjacencies = new LinkedList<Integer>();
	private int id;
	
	public AdjacencyList (int id) {
		this.id=id;
	}
	
	public int getId() {
        return this.id;
    }
	
	public void add(int neighbour) {
		adjacencies.add(neighbour);
	}
	
	public boolean contains(int v) {
		// TODO Auto-generated method stub
		return adjacencies.contains(v);
	}

	public int size() {
		// TODO Auto-generated method stub
		return adjacencies.size();
	}

	public int get(int currentIndex) {
		// TODO Auto-generated method stub
		return adjacencies.get(currentIndex);
	}
	@Override
	public Iterator<Integer> iterator(){
		return adjacencies.iterator();
	}

}
