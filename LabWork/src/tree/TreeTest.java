package tree;

import data.Student;
import java.util.Iterator;

public class TreeTest {
    public void TestProgram(){
        System.out.println("--- TASK 1: Application Test ---");

        // 1. Create Data Set A (Class A)
        SortedBinaryTree<Node> classA = new SortedBinaryTree<>();
        classA.insert(new StudentItem(new Student("Alice", 10)));
        classA.insert(new StudentItem(new Student("Bob", 5)));
        classA.insert(new StudentItem(new Student("Charlie", 15)));
        classA.insert(new StudentItem(new Student("David", 2)));
        classA.insert(new StudentItem(new Student("Eve", 12)));

        // 2. Create Data Set B (Class B)
        SortedBinaryTree<Node> classB = new SortedBinaryTree<>();
        classB.insert(new StudentItem(new Student("Frank", 8)));
        classB.insert(new StudentItem(new Student("Grace", 3)));
        classB.insert(new StudentItem(new Student("Heidi", 14)));
        classB.insert(new StudentItem(new Student("Ivan", 1)));
        classB.insert(new StudentItem(new Student("Judy", 9)));

        System.out.println("\n[Class A before merge]:");
        classA.printTrees(classA);

        System.out.println("\n[Class B before merge]:");
        classB.printTrees(classB);

        // 3. Merge Class B into Class A
        System.out.println("\n... Merging Class B into Class A ...");
        classA.merge(classB);

        // 4. Verify the result (Sorted Master List)
        System.out.println("\n[Master List (Sorted)]:");
        classA.printTrees(classA);
    }


}