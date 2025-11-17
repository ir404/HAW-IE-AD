import util.Student;
import util.Node;
import util.QuickSort;

import java.util.ArrayList;
import java.util.List;

public class SortTest {

    public static void start(List<Node> data) {
        System.out.println("\nAfter Sorting using QuickSort:\n");
        QuickSort sorter=new QuickSort(data);
         sorter.start();
         System.out.println("Comaparisons: "+sorter.getComparisons());
         System.out.println("Swaps: "+sorter.getSwaps());
    }

    public static void main(String[] args) {
        // --- Step (d): Store 10 meaningful data sets ---
        List<Node> studentList = new ArrayList<>();
        studentList.add(new Student("student_A", 8));
        studentList.add(new Student("student_B", 3));
        studentList.add(new Student("student_C", 12));
        studentList.add(new Student("student_D", 1));
        studentList.add(new Student("student_E", 8));
        studentList.add(new Student("student_F", 5));
        studentList.add(new Student("student_G", 9));
        studentList.add(new Student("student_H", 15));
        studentList.add(new Student("student_I", 0));
        studentList.add(new Student("student_J", 7));

        // --- Step (f): Print before ---
        System.out.println("--- BEFORE SORTING ---");
        for (Node s : studentList) {
        	Student student=(Student) s;
            System.out.println(student.getName()+":  "+s.getKey());
        }

        // --- Step (e): Call start() ---
        start(studentList);

        // --- Step (f): Print after ---
        for (Node s : studentList) {
        	Student student=(Student) s;
        	System.out.println(student.getName()+":  "+s.getKey());
        }
    }
}
