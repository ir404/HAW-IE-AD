package example;

public class Stack {
	private List list;
	
	public Stack() {
		list = new List();
	}
	
	public boolean empty() {
		return list.empty();
	}

	public void push(String value) {
		list.addToFront(value);
	}
	
	public String top() {
		if (list.getHead() != null) {
			return list.getHead().getValue();
		} else {
			return "No element on stack!!!";
		}
	}
	
	public String pop() {
		return list.removeHead().getValue();
	} 
}
