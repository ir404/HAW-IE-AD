package tree;

import data.Student;

import java.util.Iterator;

public class TreeTest {
    public void start() {
        SortedBinaryTree<Node> classA = new SortedBinaryTree<>();
        classA.insert(new StudentItem(new Student("Alice", 10)));
        classA.insert(new StudentItem(new Student("Bob", 5)));
        classA.insert(new StudentItem(new Student("Charlie", 15)));
        classA.insert(new StudentItem(new Student("David", 2)));
        classA.insert(new StudentItem(new Student("Eve", 12)));

        SortedBinaryTree<Node> classB = new SortedBinaryTree<>();
        classB.insert(new StudentItem(new Student("Frank", 8)));
        classB.insert(new StudentItem(new Student("Grace", 3)));
        classB.insert(new StudentItem(new Student("Heidi", 14)));
        classB.insert(new StudentItem(new Student("Ivan", 1)));
        classB.insert(new StudentItem(new Student("Judy", 9)));

        System.out.println("\n[Class A before merge]:");
        printTree(classA);

        System.out.println("\n[Class B before merge]:");
        printTree(classB);

        // Merge Class B into Class A
        System.out.println("\n... Merging Class B into Class A ...");
        classA.merge(classB);

        // Verify the result (Sorted Master List)
        System.out.println("\n[Master List (Sorted)]:");
        printTree(classA);
    }

    public void printTree(SortedBinaryTree<Node> tree) {
        Iterator<Node> it = tree.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}