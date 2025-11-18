package graphs;

public class Edge {
	private final int DOES_NOT_EXIST = -1;
	
	private int distance;
	
	public Edge() {
		this.distance = DOES_NOT_EXIST;
	}
	
	public boolean exists() {
		return distance != DOES_NOT_EXIST;
	}
	
	public int getDistance() {
		return distance;
	}
	
	public void connect(int distance) {
		this.distance = distance;
	}
	
	public void disconnect() {
		distance = DOES_NOT_EXIST;
	}
}
