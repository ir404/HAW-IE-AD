package example;

public class Main {

	public static void main(String[] args) {
		Stack names = new Stack();
		names.push("Ant");
		names.push("Bee");
		names.push("Cat");
		
//		System.out.println("Top of stack: " + names.top());
//		names.pop();
//		System.out.println("Top of stack: " + names.top());
//		names.pop();
//		System.out.println("Top of stack: " + names.top());
//		names.pop();
		
//		System.out.println("Search for Ant: " + names )
		
		List myList = new List();
//		myList.addToFront("Ant");
//		myList.addToFront("Bob");
//		myList.addToFront("Cat");
		myList.display();
		System.out.println("Search for Ant: " + myList.search("Dog"));
		System.out.println();
		myList.append("Adam");
		myList.append("Ben");
		myList.append("Cassie");
		myList.display();
		
		myList.addToFront("Max");
		myList.addToFront("Nate");
		myList.display();
		
		myList.includeAt(1, "Norbert");
		myList.display();
		
		myList.removeHead();
		myList.display();
		
		myList.removeAt(myList.length() - 1);
		myList.display();
		
		myList.append("John");
		myList.append("Zack");
		myList.display();
		
		Element temp = myList.removeTail();
		myList.display();
		if (temp != null)
			System.out.println(temp.getValue());
		else 
			System.out.println("oops");
		
		myList.removeAt(2);
		myList.display();
	}

}