package tree;

import data.Student;

public class TreeTest {
    public void start() {
        SortedBinaryTree<Node> classA = new SortedBinaryTree<>();
        classA.insert(new StudentItem(new Student("Alice", 10)));
        classA.insert(new StudentItem(new Student("Bob", 1)));
        classA.insert(new StudentItem(new Student("Charlie", 5)));
        classA.insert(new StudentItem(new Student("David", 2)));
        classA.insert(new StudentItem(new Student("Eve", 12)));

        SortedBinaryTree<Node> classB = new SortedBinaryTree<>();
        classB.insert(new StudentItem(new Student("Frank", 8)));
        classB.insert(new StudentItem(new Student("Grace", 3)));
        classB.insert(new StudentItem(new Student("Heidi", 14)));
        classB.insert(new StudentItem(new Student("Ivan", 5)));
        classB.insert(new StudentItem(new Student("Judy", 9)));
        classB.insert(new StudentItem(new Student("James", 7)));
        classB.insert(new StudentItem(new Student("Gwen", 6)));
        classB.insert(new StudentItem(new Student("Myo", 4)));

        System.out.println("\n[Class A before merge]:");
        printTree(classA);
        System.out.println("Height: " + classA.getHeight());

        System.out.println("\n[Class B before merge]:");
        printTree(classB);
        System.out.println("Height: " + classB.getHeight());

        // Merge Class B into Class A
        System.out.println("\n... Merging Class B into Class A ...");
        classB.merge(classA);

        // Verify the result (Sorted Master List)
        System.out.println("\n[Master List (Sorted)]:");
        printTree(classB);
        System.out.println("Height: " + classB.getHeight());
        System.out.println("Is balanced? " + classB.isBalanced());

        int searchKey = 16;
        Node result = classB.find(searchKey);
        System.out.println("\n\nFind " + searchKey + " in Class B");
        if (result != null) {
        	System.out.println("Student name: " + result.toString());
        }
        else {
        	System.out.println("No student with grade " + searchKey);
    	}
    }

    public void printTree(SortedBinaryTree<Node> tree) {
        for(Node n: tree) {
        	System.out.println(n);
        }
    }
    
}
