package example;

public class Element {
	private String value;
	private Element next;
	private Element previous;
	
	public Element(String value) {
		this.value = value;
		next = null;
		previous = null;
	}
	
	public void setNext(Element nextOne) {
		next = nextOne;
	}
	
	public void setPrevious(Element previousOne) {
		previous = previousOne;
	}
	
	public String getValue() {
		return value;
	}
	
	public Element getNext() {
		return next;
	}
	
	public Element getPrevious() {
		return previous;
	}
}
