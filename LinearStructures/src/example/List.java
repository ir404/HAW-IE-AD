package example;

// This implementation is of a doubly-linked list

public class List {
	private Element head; 		// reference to the very first element of the list
	private Element tail;		// reference to the very last element of the list
	private int length;			// number of elements in the list
	
	public List() {
		// initialisation
		head = null;
		tail = null;
		length = 0;
	}
	
	public boolean empty() {
		return head == null;
		// alternatively, return tail == null;
	}
	
	// inserts a new element in front of the current head, the newly added element becomes the new head
	public void addToFront(String s) {
		Element e = new Element(s);
		head.setPrevious(e);
		e.setNext(head);
		head = e;
		if (empty()) {			// if the list is empty
			tail = e;				// then tail and head both point to the same element
		}
		length += 1;
	}
	
	// inserts a new element right after the current tail and the reference to tail is updated
	public void append(String s) {
		Element e = new Element(s);
		if (empty() ) {			// if the list is empty	
			head = e;				// then head and tail both point to the same element
		} else {				// otherwise,
			tail.setNext(e);		// the current tail will point to the new element 
		}
		e.setPrevious(tail);
		tail = e;				// the new element becomes the new tail
		length += 1;
	}
	
	public void includeAt(int position, String s) {
		if (length == 0 || position == 0) {
			addToFront(s);
		} else if (position >= length) {
			append(s);
		} else {
			Element current = getHead();
			Element previous = null;
			int index = 0;
			
			while (index < length && index != position) {
				previous = current;
				current = current.getNext();
				index++;
			}
			
			if (index == position) {
				Element temp = new Element(s);
				temp.setNext(current);	
				current.setPrevious(temp);
				if (previous != null) 
					previous.setNext(temp);
			}
		} 
	}
	
	public void removeAt(int position) {
		if (length > 0 && position >= 0) {
			if (position == 0) { 
				removeHead();
			} else if (position < length) {
				Element current = getHead();
				Element previous = null;
				int index = 0;
				
				while (index < length && index != position) {
					previous = current;
					current = current.getNext();
					index++;
				}
				
				if (index == position) {
					if (previous != null) {
						previous.setNext(current.getNext());
						
						if (current.getNext() != null) {
							current.getNext().setPrevious(previous);
						} else {
							tail = previous;
						}
					}
				}
			}
		}
	}
	
	public int length() {
		return length;
	}
	
	public Element getHead() {
		return head;
	}
	
	public Element removeHead() {
		Element oldHead = head;
		if (head != null) 
			head = head.getNext();
		return oldHead;
	}
	
	public Element removeTail() {
		Element oldTail = tail;
		if (tail != null) {
			Element temp = tail.getPrevious();
			temp.setNext(null);
			tail = temp;
		}
		return oldTail;
	}
	
	public boolean search(String searchFor) {
		Element current = getHead();
		boolean found = false;
		while (!found && current != null) {
			if (current.getValue() == searchFor) found = true;
			
			current = current.getNext();		// move to the next element that current points to
		}
		return found;
	}
	
	public void display() {
		Element current = getHead();
		String result = "";
		if (current != null && current == head) {
			result = current.getValue();
			current = current.getNext();
			while (current != null) {	
				result += " -> " + current.getValue();
				current = current.getNext();
			}
		}
		System.out.println(result);
	}
}
